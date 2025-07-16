package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003\u00a2\u0006\u0002\u0010\rJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u0003H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H\u00c6\u0003JS\u0010\u001a\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H\u00c6\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020!H\u00d6\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f\u00a8\u0006\""}, d2 = {"Lcom/hackernews/futuristic/data/model/DiscussionFlow;", "", "timeline", "", "Lcom/hackernews/futuristic/data/model/DiscussionEvent;", "peakActivity", "", "discussionPhases", "Lcom/hackernews/futuristic/data/model/DiscussionPhase;", "topicEvolution", "Lcom/hackernews/futuristic/data/model/TopicShift;", "participantFlow", "Lcom/hackernews/futuristic/data/model/ParticipantActivity;", "(Ljava/util/List;JLjava/util/List;Ljava/util/List;Ljava/util/List;)V", "getDiscussionPhases", "()Ljava/util/List;", "getParticipantFlow", "getPeakActivity", "()J", "getTimeline", "getTopicEvolution", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class DiscussionFlow {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.DiscussionEvent> timeline = null;
    private final long peakActivity = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.DiscussionPhase> discussionPhases = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.TopicShift> topicEvolution = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.ParticipantActivity> participantFlow = null;
    
    public DiscussionFlow(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.DiscussionEvent> timeline, long peakActivity, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.DiscussionPhase> discussionPhases, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.TopicShift> topicEvolution, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.ParticipantActivity> participantFlow) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.DiscussionEvent> getTimeline() {
        return null;
    }
    
    public final long getPeakActivity() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.DiscussionPhase> getDiscussionPhases() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.TopicShift> getTopicEvolution() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.ParticipantActivity> getParticipantFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.DiscussionEvent> component1() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.DiscussionPhase> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.TopicShift> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.ParticipantActivity> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.DiscussionFlow copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.DiscussionEvent> timeline, long peakActivity, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.DiscussionPhase> discussionPhases, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.TopicShift> topicEvolution, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.ParticipantActivity> participantFlow) {
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