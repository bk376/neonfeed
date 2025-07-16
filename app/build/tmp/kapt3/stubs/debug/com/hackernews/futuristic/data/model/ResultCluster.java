package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u00a2\u0006\u0002\u0010\u000bJ\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0004H\u00c6\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00c6\u0003JM\u0010\u0018\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r\u00a8\u0006 "}, d2 = {"Lcom/hackernews/futuristic/data/model/ResultCluster;", "", "centroid", "", "", "stories", "Lcom/hackernews/futuristic/data/model/Story;", "clusterRadius", "coherence", "dominantDimensions", "Lcom/hackernews/futuristic/data/model/SearchDimension;", "(Ljava/util/List;Ljava/util/List;FFLjava/util/List;)V", "getCentroid", "()Ljava/util/List;", "getClusterRadius", "()F", "getCoherence", "getDominantDimensions", "getStories", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class ResultCluster {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Float> centroid = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.Story> stories = null;
    private final float clusterRadius = 0.0F;
    private final float coherence = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.SearchDimension> dominantDimensions = null;
    
    public ResultCluster(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Float> centroid, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.Story> stories, float clusterRadius, float coherence, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.hackernews.futuristic.data.model.SearchDimension> dominantDimensions) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Float> getCentroid() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.Story> getStories() {
        return null;
    }
    
    public final float getClusterRadius() {
        return 0.0F;
    }
    
    public final float getCoherence() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.SearchDimension> getDominantDimensions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Float> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.Story> component2() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.SearchDimension> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ResultCluster copy(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Float> centroid, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.Story> stories, float clusterRadius, float coherence, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.hackernews.futuristic.data.model.SearchDimension> dominantDimensions) {
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