package com.hackernews.futuristic.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 =2\u00020\u0001:\u0001=B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0082@\u00a2\u0006\u0002\u0010\u000eJ\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0082@\u00a2\u0006\u0002\u0010\u000eJ\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0082@\u00a2\u0006\u0002\u0010\u000eJ\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0082@\u00a2\u0006\u0002\u0010\u000eJ,\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\n0\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019J\"\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\n0\u0015H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001b\u0010\u001cJ*\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00152\u0006\u0010\u001e\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001f\u0010 J0\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00152\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\"\u0010\u000eJ,\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\n0\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b$\u0010\u0019J\"\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\n0\u0015H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b&\u0010\u001cJ0\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\n0\u00152\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b(\u0010\u000eJ\\\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\n0\u00152\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0016\u001a\u00020\u00172(\u0010,\u001a$\b\u0001\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\n0/0.\u0012\u0006\u0012\u0004\u0018\u00010\u00010-H\u0082@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b0\u00101J$\u00102\u001a\b\u0012\u0004\u0012\u00020\u00120\u00152\u0006\u00103\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b4\u00105J:\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u00152\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b7\u00108J,\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\n0\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b:\u0010\u0019J\"\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\n0\u0015H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b<\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006>"}, d2 = {"Lcom/hackernews/futuristic/data/repository/HackerNewsRepository;", "", "api", "Lcom/hackernews/futuristic/data/api/HackerNewsApi;", "storyDao", "Lcom/hackernews/futuristic/data/local/dao/StoryDao;", "commentDao", "Lcom/hackernews/futuristic/data/local/dao/CommentDao;", "(Lcom/hackernews/futuristic/data/api/HackerNewsApi;Lcom/hackernews/futuristic/data/local/dao/StoryDao;Lcom/hackernews/futuristic/data/local/dao/CommentDao;)V", "fetchCommentsFromApi", "", "Lcom/hackernews/futuristic/data/model/Comment;", "commentIds", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchCommentsRecursively", "fetchCommentsWithCache", "fetchStoriesFromApi", "Lcom/hackernews/futuristic/data/model/Story;", "storyIds", "getBestStories", "Lkotlin/Result;", "limit", "", "getBestStories-gIAlu-s", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBestStoryIds", "getBestStoryIds-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChildComments", "parentComment", "getChildComments-gIAlu-s", "(Lcom/hackernews/futuristic/data/model/Comment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getComments", "getComments-gIAlu-s", "getNewStories", "getNewStories-gIAlu-s", "getNewStoryIds", "getNewStoryIds-IoAF18A", "getStoriesByIds", "getStoriesByIds-gIAlu-s", "getStoriesWithCache", "type", "", "apiCall", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "Lretrofit2/Response;", "getStoriesWithCache-BWLJW6A", "(Ljava/lang/String;ILkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStory", "id", "getStory-gIAlu-s", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTopLevelComments", "getTopLevelComments-0E7RQCE", "(Ljava/util/List;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTopStories", "getTopStories-gIAlu-s", "getTopStoryIds", "getTopStoryIds-IoAF18A", "Companion", "app_debug"})
public final class HackerNewsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.api.HackerNewsApi api = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.local.dao.StoryDao storyDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.local.dao.CommentDao commentDao = null;
    private static final long CACHE_EXPIRY_TIME = 300000L;
    @org.jetbrains.annotations.NotNull()
    public static final com.hackernews.futuristic.data.repository.HackerNewsRepository.Companion Companion = null;
    
    @javax.inject.Inject()
    public HackerNewsRepository(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.api.HackerNewsApi api, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.local.dao.StoryDao storyDao, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.local.dao.CommentDao commentDao) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/hackernews/futuristic/data/repository/HackerNewsRepository$Companion;", "", "()V", "CACHE_EXPIRY_TIME", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}