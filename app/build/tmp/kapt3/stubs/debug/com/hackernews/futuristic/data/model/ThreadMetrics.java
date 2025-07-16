package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f\u00a2\u0006\u0002\u0010\u0012J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0006H\u00c6\u0003J\t\u0010#\u001a\u00020\bH\u00c6\u0003J\t\u0010$\u001a\u00020\u0006H\u00c6\u0003J\t\u0010%\u001a\u00020\u0006H\u00c6\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u00c6\u0003J\u000f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u00c6\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00110\fH\u00c6\u0003Ju\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\fH\u00c6\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020\u0003H\u00d6\u0001J\t\u0010.\u001a\u00020/H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001e\u00a8\u00060"}, d2 = {"Lcom/hackernews/futuristic/data/model/ThreadMetrics;", "", "totalComments", "", "maxDepth", "branchingFactor", "", "averageResponseTime", "", "discussionVelocity", "consensusLevel", "controversyHotspots", "", "Lcom/hackernews/futuristic/data/model/CommentCluster;", "echoChambersDetected", "Lcom/hackernews/futuristic/data/model/EchoChamber;", "keyInfluencers", "Lcom/hackernews/futuristic/data/model/InfluentialCommenter;", "(IIFJFFLjava/util/List;Ljava/util/List;Ljava/util/List;)V", "getAverageResponseTime", "()J", "getBranchingFactor", "()F", "getConsensusLevel", "getControversyHotspots", "()Ljava/util/List;", "getDiscussionVelocity", "getEchoChambersDetected", "getKeyInfluencers", "getMaxDepth", "()I", "getTotalComments", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
public final class ThreadMetrics {
    private final int totalComments = 0;
    private final int maxDepth = 0;
    private final float branchingFactor = 0.0F;
    private final long averageResponseTime = 0L;
    private final float discussionVelocity = 0.0F;
    private final float consensusLevel = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.CommentCluster> controversyHotspots = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.EchoChamber> echoChambersDetected = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.InfluentialCommenter> keyInfluencers = null;
    
    public ThreadMetrics(int totalComments, int maxDepth, float branchingFactor, long averageResponseTime, float discussionVelocity, float consensusLevel, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.CommentCluster> controversyHotspots, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.EchoChamber> echoChambersDetected, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.InfluentialCommenter> keyInfluencers) {
        super();
    }
    
    public final int getTotalComments() {
        return 0;
    }
    
    public final int getMaxDepth() {
        return 0;
    }
    
    public final float getBranchingFactor() {
        return 0.0F;
    }
    
    public final long getAverageResponseTime() {
        return 0L;
    }
    
    public final float getDiscussionVelocity() {
        return 0.0F;
    }
    
    public final float getConsensusLevel() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.CommentCluster> getControversyHotspots() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.EchoChamber> getEchoChambersDetected() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.InfluentialCommenter> getKeyInfluencers() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final long component4() {
        return 0L;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.CommentCluster> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.EchoChamber> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.InfluentialCommenter> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ThreadMetrics copy(int totalComments, int maxDepth, float branchingFactor, long averageResponseTime, float discussionVelocity, float consensusLevel, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.CommentCluster> controversyHotspots, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.EchoChamber> echoChambersDetected, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.InfluentialCommenter> keyInfluencers) {
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