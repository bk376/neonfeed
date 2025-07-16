package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\bH\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\rH\u00c6\u0003JK\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\rH\u00d6\u0001J\t\u0010%\u001a\u00020\u000bH\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012\u00a8\u0006&"}, d2 = {"Lcom/hackernews/futuristic/data/model/DiscussionPhase;", "", "startTime", "", "endTime", "phaseType", "Lcom/hackernews/futuristic/data/model/PhaseType;", "dominantSentiment", "Lcom/hackernews/futuristic/data/model/CommentEmotion;", "keyTopics", "", "", "participantCount", "", "(JJLcom/hackernews/futuristic/data/model/PhaseType;Lcom/hackernews/futuristic/data/model/CommentEmotion;Ljava/util/List;I)V", "getDominantSentiment", "()Lcom/hackernews/futuristic/data/model/CommentEmotion;", "getEndTime", "()J", "getKeyTopics", "()Ljava/util/List;", "getParticipantCount", "()I", "getPhaseType", "()Lcom/hackernews/futuristic/data/model/PhaseType;", "getStartTime", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class DiscussionPhase {
    private final long startTime = 0L;
    private final long endTime = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.PhaseType phaseType = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.CommentEmotion dominantSentiment = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> keyTopics = null;
    private final int participantCount = 0;
    
    public DiscussionPhase(long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.PhaseType phaseType, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentEmotion dominantSentiment, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> keyTopics, int participantCount) {
        super();
    }
    
    public final long getStartTime() {
        return 0L;
    }
    
    public final long getEndTime() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.PhaseType getPhaseType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentEmotion getDominantSentiment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getKeyTopics() {
        return null;
    }
    
    public final int getParticipantCount() {
        return 0;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.PhaseType component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.CommentEmotion component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.DiscussionPhase copy(long startTime, long endTime, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.PhaseType phaseType, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentEmotion dominantSentiment, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> keyTopics, int participantCount) {
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