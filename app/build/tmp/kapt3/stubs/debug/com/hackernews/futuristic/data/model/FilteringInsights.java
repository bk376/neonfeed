package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\u0002\u0010\u0014J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\u00c6\u0003J\t\u0010\'\u001a\u00020\u0013H\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\t\u0010)\u001a\u00020\u0003H\u00c6\u0003J\t\u0010*\u001a\u00020\u0007H\u00c6\u0003J\t\u0010+\u001a\u00020\tH\u00c6\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003J\t\u0010-\u001a\u00020\u0007H\u00c6\u0003J\t\u0010.\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003J\u0089\u0001\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u00c6\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u00020\u0003H\u00d6\u0001J\t\u00105\u001a\u00020\fH\u00d6\u0001R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001aR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001fR\u0011\u0010\r\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018\u00a8\u00066"}, d2 = {"Lcom/hackernews/futuristic/data/model/FilteringInsights;", "", "totalStoriesProcessed", "", "storiesFilteredOut", "highEngagementStories", "filteringAccuracy", "", "averageReadingTime", "", "topFilteringReasons", "", "", "userSatisfactionScore", "modelPerformance", "recommendations", "trendsDetected", "Lcom/hackernews/futuristic/data/model/ContentTrend;", "performanceMetrics", "Lcom/hackernews/futuristic/data/model/FilteringMetrics;", "(IIIFJLjava/util/List;FFLjava/util/List;Ljava/util/List;Lcom/hackernews/futuristic/data/model/FilteringMetrics;)V", "getAverageReadingTime", "()J", "getFilteringAccuracy", "()F", "getHighEngagementStories", "()I", "getModelPerformance", "getPerformanceMetrics", "()Lcom/hackernews/futuristic/data/model/FilteringMetrics;", "getRecommendations", "()Ljava/util/List;", "getStoriesFilteredOut", "getTopFilteringReasons", "getTotalStoriesProcessed", "getTrendsDetected", "getUserSatisfactionScore", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class FilteringInsights {
    private final int totalStoriesProcessed = 0;
    private final int storiesFilteredOut = 0;
    private final int highEngagementStories = 0;
    private final float filteringAccuracy = 0.0F;
    private final long averageReadingTime = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> topFilteringReasons = null;
    private final float userSatisfactionScore = 0.0F;
    private final float modelPerformance = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> recommendations = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.ContentTrend> trendsDetected = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.FilteringMetrics performanceMetrics = null;
    
    public FilteringInsights(int totalStoriesProcessed, int storiesFilteredOut, int highEngagementStories, float filteringAccuracy, long averageReadingTime, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> topFilteringReasons, float userSatisfactionScore, float modelPerformance, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> recommendations, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.ContentTrend> trendsDetected, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FilteringMetrics performanceMetrics) {
        super();
    }
    
    public final int getTotalStoriesProcessed() {
        return 0;
    }
    
    public final int getStoriesFilteredOut() {
        return 0;
    }
    
    public final int getHighEngagementStories() {
        return 0;
    }
    
    public final float getFilteringAccuracy() {
        return 0.0F;
    }
    
    public final long getAverageReadingTime() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getTopFilteringReasons() {
        return null;
    }
    
    public final float getUserSatisfactionScore() {
        return 0.0F;
    }
    
    public final float getModelPerformance() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getRecommendations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.ContentTrend> getTrendsDetected() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringMetrics getPerformanceMetrics() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.ContentTrend> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringMetrics component11() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    public final long component5() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    public final float component7() {
        return 0.0F;
    }
    
    public final float component8() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringInsights copy(int totalStoriesProcessed, int storiesFilteredOut, int highEngagementStories, float filteringAccuracy, long averageReadingTime, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> topFilteringReasons, float userSatisfactionScore, float modelPerformance, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> recommendations, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.ContentTrend> trendsDetected, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FilteringMetrics performanceMetrics) {
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