package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\bH\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\n0\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\fH\u00c6\u0003J\t\u0010 \u001a\u00020\u000eH\u00c6\u0003JQ\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010\'\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006("}, d2 = {"Lcom/hackernews/futuristic/data/model/QuantumSearchQuery;", "", "query", "", "searchDimensions", "", "Lcom/hackernews/futuristic/data/model/SearchDimension;", "timeRange", "Lcom/hackernews/futuristic/data/model/TimeRange;", "quantumFilters", "Lcom/hackernews/futuristic/data/model/QuantumFilter;", "searchMode", "Lcom/hackernews/futuristic/data/model/SearchMode;", "probabilityThreshold", "", "(Ljava/lang/String;Ljava/util/List;Lcom/hackernews/futuristic/data/model/TimeRange;Ljava/util/List;Lcom/hackernews/futuristic/data/model/SearchMode;F)V", "getProbabilityThreshold", "()F", "getQuantumFilters", "()Ljava/util/List;", "getQuery", "()Ljava/lang/String;", "getSearchDimensions", "getSearchMode", "()Lcom/hackernews/futuristic/data/model/SearchMode;", "getTimeRange", "()Lcom/hackernews/futuristic/data/model/TimeRange;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_debug"})
public final class QuantumSearchQuery {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String query = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.SearchDimension> searchDimensions = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.TimeRange timeRange = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.QuantumFilter> quantumFilters = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.SearchMode searchMode = null;
    private final float probabilityThreshold = 0.0F;
    
    public QuantumSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.hackernews.futuristic.data.model.SearchDimension> searchDimensions, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.TimeRange timeRange, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.QuantumFilter> quantumFilters, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.SearchMode searchMode, float probabilityThreshold) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.SearchDimension> getSearchDimensions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TimeRange getTimeRange() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.QuantumFilter> getQuantumFilters() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.SearchMode getSearchMode() {
        return null;
    }
    
    public final float getProbabilityThreshold() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.SearchDimension> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TimeRange component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.QuantumFilter> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.SearchMode component5() {
        return null;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.QuantumSearchQuery copy(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.hackernews.futuristic.data.model.SearchDimension> searchDimensions, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.TimeRange timeRange, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.QuantumFilter> quantumFilters, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.SearchMode searchMode, float probabilityThreshold) {
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