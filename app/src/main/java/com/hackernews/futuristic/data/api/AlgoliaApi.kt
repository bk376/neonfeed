package com.hackernews.futuristic.data.api

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * HN Search API (https://hn.algolia.com/api). Full-text search over all of HN,
 * plus whole comment trees in a single request.
 */
interface AlgoliaApi {
    /** Ranked by relevance, with points and comment count as tie-breakers. */
    @GET("search")
    suspend fun search(
        @Query("query") query: String,
        @Query("tags") tags: String,
        @Query("numericFilters") numericFilters: String? = null,
        @Query("page") page: Int = 0,
        @Query("hitsPerPage") hitsPerPage: Int = 30
    ): Response<AlgoliaSearchResponse>

    /** Newest first. */
    @GET("search_by_date")
    suspend fun searchByDate(
        @Query("query") query: String,
        @Query("tags") tags: String,
        @Query("numericFilters") numericFilters: String? = null,
        @Query("page") page: Int = 0,
        @Query("hitsPerPage") hitsPerPage: Int = 30
    ): Response<AlgoliaSearchResponse>

    @GET("items/{id}")
    suspend fun getItem(@Path("id") id: Long): Response<AlgoliaItem>
}

data class AlgoliaSearchResponse(
    val hits: List<AlgoliaHit>?,
    val page: Int,
    val nbPages: Int,
    val nbHits: Int
)

data class AlgoliaHit(
    @SerializedName("objectID") val objectId: String,
    val title: String?,
    val url: String?,
    val author: String?,
    val points: Int?,
    @SerializedName("story_text") val storyText: String?,
    @SerializedName("comment_text") val commentText: String?,
    @SerializedName("num_comments") val numComments: Int?,
    @SerializedName("story_id") val storyId: Long?,
    @SerializedName("story_title") val storyTitle: String?,
    @SerializedName("created_at_i") val createdAt: Long
)

data class AlgoliaItem(
    val id: Long,
    val type: String?,
    val author: String?,
    val title: String?,
    val url: String?,
    val text: String?,
    val points: Int?,
    @SerializedName("created_at_i") val createdAt: Long,
    val children: List<AlgoliaItem>?
)
