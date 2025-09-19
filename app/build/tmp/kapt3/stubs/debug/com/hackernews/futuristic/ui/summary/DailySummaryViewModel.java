package com.hackernews.futuristic.ui.summary;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J$\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001a0\u0017H\u0002J\u0006\u0010!\u001a\u00020\u0013J\u0016\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u001eH\u0082@\u00a2\u0006\u0002\u0010$J&\u0010%\u001a\u00020\u001c2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\'H\u0002J\u001c\u0010)\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0082@\u00a2\u0006\u0002\u0010*J\u001c\u0010+\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0082@\u00a2\u0006\u0002\u0010*J\u0006\u0010,\u001a\u00020\u0013R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006-"}, d2 = {"Lcom/hackernews/futuristic/ui/summary/DailySummaryViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/hackernews/futuristic/data/repository/HackerNewsRepository;", "neuralSynthesizer", "Lcom/hackernews/futuristic/data/analyzer/NeuralSynthesizer;", "cloudLLMService", "Lcom/hackernews/futuristic/data/service/CloudLLMService;", "geminiApiService", "Lcom/hackernews/futuristic/data/service/GeminiApiService;", "(Lcom/hackernews/futuristic/data/repository/HackerNewsRepository;Lcom/hackernews/futuristic/data/analyzer/NeuralSynthesizer;Lcom/hackernews/futuristic/data/service/CloudLLMService;Lcom/hackernews/futuristic/data/service/GeminiApiService;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hackernews/futuristic/ui/summary/DailySummaryUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "acceptBusinessOpportunity", "", "createIntelligentMockSummary", "Lcom/hackernews/futuristic/data/model/DailySummary;", "stories", "", "Lcom/hackernews/futuristic/data/model/Story;", "extractTrendingTopics", "Lcom/hackernews/futuristic/data/model/TrendingTopic;", "allTitles", "", "generateBusinessOpportunity", "Lcom/hackernews/futuristic/data/model/BusinessOpportunity;", "topStories", "trendingTopics", "generateDailySummary", "generateDetailedBusinessPlan", "opportunity", "(Lcom/hackernews/futuristic/data/model/BusinessOpportunity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateExecutiveSummary", "totalScore", "", "totalComments", "generateSummaryWithFallbacks", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateWithAlternativeAI", "shareSummary", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DailySummaryViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.repository.HackerNewsRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.analyzer.NeuralSynthesizer neuralSynthesizer = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.service.CloudLLMService cloudLLMService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.service.GeminiApiService geminiApiService = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hackernews.futuristic.ui.summary.DailySummaryUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.summary.DailySummaryUiState> uiState = null;
    
    @javax.inject.Inject()
    public DailySummaryViewModel(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.repository.HackerNewsRepository repository, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.analyzer.NeuralSynthesizer neuralSynthesizer, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.service.CloudLLMService cloudLLMService, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.service.GeminiApiService geminiApiService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.summary.DailySummaryUiState> getUiState() {
        return null;
    }
    
    public final void generateDailySummary() {
    }
    
    private final java.lang.Object generateSummaryWithFallbacks(java.util.List<com.hackernews.futuristic.data.model.Story> stories, kotlin.coroutines.Continuation<? super com.hackernews.futuristic.data.model.DailySummary> $completion) {
        return null;
    }
    
    private final java.lang.Object generateWithAlternativeAI(java.util.List<com.hackernews.futuristic.data.model.Story> stories, kotlin.coroutines.Continuation<? super com.hackernews.futuristic.data.model.DailySummary> $completion) {
        return null;
    }
    
    private final com.hackernews.futuristic.data.model.DailySummary createIntelligentMockSummary(java.util.List<com.hackernews.futuristic.data.model.Story> stories) {
        return null;
    }
    
    private final java.util.List<com.hackernews.futuristic.data.model.TrendingTopic> extractTrendingTopics(java.lang.String allTitles) {
        return null;
    }
    
    private final java.lang.String generateExecutiveSummary(java.util.List<com.hackernews.futuristic.data.model.Story> topStories, int totalScore, int totalComments) {
        return null;
    }
    
    private final com.hackernews.futuristic.data.model.BusinessOpportunity generateBusinessOpportunity(java.util.List<com.hackernews.futuristic.data.model.Story> topStories, java.util.List<com.hackernews.futuristic.data.model.TrendingTopic> trendingTopics) {
        return null;
    }
    
    public final void acceptBusinessOpportunity() {
    }
    
    private final java.lang.Object generateDetailedBusinessPlan(com.hackernews.futuristic.data.model.BusinessOpportunity opportunity, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void shareSummary() {
    }
}