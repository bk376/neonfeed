package com.hackernews.futuristic.data.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\b\b\u0001\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00032\b\b\u0001\u0010\n\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\b\b\u0001\u0010\n\u001a\u00020\u0015H\u00a7@\u00a2\u0006\u0002\u0010\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/hackernews/futuristic/data/api/HackerNewsApi;", "", "getAskStories", "Lretrofit2/Response;", "", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBestStories", "getComment", "Lcom/hackernews/futuristic/data/model/Comment;", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItem", "Lcom/hackernews/futuristic/data/model/Story;", "getJobStories", "getMaxItemId", "getNewStories", "getShowStories", "getTopStories", "getUser", "Lcom/hackernews/futuristic/data/api/User;", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface HackerNewsApi {
    
    @retrofit2.http.GET(value = "topstories.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTopStories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<java.lang.Long>>> $completion);
    
    @retrofit2.http.GET(value = "newstories.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getNewStories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<java.lang.Long>>> $completion);
    
    @retrofit2.http.GET(value = "beststories.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getBestStories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<java.lang.Long>>> $completion);
    
    @retrofit2.http.GET(value = "askstories.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAskStories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<java.lang.Long>>> $completion);
    
    @retrofit2.http.GET(value = "showstories.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getShowStories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<java.lang.Long>>> $completion);
    
    @retrofit2.http.GET(value = "jobstories.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getJobStories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<java.lang.Long>>> $completion);
    
    @retrofit2.http.GET(value = "item/{id}.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getItem(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.hackernews.futuristic.data.model.Story>> $completion);
    
    @retrofit2.http.GET(value = "item/{id}.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getComment(@retrofit2.http.Path(value = "id")
    long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.hackernews.futuristic.data.model.Comment>> $completion);
    
    @retrofit2.http.GET(value = "user/{id}.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUser(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.hackernews.futuristic.data.api.User>> $completion);
    
    @retrofit2.http.GET(value = "maxitem.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMaxItemId(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.lang.Long>> $completion);
}