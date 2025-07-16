package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\t\u0010!\u001a\u00020\bH\u00c6\u0003J\t\u0010\"\u001a\u00020\nH\u00c6\u0003J\t\u0010#\u001a\u00020\fH\u00c6\u0003J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00c6\u0003JU\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u00c6\u0001J\u0013\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010)\u001a\u00020*H\u00d6\u0001J\t\u0010+\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d\u00a8\u0006,"}, d2 = {"Lcom/hackernews/futuristic/data/model/PersonalizationProfile;", "", "userId", "", "createdAt", "", "lastUpdated", "preferences", "Lcom/hackernews/futuristic/data/model/UserContentPreferences;", "behaviorPatterns", "Lcom/hackernews/futuristic/data/model/BehaviorPatterns;", "contextualFactors", "Lcom/hackernews/futuristic/data/model/ContextualFactors;", "adaptationHistory", "", "Lcom/hackernews/futuristic/data/model/AdaptationEvent;", "(Ljava/lang/String;JJLcom/hackernews/futuristic/data/model/UserContentPreferences;Lcom/hackernews/futuristic/data/model/BehaviorPatterns;Lcom/hackernews/futuristic/data/model/ContextualFactors;Ljava/util/List;)V", "getAdaptationHistory", "()Ljava/util/List;", "getBehaviorPatterns", "()Lcom/hackernews/futuristic/data/model/BehaviorPatterns;", "getContextualFactors", "()Lcom/hackernews/futuristic/data/model/ContextualFactors;", "getCreatedAt", "()J", "getLastUpdated", "getPreferences", "()Lcom/hackernews/futuristic/data/model/UserContentPreferences;", "getUserId", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class PersonalizationProfile {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = null;
    private final long createdAt = 0L;
    private final long lastUpdated = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.UserContentPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.BehaviorPatterns behaviorPatterns = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ContextualFactors contextualFactors = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.AdaptationEvent> adaptationHistory = null;
    
    public PersonalizationProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, long createdAt, long lastUpdated, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.UserContentPreferences preferences, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.BehaviorPatterns behaviorPatterns, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ContextualFactors contextualFactors, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.AdaptationEvent> adaptationHistory) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserId() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long getLastUpdated() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.UserContentPreferences getPreferences() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.BehaviorPatterns getBehaviorPatterns() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ContextualFactors getContextualFactors() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.AdaptationEvent> getAdaptationHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.UserContentPreferences component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.BehaviorPatterns component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ContextualFactors component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.AdaptationEvent> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.PersonalizationProfile copy(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, long createdAt, long lastUpdated, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.UserContentPreferences preferences, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.BehaviorPatterns behaviorPatterns, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ContextualFactors contextualFactors, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.AdaptationEvent> adaptationHistory) {
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