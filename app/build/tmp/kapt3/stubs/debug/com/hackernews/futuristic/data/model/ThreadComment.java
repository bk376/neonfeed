package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\'\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\u0002\u0010\u0017J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0012H\u00c6\u0003J\t\u00101\u001a\u00020\u0014H\u00c6\u0003J\t\u00102\u001a\u00020\u0016H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0011\u00106\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\tH\u00c6\u0003J\u0010\u00107\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010%J\t\u00108\u001a\u00020\fH\u00c6\u0003J\t\u00109\u001a\u00020\u000eH\u00c6\u0003J\t\u0010:\u001a\u00020\u0010H\u00c6\u0003J\u0094\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u00c6\u0001\u00a2\u0006\u0002\u0010<J\u0013\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010@\u001a\u00020\fH\u00d6\u0001J\t\u0010A\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0015\u0010\n\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001bR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001fR\u0011\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.\u00a8\u0006B"}, d2 = {"Lcom/hackernews/futuristic/data/model/ThreadComment;", "", "id", "", "by", "", "time", "text", "kids", "", "parent", "depth", "", "threadPosition", "Lcom/hackernews/futuristic/data/model/ThreadPosition;", "sentiment", "Lcom/hackernews/futuristic/data/model/CommentSentiment;", "influence", "Lcom/hackernews/futuristic/data/model/CommentInfluence;", "branchType", "Lcom/hackernews/futuristic/data/model/BranchType;", "visualProperties", "Lcom/hackernews/futuristic/data/model/CommentVisualProperties;", "(JLjava/lang/String;JLjava/lang/String;Ljava/util/List;Ljava/lang/Long;ILcom/hackernews/futuristic/data/model/ThreadPosition;Lcom/hackernews/futuristic/data/model/CommentSentiment;Lcom/hackernews/futuristic/data/model/CommentInfluence;Lcom/hackernews/futuristic/data/model/BranchType;Lcom/hackernews/futuristic/data/model/CommentVisualProperties;)V", "getBranchType", "()Lcom/hackernews/futuristic/data/model/BranchType;", "getBy", "()Ljava/lang/String;", "getDepth", "()I", "getId", "()J", "getInfluence", "()Lcom/hackernews/futuristic/data/model/CommentInfluence;", "getKids", "()Ljava/util/List;", "getParent", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSentiment", "()Lcom/hackernews/futuristic/data/model/CommentSentiment;", "getText", "getThreadPosition", "()Lcom/hackernews/futuristic/data/model/ThreadPosition;", "getTime", "getVisualProperties", "()Lcom/hackernews/futuristic/data/model/CommentVisualProperties;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;JLjava/lang/String;Ljava/util/List;Ljava/lang/Long;ILcom/hackernews/futuristic/data/model/ThreadPosition;Lcom/hackernews/futuristic/data/model/CommentSentiment;Lcom/hackernews/futuristic/data/model/CommentInfluence;Lcom/hackernews/futuristic/data/model/BranchType;Lcom/hackernews/futuristic/data/model/CommentVisualProperties;)Lcom/hackernews/futuristic/data/model/ThreadComment;", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class ThreadComment {
    private final long id = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String by = null;
    private final long time = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String text = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.List<java.lang.Long> kids = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long parent = null;
    private final int depth = 0;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ThreadPosition threadPosition = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.CommentSentiment sentiment = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.CommentInfluence influence = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.BranchType branchType = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.CommentVisualProperties visualProperties = null;
    
    public ThreadComment(long id, @org.jetbrains.annotations.Nullable()
    java.lang.String by, long time, @org.jetbrains.annotations.Nullable()
    java.lang.String text, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Long> kids, @org.jetbrains.annotations.Nullable()
    java.lang.Long parent, int depth, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ThreadPosition threadPosition, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentSentiment sentiment, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentInfluence influence, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.BranchType branchType, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentVisualProperties visualProperties) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBy() {
        return null;
    }
    
    public final long getTime() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getText() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.Long> getKids() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getParent() {
        return null;
    }
    
    public final int getDepth() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ThreadPosition getThreadPosition() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentSentiment getSentiment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentInfluence getInfluence() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.BranchType getBranchType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentVisualProperties getVisualProperties() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentInfluence component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.BranchType component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentVisualProperties component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<java.lang.Long> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component6() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ThreadPosition component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentSentiment component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ThreadComment copy(long id, @org.jetbrains.annotations.Nullable()
    java.lang.String by, long time, @org.jetbrains.annotations.Nullable()
    java.lang.String text, @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Long> kids, @org.jetbrains.annotations.Nullable()
    java.lang.Long parent, int depth, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ThreadPosition threadPosition, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentSentiment sentiment, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentInfluence influence, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.BranchType branchType, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentVisualProperties visualProperties) {
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