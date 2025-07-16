package com.hackernews.futuristic.ui.summary;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0016\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@\u00a2\u0006\u0002\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\u000fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0016"}, d2 = {"Lcom/hackernews/futuristic/ui/summary/DailySummaryViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/hackernews/futuristic/data/repository/HackerNewsRepository;", "neuralSynthesizer", "Lcom/hackernews/futuristic/data/analyzer/NeuralSynthesizer;", "(Lcom/hackernews/futuristic/data/repository/HackerNewsRepository;Lcom/hackernews/futuristic/data/analyzer/NeuralSynthesizer;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hackernews/futuristic/ui/summary/DailySummaryUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "acceptBusinessOpportunity", "", "generateDailySummary", "generateDetailedBusinessPlan", "opportunity", "Lcom/hackernews/futuristic/data/model/BusinessOpportunity;", "(Lcom/hackernews/futuristic/data/model/BusinessOpportunity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shareSummary", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DailySummaryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.repository.HackerNewsRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.analyzer.NeuralSynthesizer neuralSynthesizer = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hackernews.futuristic.ui.summary.DailySummaryUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.summary.DailySummaryUiState> uiState = null;
    
    @javax.inject.Inject()
    public DailySummaryViewModel(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.repository.HackerNewsRepository repository, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.analyzer.NeuralSynthesizer neuralSynthesizer) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.summary.DailySummaryUiState> getUiState() {
        return null;
    }
    
    public final void generateDailySummary() {
    }
    
    public final void acceptBusinessOpportunity() {
    }
    
    private final java.lang.Object generateDetailedBusinessPlan(com.hackernews.futuristic.data.model.BusinessOpportunity opportunity, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void shareSummary() {
    }
}