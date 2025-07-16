package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0002\u0010\u0013J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0010H\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\bH\u00c6\u0003J\t\u0010.\u001a\u00020\bH\u00c6\u0003J\t\u0010/\u001a\u00020\u000bH\u00c6\u0003J\t\u00100\u001a\u00020\rH\u00c6\u0003J\u0010\u00101\u001a\u0004\u0018\u00010\rH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0019J\u0080\u0001\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00c6\u0001\u00a2\u0006\u0002\u00103J\u0013\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00107\u001a\u000208H\u00d6\u0001J\t\u00109\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0015\u00a8\u0006:"}, d2 = {"Lcom/hackernews/futuristic/data/model/FilteringExperiment;", "", "id", "", "name", "description", "hypothesis", "controlGroup", "Lcom/hackernews/futuristic/data/model/FilteringConfiguration;", "testGroup", "metrics", "Lcom/hackernews/futuristic/data/model/ExperimentMetrics;", "startTime", "", "endTime", "status", "Lcom/hackernews/futuristic/data/model/ExperimentStatus;", "results", "Lcom/hackernews/futuristic/data/model/ExperimentResults;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/hackernews/futuristic/data/model/FilteringConfiguration;Lcom/hackernews/futuristic/data/model/FilteringConfiguration;Lcom/hackernews/futuristic/data/model/ExperimentMetrics;JLjava/lang/Long;Lcom/hackernews/futuristic/data/model/ExperimentStatus;Lcom/hackernews/futuristic/data/model/ExperimentResults;)V", "getControlGroup", "()Lcom/hackernews/futuristic/data/model/FilteringConfiguration;", "getDescription", "()Ljava/lang/String;", "getEndTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getHypothesis", "getId", "getMetrics", "()Lcom/hackernews/futuristic/data/model/ExperimentMetrics;", "getName", "getResults", "()Lcom/hackernews/futuristic/data/model/ExperimentResults;", "getStartTime", "()J", "getStatus", "()Lcom/hackernews/futuristic/data/model/ExperimentStatus;", "getTestGroup", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/hackernews/futuristic/data/model/FilteringConfiguration;Lcom/hackernews/futuristic/data/model/FilteringConfiguration;Lcom/hackernews/futuristic/data/model/ExperimentMetrics;JLjava/lang/Long;Lcom/hackernews/futuristic/data/model/ExperimentStatus;Lcom/hackernews/futuristic/data/model/ExperimentResults;)Lcom/hackernews/futuristic/data/model/FilteringExperiment;", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class FilteringExperiment {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String hypothesis = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.FilteringConfiguration controlGroup = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.FilteringConfiguration testGroup = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ExperimentMetrics metrics = null;
    private final long startTime = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long endTime = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ExperimentStatus status = null;
    @org.jetbrains.annotations.Nullable()
    private final com.hackernews.futuristic.data.model.ExperimentResults results = null;
    
    public FilteringExperiment(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String hypothesis, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FilteringConfiguration controlGroup, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FilteringConfiguration testGroup, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ExperimentMetrics metrics, long startTime, @org.jetbrains.annotations.Nullable()
    java.lang.Long endTime, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ExperimentStatus status, @org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.ExperimentResults results) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getHypothesis() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringConfiguration getControlGroup() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringConfiguration getTestGroup() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ExperimentMetrics getMetrics() {
        return null;
    }
    
    public final long getStartTime() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getEndTime() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ExperimentStatus getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.model.ExperimentResults getResults() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ExperimentStatus component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.model.ExperimentResults component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringConfiguration component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringConfiguration component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ExperimentMetrics component7() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringExperiment copy(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String hypothesis, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FilteringConfiguration controlGroup, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FilteringConfiguration testGroup, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ExperimentMetrics metrics, long startTime, @org.jetbrains.annotations.Nullable()
    java.lang.Long endTime, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ExperimentStatus status, @org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.ExperimentResults results) {
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