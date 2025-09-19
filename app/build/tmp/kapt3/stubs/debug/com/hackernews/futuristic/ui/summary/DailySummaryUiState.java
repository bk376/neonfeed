package com.hackernews.futuristic.ui.summary;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\nH\u00c6\u0003JA\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0007H\u00d6\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001e"}, d2 = {"Lcom/hackernews/futuristic/ui/summary/DailySummaryUiState;", "", "isLoading", "", "summary", "Lcom/hackernews/futuristic/data/model/DailySummary;", "error", "", "isGeneratingPlan", "developmentPlan", "Lcom/hackernews/futuristic/data/service/AppDevelopmentPlan;", "(ZLcom/hackernews/futuristic/data/model/DailySummary;Ljava/lang/String;ZLcom/hackernews/futuristic/data/service/AppDevelopmentPlan;)V", "getDevelopmentPlan", "()Lcom/hackernews/futuristic/data/service/AppDevelopmentPlan;", "getError", "()Ljava/lang/String;", "()Z", "getSummary", "()Lcom/hackernews/futuristic/data/model/DailySummary;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class DailySummaryUiState {
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final com.hackernews.futuristic.data.model.DailySummary summary = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    private final boolean isGeneratingPlan = false;
    @org.jetbrains.annotations.Nullable()
    private final com.hackernews.futuristic.data.service.AppDevelopmentPlan developmentPlan = null;
    
    public DailySummaryUiState(boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.DailySummary summary, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean isGeneratingPlan, @org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.service.AppDevelopmentPlan developmentPlan) {
        super();
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.model.DailySummary getSummary() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public final boolean isGeneratingPlan() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.service.AppDevelopmentPlan getDevelopmentPlan() {
        return null;
    }
    
    public DailySummaryUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.model.DailySummary component2() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component3() {
        return null;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.hackernews.futuristic.data.service.AppDevelopmentPlan component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.ui.summary.DailySummaryUiState copy(boolean isLoading, @org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.model.DailySummary summary, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean isGeneratingPlan, @org.jetbrains.annotations.Nullable()
    com.hackernews.futuristic.data.service.AppDevelopmentPlan developmentPlan) {
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