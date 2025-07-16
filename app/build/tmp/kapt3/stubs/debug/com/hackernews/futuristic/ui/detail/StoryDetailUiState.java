package com.hackernews.futuristic.ui.detail;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000fJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\nH\u00c6\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\rH\u00c6\u0003J]\u0010!\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\n2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\rH\u00d6\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0017R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\'"}, d2 = {"Lcom/hackernews/futuristic/ui/detail/StoryDetailUiState;", "", "story", "Lcom/hackernews/futuristic/data/model/Story;", "comments", "", "Lcom/hackernews/futuristic/data/model/Comment;", "commentThread", "Lcom/hackernews/futuristic/data/model/CommentThread;", "isLoading", "", "isLoadingComments", "error", "", "commentsError", "(Lcom/hackernews/futuristic/data/model/Story;Ljava/util/List;Lcom/hackernews/futuristic/data/model/CommentThread;ZZLjava/lang/String;Ljava/lang/String;)V", "getCommentThread", "()Lcom/hackernews/futuristic/data/model/CommentThread;", "getComments", "()Ljava/util/List;", "getCommentsError", "()Ljava/lang/String;", "getError", "()Z", "getStory", "()Lcom/hackernews/futuristic/data/model/Story;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class StoryDetailUiState {
    @org.jetbrains.annotations.Nullable()
    private final com.hackernews.futuristic.data.model.Story story = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.Comment> comments = null;
    @org.jetbrains.annotations.Nullable()
    private final com.hackernews.futuristic.data.model.CommentThread commentThread = null;
    private final boolean isLoading = false;
    private final boolean isLoadingComments = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String commentsError = null;
    
    public StoryDetailUiState(@org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.Story story, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.Comment> comments, @org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.CommentThread commentThread, boolean isLoading, boolean isLoadingComments, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.Nullable()
    java.lang.String commentsError) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.model.Story getStory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.Comment> getComments() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.model.CommentThread getCommentThread() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    public final boolean isLoadingComments() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCommentsError() {
        return null;
    }
    
    public StoryDetailUiState() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.model.Story component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.Comment> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.model.CommentThread component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.ui.detail.StoryDetailUiState copy(@org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.Story story, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.Comment> comments, @org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.CommentThread commentThread, boolean isLoading, boolean isLoadingComments, @org.jetbrains.annotations.Nullable()
    java.lang.String error, @org.jetbrains.annotations.Nullable()
    java.lang.String commentsError) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}