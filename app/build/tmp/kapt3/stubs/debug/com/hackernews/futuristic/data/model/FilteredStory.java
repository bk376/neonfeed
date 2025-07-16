package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001f\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0005H\u00c6\u0003J\t\u0010#\u001a\u00020\u0007H\u00c6\u0003J\t\u0010$\u001a\u00020\tH\u00c6\u0003J\t\u0010%\u001a\u00020\u000bH\u00c6\u0003J\t\u0010&\u001a\u00020\rH\u00c6\u0003J\t\u0010\'\u001a\u00020\u000fH\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003JY\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010*\u001a\u00020\u000b2\b\u0010+\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010,\u001a\u00020\u000fH\u00d6\u0001J\t\u0010-\u001a\u00020\rH\u00d6\u0001R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 \u00a8\u0006."}, d2 = {"Lcom/hackernews/futuristic/data/model/FilteredStory;", "", "story", "Lcom/hackernews/futuristic/data/model/Story;", "relevanceScore", "", "predictions", "Lcom/hackernews/futuristic/data/model/ContentPrediction;", "filterResults", "Lcom/hackernews/futuristic/data/model/FilteringResult;", "shouldShow", "", "reason", "", "rank", "", "boostFactor", "(Lcom/hackernews/futuristic/data/model/Story;FLcom/hackernews/futuristic/data/model/ContentPrediction;Lcom/hackernews/futuristic/data/model/FilteringResult;ZLjava/lang/String;IF)V", "getBoostFactor", "()F", "getFilterResults", "()Lcom/hackernews/futuristic/data/model/FilteringResult;", "getPredictions", "()Lcom/hackernews/futuristic/data/model/ContentPrediction;", "getRank", "()I", "getReason", "()Ljava/lang/String;", "getRelevanceScore", "getShouldShow", "()Z", "getStory", "()Lcom/hackernews/futuristic/data/model/Story;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class FilteredStory {
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.Story story = null;
    private final float relevanceScore = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ContentPrediction predictions = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.FilteringResult filterResults = null;
    private final boolean shouldShow = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String reason = null;
    private final int rank = 0;
    private final float boostFactor = 0.0F;
    
    public FilteredStory(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.Story story, float relevanceScore, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ContentPrediction predictions, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FilteringResult filterResults, boolean shouldShow, @org.jetbrains.annotations.NotNull()
    java.lang.String reason, int rank, float boostFactor) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.Story getStory() {
        return null;
    }
    
    public final float getRelevanceScore() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ContentPrediction getPredictions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringResult getFilterResults() {
        return null;
    }
    
    public final boolean getShouldShow() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getReason() {
        return null;
    }
    
    public final int getRank() {
        return 0;
    }
    
    public final float getBoostFactor() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.Story component1() {
        return null;
    }
    
    public final float component2() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ContentPrediction component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringResult component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final float component8() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteredStory copy(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.Story story, float relevanceScore, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ContentPrediction predictions, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FilteringResult filterResults, boolean shouldShow, @org.jetbrains.annotations.NotNull()
    java.lang.String reason, int rank, float boostFactor) {
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