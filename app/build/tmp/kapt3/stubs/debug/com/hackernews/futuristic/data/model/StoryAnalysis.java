package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0012J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0005H\u00c6\u0003J\t\u0010$\u001a\u00020\u0007H\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00c6\u0003J\t\u0010)\u001a\u00020\u0010H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003Ji\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010/\u001a\u000200H\u00d6\u0001J\t\u00101\u001a\u00020\u000eH\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!\u00a8\u00062"}, d2 = {"Lcom/hackernews/futuristic/data/model/StoryAnalysis;", "", "storyId", "", "sentimentScore", "", "emotionProfile", "Lcom/hackernews/futuristic/data/model/EmotionProfile;", "topicClassification", "Lcom/hackernews/futuristic/data/model/TopicClassification;", "controversyLevel", "relevanceScore", "keywords", "", "", "threatLevel", "Lcom/hackernews/futuristic/data/model/ThreatLevel;", "processingTimestamp", "(JFLcom/hackernews/futuristic/data/model/EmotionProfile;Lcom/hackernews/futuristic/data/model/TopicClassification;FFLjava/util/List;Lcom/hackernews/futuristic/data/model/ThreatLevel;J)V", "getControversyLevel", "()F", "getEmotionProfile", "()Lcom/hackernews/futuristic/data/model/EmotionProfile;", "getKeywords", "()Ljava/util/List;", "getProcessingTimestamp", "()J", "getRelevanceScore", "getSentimentScore", "getStoryId", "getThreatLevel", "()Lcom/hackernews/futuristic/data/model/ThreatLevel;", "getTopicClassification", "()Lcom/hackernews/futuristic/data/model/TopicClassification;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class StoryAnalysis {
    private final long storyId = 0L;
    private final float sentimentScore = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.EmotionProfile emotionProfile = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.TopicClassification topicClassification = null;
    private final float controversyLevel = 0.0F;
    private final float relevanceScore = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> keywords = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ThreatLevel threatLevel = null;
    private final long processingTimestamp = 0L;
    
    public StoryAnalysis(long storyId, float sentimentScore, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.EmotionProfile emotionProfile, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.TopicClassification topicClassification, float controversyLevel, float relevanceScore, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> keywords, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ThreatLevel threatLevel, long processingTimestamp) {
        super();
    }
    
    public final long getStoryId() {
        return 0L;
    }
    
    public final float getSentimentScore() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.EmotionProfile getEmotionProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TopicClassification getTopicClassification() {
        return null;
    }
    
    public final float getControversyLevel() {
        return 0.0F;
    }
    
    public final float getRelevanceScore() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getKeywords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ThreatLevel getThreatLevel() {
        return null;
    }
    
    public final long getProcessingTimestamp() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final float component2() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.EmotionProfile component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TopicClassification component4() {
        return null;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ThreatLevel component8() {
        return null;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.StoryAnalysis copy(long storyId, float sentimentScore, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.EmotionProfile emotionProfile, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.TopicClassification topicClassification, float controversyLevel, float relevanceScore, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> keywords, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ThreatLevel threatLevel, long processingTimestamp) {
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