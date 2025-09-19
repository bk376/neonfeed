package com.hackernews.futuristic.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u0000 V2\u00020\u0001:\u0001VB\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013H\u0082@\u00a2\u0006\u0002\u0010\u0017J\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013H\u0082@\u00a2\u0006\u0002\u0010\u0017J\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013H\u0082@\u00a2\u0006\u0002\u0010\u0017J\"\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00132\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013H\u0082@\u00a2\u0006\u0002\u0010\u0017J,\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130\f2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010 J\"\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00130\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\"\u0010#J\u000e\u0010$\u001a\u00020\u001eH\u0086@\u00a2\u0006\u0002\u0010#J\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130&J*\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\f2\u0006\u0010(\u001a\u00020\u0014H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b)\u0010*J0\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b,\u0010\u0017J,\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130\f2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b.\u0010 J\"\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00130\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u0010#J0\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130\f2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b2\u0010\u0017J\\\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130\f2\u0006\u00104\u001a\u0002052\u0006\u0010\u001d\u001a\u00020\u001e2(\u00106\u001a$\b\u0001\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00130908\u0012\u0006\u0012\u0004\u0018\u00010\u000107H\u0082@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b:\u0010;J$\u0010<\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u0006\u0010=\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b>\u0010?J:\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00132\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bA\u0010BJ,\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130\f2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bD\u0010 J\"\u0010E\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00130\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bF\u0010#J$\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\f2\u0006\u0010I\u001a\u000205H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bJ\u0010KJ4\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00130\f2\u0006\u0010I\u001a\u0002052\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bM\u0010NJ4\u0010O\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00130\f2\u0006\u0010I\u001a\u0002052\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bP\u0010NJ\u0016\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010?J$\u0010T\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010S\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bU\u0010?R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006W"}, d2 = {"Lcom/hackernews/futuristic/data/repository/HackerNewsRepository;", "", "api", "Lcom/hackernews/futuristic/data/api/HackerNewsApi;", "storyDao", "Lcom/hackernews/futuristic/data/local/dao/StoryDao;", "commentDao", "Lcom/hackernews/futuristic/data/local/dao/CommentDao;", "bookmarkDao", "Lcom/hackernews/futuristic/data/local/dao/BookmarkDao;", "(Lcom/hackernews/futuristic/data/api/HackerNewsApi;Lcom/hackernews/futuristic/data/local/dao/StoryDao;Lcom/hackernews/futuristic/data/local/dao/CommentDao;Lcom/hackernews/futuristic/data/local/dao/BookmarkDao;)V", "bookmarkStory", "Lkotlin/Result;", "", "story", "Lcom/hackernews/futuristic/data/model/Story;", "bookmarkStory-gIAlu-s", "(Lcom/hackernews/futuristic/data/model/Story;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchCommentsFromApi", "", "Lcom/hackernews/futuristic/data/model/Comment;", "commentIds", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchCommentsRecursively", "fetchCommentsWithCache", "fetchStoriesFromApi", "storyIds", "getBestStories", "limit", "", "getBestStories-gIAlu-s", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBestStoryIds", "getBestStoryIds-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBookmarkCount", "getBookmarkedStories", "Lkotlinx/coroutines/flow/Flow;", "getChildComments", "parentComment", "getChildComments-gIAlu-s", "(Lcom/hackernews/futuristic/data/model/Comment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getComments", "getComments-gIAlu-s", "getNewStories", "getNewStories-gIAlu-s", "getNewStoryIds", "getNewStoryIds-IoAF18A", "getStoriesByIds", "getStoriesByIds-gIAlu-s", "getStoriesWithCache", "type", "", "apiCall", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lretrofit2/Response;", "getStoriesWithCache-BWLJW6A", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStory", "id", "getStory-gIAlu-s", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTopLevelComments", "getTopLevelComments-0E7RQCE", "(Ljava/util/List;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTopStories", "getTopStories-gIAlu-s", "getTopStoryIds", "getTopStoryIds-IoAF18A", "getUser", "Lcom/hackernews/futuristic/data/api/User;", "username", "getUser-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserComments", "getUserComments-0E7RQCE", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserSubmissions", "getUserSubmissions-0E7RQCE", "isBookmarked", "", "storyId", "removeBookmark", "removeBookmark-gIAlu-s", "Companion", "app_debug"})
public final class HackerNewsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.api.HackerNewsApi api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.local.dao.StoryDao storyDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.local.dao.CommentDao commentDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.local.dao.BookmarkDao bookmarkDao = null;
    private static final long CACHE_EXPIRY_TIME = 300000L;
    @org.jetbrains.annotations.NotNull()
    public static final com.hackernews.futuristic.data.repository.HackerNewsRepository.Companion Companion = null;
    
    @javax.inject.Inject()
    public HackerNewsRepository(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.api.HackerNewsApi api, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.local.dao.StoryDao storyDao, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.local.dao.CommentDao commentDao, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.local.dao.BookmarkDao bookmarkDao) {
        super();
    }
    
    private final java.lang.Object fetchCommentsWithCache(java.util.List<java.lang.Long> commentIds, kotlin.coroutines.Continuation<? super java.util.List<com.hackernews.futuristic.data.model.Comment>> $completion) {
        return null;
    }
    
    private final java.lang.Object fetchStoriesFromApi(java.util.List<java.lang.Long> storyIds, kotlin.coroutines.Continuation<? super java.util.List<com.hackernews.futuristic.data.model.Story>> $completion) {
        return null;
    }
    
    private final java.lang.Object fetchCommentsRecursively(java.util.List<java.lang.Long> commentIds, kotlin.coroutines.Continuation<? super java.util.List<com.hackernews.futuristic.data.model.Comment>> $completion) {
        return null;
    }
    
    private final java.lang.Object fetchCommentsFromApi(java.util.List<java.lang.Long> commentIds, kotlin.coroutines.Continuation<? super java.util.List<com.hackernews.futuristic.data.model.Comment>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isBookmarked(long storyId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.hackernews.futuristic.data.model.Story>> getBookmarkedStories() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getBookmarkCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/hackernews/futuristic/data/repository/HackerNewsRepository$Companion;", "", "()V", "CACHE_EXPIRY_TIME", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}