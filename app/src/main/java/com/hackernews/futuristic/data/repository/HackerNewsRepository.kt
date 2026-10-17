package com.hackernews.futuristic.data.repository

import com.hackernews.futuristic.data.api.AlgoliaApi
import com.hackernews.futuristic.data.api.AlgoliaHit
import com.hackernews.futuristic.data.api.AlgoliaItem
import com.hackernews.futuristic.data.api.AlgoliaSearchResponse
import com.hackernews.futuristic.data.api.HackerNewsApi
import com.hackernews.futuristic.data.api.User
import com.hackernews.futuristic.data.local.dao.BookmarkDao
import com.hackernews.futuristic.data.local.dao.StoryDao
import com.hackernews.futuristic.data.local.entities.BookmarkEntity
import com.hackernews.futuristic.data.local.entities.toDomainModel
import com.hackernews.futuristic.data.local.entities.toEntity
import com.hackernews.futuristic.data.model.CommentHit
import com.hackernews.futuristic.data.model.CommentNode
import com.hackernews.futuristic.data.model.RelativeTimeRange
import com.hackernews.futuristic.data.model.Story
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/** Home feeds. FRONT_PAGE/NEW/ASK/SHOW are HN's live lists; TOP is highest-scored in a time window. */
enum class Feed(val label: String) { FRONT_PAGE("Front page"), NEW("New"), ASK("Ask"), SHOW("Show"), TOP("Top") }

enum class SearchType { STORIES, COMMENTS }

enum class SearchSort(val displayName: String) { RELEVANCE("Relevance"), NEWEST("Newest") }

data class SearchPage(
    val stories: List<Story>,
    val comments: List<CommentHit>,
    val page: Int,
    val hasMore: Boolean,
    val totalHits: Int
)

@Singleton
class HackerNewsRepository @Inject constructor(
    private val api: HackerNewsApi,
    private val algolia: AlgoliaApi,
    private val storyDao: StoryDao,
    private val bookmarkDao: BookmarkDao
) {

    private companion object {
        /** Scores and comment counts move fast; don't show cached ones older than this. */
        const val STORY_CACHE_TTL = 5 * 60 * 1000L
        const val STORY_CACHE_RETENTION = 24 * 60 * 60 * 1000L
        const val FEED_CACHE_TAG = "FEED"
    }

    // --- Feeds (Firebase: live ranking) ---

    suspend fun getFeedIds(feed: Feed): Result<List<Long>> = runCatching {
        when (feed) {
            Feed.FRONT_PAGE -> api.getTopStories()
            Feed.NEW -> api.getNewStories()
            Feed.ASK -> api.getAskStories()
            Feed.SHOW -> api.getShowStories()
            Feed.TOP -> throw IllegalArgumentException("TOP is paged via getTopStories")
        }.bodyOrThrow()
    }

    /** Highest-scored stories posted within [range]: Algolia ranks an empty query by points. */
    suspend fun getTopStories(range: RelativeTimeRange, page: Int): Result<SearchPage> =
        search(query = "", type = SearchType.STORIES, sort = SearchSort.RELEVANCE, timeRange = range, page = page)

    suspend fun getStoriesByIds(storyIds: List<Long>): Result<List<Story>> = runCatching {
        val now = System.currentTimeMillis()
        val cached = storyDao.getFreshStoriesByIds(storyIds, freshSince = now - STORY_CACHE_TTL)
            .map { it.toDomainModel() }
        val cachedIds = cached.mapTo(HashSet()) { it.id }
        val fetched = fetchStories(storyIds.filterNot { it in cachedIds })

        if (fetched.isNotEmpty()) {
            storyDao.insertStories(fetched.map { it.toEntity(FEED_CACHE_TAG) })
        }
        storyDao.deleteStoriesOlderThan(now - STORY_CACHE_RETENTION)

        val byId = (cached + fetched).associateBy { it.id }
        storyIds.mapNotNull { byId[it] }
    }

    suspend fun getStory(id: Long): Result<Story> = runCatching {
        api.getItem(id).bodyOrThrow()
    }

    private suspend fun fetchStories(ids: List<Long>): List<Story> = coroutineScope {
        ids.map { id ->
            async { runCatching { api.getItem(id).body() }.getOrNull() }
        }.awaitAll().filterNotNull()
    }

    // --- Comments (Algolia: the whole tree in one request) ---

    /**
     * Loads every comment on [story]. Top-level threads follow HN's ranking (the
     * order of [Story.kids]); replies within a thread are chronological.
     */
    suspend fun getCommentTree(story: Story): Result<List<CommentNode>> = runCatching {
        val item = algolia.getItem(story.id).bodyOrThrow()
        val rank = story.kids.orEmpty().withIndex().associate { (index, id) -> id to index }
        item.children.orEmpty()
            .map { it.toCommentNode() }
            .sortedBy { rank[it.id] ?: Int.MAX_VALUE }
    }

    private fun AlgoliaItem.toCommentNode(): CommentNode = CommentNode(
        id = id,
        by = author,
        time = createdAt,
        text = text,
        children = children.orEmpty().map { it.toCommentNode() }
    )

    // --- Search (Algolia) ---

    suspend fun search(
        query: String,
        type: SearchType,
        sort: SearchSort,
        timeRange: RelativeTimeRange,
        page: Int = 0
    ): Result<SearchPage> = runCatching {
        val tags = if (type == SearchType.STORIES) "story" else "comment"
        val numericFilters = if (timeRange == RelativeTimeRange.ALL_TIME) {
            null
        } else {
            val since = System.currentTimeMillis() / 1000 - timeRange.hours * 3600
            "created_at_i>$since"
        }
        val response = when (sort) {
            SearchSort.RELEVANCE -> algolia.search(query, tags, numericFilters, page)
            SearchSort.NEWEST -> algolia.searchByDate(query, tags, numericFilters, page)
        }
        response.bodyOrThrow().toSearchPage(type)
    }

    private fun AlgoliaSearchResponse.toSearchPage(type: SearchType): SearchPage {
        val hits = hits.orEmpty()
        return SearchPage(
            stories = if (type == SearchType.STORIES) hits.mapNotNull { it.toStory() } else emptyList(),
            comments = if (type == SearchType.COMMENTS) hits.mapNotNull { it.toCommentHit() } else emptyList(),
            page = page,
            hasMore = page + 1 < nbPages,
            totalHits = nbHits
        )
    }

    private fun AlgoliaHit.toStory(): Story? {
        val id = objectId.toLongOrNull() ?: return null
        return Story(
            id = id,
            title = title,
            url = url,
            text = storyText,
            by = author,
            time = createdAt,
            score = points ?: 0,
            descendants = numComments,
            kids = null,
            type = "story"
        )
    }

    private fun AlgoliaHit.toCommentHit(): CommentHit? {
        val id = objectId.toLongOrNull() ?: return null
        return CommentHit(
            id = id,
            by = author,
            time = createdAt,
            text = commentText,
            storyId = storyId,
            storyTitle = storyTitle,
            points = points
        )
    }

    // --- Users ---

    suspend fun getUser(username: String): Result<User> = runCatching {
        api.getUser(username).bodyOrThrow()
    }

    suspend fun getUserStories(username: String, page: Int = 0): Result<SearchPage> = runCatching {
        algolia.searchByDate(query = "", tags = "story,author_$username", page = page)
            .bodyOrThrow()
            .toSearchPage(SearchType.STORIES)
    }

    suspend fun getUserComments(username: String, page: Int = 0): Result<SearchPage> = runCatching {
        algolia.searchByDate(query = "", tags = "comment,author_$username", page = page)
            .bodyOrThrow()
            .toSearchPage(SearchType.COMMENTS)
    }

    // --- Bookmarks ---

    fun getBookmarkedStories(): Flow<List<Story>> =
        bookmarkDao.getAllBookmarks().map { bookmarks ->
            bookmarks.map { bookmark ->
                Story(
                    id = bookmark.storyId,
                    title = bookmark.title,
                    url = bookmark.url,
                    by = bookmark.by,
                    time = bookmark.time,
                    score = bookmark.score,
                    descendants = bookmark.descendants,
                    text = null,
                    kids = null,
                    type = "story"
                )
            }
        }

    fun getBookmarkedIds(): Flow<Set<Long>> = bookmarkDao.getBookmarkedIds().map { it.toSet() }

    suspend fun toggleBookmark(story: Story) {
        if (bookmarkDao.isBookmarked(story.id)) {
            bookmarkDao.deleteBookmarkById(story.id)
        } else {
            bookmarkDao.insertBookmark(
                BookmarkEntity(
                    storyId = story.id,
                    title = story.title.orEmpty(),
                    url = story.url,
                    by = story.by.orEmpty(),
                    time = story.time,
                    score = story.score,
                    descendants = story.descendants ?: 0
                )
            )
        }
    }

    suspend fun removeBookmark(storyId: Long) = bookmarkDao.deleteBookmarkById(storyId)
}

private fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) throw IllegalStateException("HTTP ${code()}")
    return body() ?: throw IllegalStateException("Not found")
}
