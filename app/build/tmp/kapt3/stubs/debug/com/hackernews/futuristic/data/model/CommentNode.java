package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005H\u00c6\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0000H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003JI\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00002\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010!\u001a\u00020\"H\u00d6\u0001J\t\u0010#\u001a\u00020$H\u00d6\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0000X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006%"}, d2 = {"Lcom/hackernews/futuristic/data/model/CommentNode;", "", "comment", "Lcom/hackernews/futuristic/data/model/ThreadComment;", "children", "", "parent", "visualBounds", "Lcom/hackernews/futuristic/data/model/NodeBounds;", "connections", "", "Lcom/hackernews/futuristic/data/model/NodeConnection;", "(Lcom/hackernews/futuristic/data/model/ThreadComment;Ljava/util/List;Lcom/hackernews/futuristic/data/model/CommentNode;Lcom/hackernews/futuristic/data/model/NodeBounds;Ljava/util/List;)V", "getChildren", "()Ljava/util/List;", "getComment", "()Lcom/hackernews/futuristic/data/model/ThreadComment;", "getConnections", "getParent", "()Lcom/hackernews/futuristic/data/model/CommentNode;", "setParent", "(Lcom/hackernews/futuristic/data/model/CommentNode;)V", "getVisualBounds", "()Lcom/hackernews/futuristic/data/model/NodeBounds;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class CommentNode {
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ThreadComment comment = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.CommentNode> children = null;
    @org.jetbrains.annotations.Nullable()
    private com.hackernews.futuristic.data.model.CommentNode parent;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.NodeBounds visualBounds = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.NodeConnection> connections = null;
    
    public CommentNode(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ThreadComment comment, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.CommentNode> children, @org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.CommentNode parent, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.NodeBounds visualBounds, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.NodeConnection> connections) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ThreadComment getComment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.CommentNode> getChildren() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.model.CommentNode getParent() {
        return null;
    }
    
    public final void setParent(@org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.CommentNode p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.NodeBounds getVisualBounds() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.NodeConnection> getConnections() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ThreadComment component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.CommentNode> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.model.CommentNode component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.NodeBounds component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.NodeConnection> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentNode copy(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ThreadComment comment, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.CommentNode> children, @org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.CommentNode parent, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.NodeBounds visualBounds, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.NodeConnection> connections) {
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