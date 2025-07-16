package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\nH\u00c6\u0003J\t\u0010 \u001a\u00020\fH\u00c6\u0003J\t\u0010!\u001a\u00020\u000eH\u00c6\u0003JK\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\'H\u00d6\u0001J\t\u0010(\u001a\u00020)H\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006*"}, d2 = {"Lcom/hackernews/futuristic/data/model/CommentThread;", "", "rootCommentId", "", "comments", "", "Lcom/hackernews/futuristic/data/model/ThreadComment;", "threadMetrics", "Lcom/hackernews/futuristic/data/model/ThreadMetrics;", "visualTree", "Lcom/hackernews/futuristic/data/model/CommentTree;", "flowDirection", "Lcom/hackernews/futuristic/data/model/FlowDirection;", "branchingPattern", "Lcom/hackernews/futuristic/data/model/BranchingPattern;", "(JLjava/util/List;Lcom/hackernews/futuristic/data/model/ThreadMetrics;Lcom/hackernews/futuristic/data/model/CommentTree;Lcom/hackernews/futuristic/data/model/FlowDirection;Lcom/hackernews/futuristic/data/model/BranchingPattern;)V", "getBranchingPattern", "()Lcom/hackernews/futuristic/data/model/BranchingPattern;", "getComments", "()Ljava/util/List;", "getFlowDirection", "()Lcom/hackernews/futuristic/data/model/FlowDirection;", "getRootCommentId", "()J", "getThreadMetrics", "()Lcom/hackernews/futuristic/data/model/ThreadMetrics;", "getVisualTree", "()Lcom/hackernews/futuristic/data/model/CommentTree;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class CommentThread {
    private final long rootCommentId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.ThreadComment> comments = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ThreadMetrics threadMetrics = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.CommentTree visualTree = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.FlowDirection flowDirection = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.BranchingPattern branchingPattern = null;
    
    public CommentThread(long rootCommentId, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.ThreadComment> comments, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ThreadMetrics threadMetrics, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentTree visualTree, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FlowDirection flowDirection, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.BranchingPattern branchingPattern) {
        super();
    }
    
    public final long getRootCommentId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.ThreadComment> getComments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ThreadMetrics getThreadMetrics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentTree getVisualTree() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FlowDirection getFlowDirection() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.BranchingPattern getBranchingPattern() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.ThreadComment> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ThreadMetrics component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentTree component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FlowDirection component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.BranchingPattern component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentThread copy(long rootCommentId, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.ThreadComment> comments, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ThreadMetrics threadMetrics, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentTree visualTree, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FlowDirection flowDirection, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.BranchingPattern branchingPattern) {
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