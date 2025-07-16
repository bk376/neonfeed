package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BY\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0015\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u00c6\u0003J\u0015\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003H\u00c6\u0003J\u0015\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\rH\u00c6\u0003Ji\u0010\u001f\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020\u0004H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012\u00a8\u0006&"}, d2 = {"Lcom/hackernews/futuristic/data/model/BehaviorPatterns;", "", "readingTimes", "", "", "", "topicEngagement", "", "difficultyPreferences", "sessionPatterns", "Lcom/hackernews/futuristic/data/model/SessionPatterns;", "attentionSpan", "preferredContentLength", "Lkotlin/ranges/IntRange;", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Lcom/hackernews/futuristic/data/model/SessionPatterns;JLkotlin/ranges/IntRange;)V", "getAttentionSpan", "()J", "getDifficultyPreferences", "()Ljava/util/Map;", "getPreferredContentLength", "()Lkotlin/ranges/IntRange;", "getReadingTimes", "getSessionPatterns", "()Lcom/hackernews/futuristic/data/model/SessionPatterns;", "getTopicEngagement", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class BehaviorPatterns {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Long> readingTimes = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Float> topicEngagement = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, java.lang.Float> difficultyPreferences = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.SessionPatterns sessionPatterns = null;
    private final long attentionSpan = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.ranges.IntRange preferredContentLength = null;
    
    public BehaviorPatterns(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Long> readingTimes, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Float> topicEngagement, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Float> difficultyPreferences, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.SessionPatterns sessionPatterns, long attentionSpan, @org.jetbrains.annotations.NotNull()
    kotlin.ranges.IntRange preferredContentLength) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Long> getReadingTimes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Float> getTopicEngagement() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Float> getDifficultyPreferences() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.SessionPatterns getSessionPatterns() {
        return null;
    }
    
    public final long getAttentionSpan() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.ranges.IntRange getPreferredContentLength() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Long> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Float> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Float> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.SessionPatterns component4() {
        return null;
    }
    
    public final long component5() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.ranges.IntRange component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.BehaviorPatterns copy(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Long> readingTimes, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Float> topicEngagement, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.Float> difficultyPreferences, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.SessionPatterns sessionPatterns, long attentionSpan, @org.jetbrains.annotations.NotNull()
    kotlin.ranges.IntRange preferredContentLength) {
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