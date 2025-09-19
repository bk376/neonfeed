package com.hackernews.futuristic.ui.search;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001e\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u00020\u001d2\u0006\u00103\u001a\u00020\u0012H\u0002J\u0006\u00104\u001a\u000205J\u000e\u00106\u001a\u000207H\u0082@\u00a2\u0006\u0002\u00108J\u0010\u00109\u001a\u0002072\u0006\u00103\u001a\u00020\u0012H\u0002J\u0006\u0010:\u001a\u000205J$\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00142\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00142\u0006\u0010=\u001a\u00020\u0019H\u0002J\u0016\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u00103\u001a\u00020\u0012H\u0002J\u0016\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u00103\u001a\u00020\u0012H\u0002J\b\u0010@\u001a\u000205H\u0002J\u0010\u0010A\u001a\u0002052\u0006\u00103\u001a\u00020\u0012H\u0002J\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u00103\u001a\u00020\u0012H\u0002J\u0016\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00120\u00142\u0006\u00103\u001a\u00020\u0012H\u0002J\u000e\u0010D\u001a\u00020\f2\u0006\u0010E\u001a\u00020\nJ\b\u0010F\u001a\u000205H\u0002J\u000e\u0010G\u001a\u0002052\u0006\u0010H\u001a\u00020\u0015J\u0006\u0010I\u001a\u000205J\u000e\u0010J\u001a\u0002052\u0006\u0010K\u001a\u00020\u000eJ\u000e\u0010L\u001a\u0002052\u0006\u0010M\u001a\u00020\u0010J\u000e\u0010N\u001a\u0002052\u0006\u0010=\u001a\u00020\u0019J\u000e\u0010O\u001a\u0002052\u0006\u00102\u001a\u00020\u001dJ\u000e\u0010P\u001a\u0002052\u0006\u0010Q\u001a\u00020\u0017J\u0006\u0010R\u001a\u000205J\b\u0010S\u001a\u000205H\u0002J\u000e\u0010T\u001a\u0002052\u0006\u00103\u001a\u00020\u0012R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\f0\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010!R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00120\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010!R\u001d\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010!R\u001d\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00140\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010!R\u0017\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00190\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010!R\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001f\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010!\u00a8\u0006U"}, d2 = {"Lcom/hackernews/futuristic/ui/search/QuantumSearchViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/hackernews/futuristic/data/repository/HackerNewsRepository;", "quantumSearchEngine", "Lcom/hackernews/futuristic/data/analyzer/QuantumSearchEngine;", "(Lcom/hackernews/futuristic/data/repository/HackerNewsRepository;Lcom/hackernews/futuristic/data/analyzer/QuantumSearchEngine;)V", "_bookmarkedStories", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "_isRegularMode", "", "_probabilityThreshold", "", "_searchMode", "Lcom/hackernews/futuristic/data/model/SearchMode;", "_searchQuery", "", "_searchSuggestions", "", "Lcom/hackernews/futuristic/data/model/SearchSuggestion;", "_selectedDimensions", "Lcom/hackernews/futuristic/data/model/SearchDimension;", "_selectedTimeRange", "Lcom/hackernews/futuristic/data/model/RelativeTimeRange;", "_uiState", "Lcom/hackernews/futuristic/ui/search/QuantumSearchUiState;", "allStories", "Lcom/hackernews/futuristic/data/model/Story;", "bookmarkedStories", "Lkotlinx/coroutines/flow/StateFlow;", "getBookmarkedStories", "()Lkotlinx/coroutines/flow/StateFlow;", "isRegularMode", "probabilityThreshold", "getProbabilityThreshold", "searchMode", "getSearchMode", "searchQuery", "getSearchQuery", "searchSuggestions", "getSearchSuggestions", "selectedDimensions", "getSelectedDimensions", "selectedTimeRange", "getSelectedTimeRange", "uiState", "getUiState", "calculateSimpleRelevance", "story", "query", "clearSearch", "", "executeQuantumSearch", "Lcom/hackernews/futuristic/data/model/QuantumSearchResult;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "executeRegularSearch", "executeSearch", "filterStoriesByTimeRange", "stories", "timeRange", "generateAutoCompleteSuggestions", "generateCrossDimensionalSuggestions", "generateInitialSuggestions", "generateSearchSuggestions", "generateSemanticExpansions", "generateSimpleRelatedQueries", "isBookmarked", "storyId", "loadInitialData", "onSuggestionClick", "suggestion", "retrySearch", "setProbabilityThreshold", "threshold", "setSearchMode", "mode", "setTimeRange", "toggleBookmark", "toggleDimension", "dimension", "toggleSearchMode", "updateBookmarkedStories", "updateSearchQuery", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class QuantumSearchViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.repository.HackerNewsRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.analyzer.QuantumSearchEngine quantumSearchEngine = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hackernews.futuristic.ui.search.QuantumSearchUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.search.QuantumSearchUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hackernews.futuristic.data.model.SearchMode> _searchMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.data.model.SearchMode> searchMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.hackernews.futuristic.data.model.SearchDimension>> _selectedDimensions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hackernews.futuristic.data.model.SearchDimension>> selectedDimensions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Float> _probabilityThreshold = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Float> probabilityThreshold = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.hackernews.futuristic.data.model.SearchSuggestion>> _searchSuggestions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hackernews.futuristic.data.model.SearchSuggestion>> searchSuggestions = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isRegularMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRegularMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<java.lang.Long>> _bookmarkedStories = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.Long>> bookmarkedStories = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hackernews.futuristic.data.model.RelativeTimeRange> _selectedTimeRange = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.data.model.RelativeTimeRange> selectedTimeRange = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.hackernews.futuristic.data.model.Story> allStories;
    
    @javax.inject.Inject()
    public QuantumSearchViewModel(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.repository.HackerNewsRepository repository, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.analyzer.QuantumSearchEngine quantumSearchEngine) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.search.QuantumSearchUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.data.model.SearchMode> getSearchMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hackernews.futuristic.data.model.SearchDimension>> getSelectedDimensions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Float> getProbabilityThreshold() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hackernews.futuristic.data.model.SearchSuggestion>> getSearchSuggestions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRegularMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.Long>> getBookmarkedStories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.data.model.RelativeTimeRange> getSelectedTimeRange() {
        return null;
    }
    
    private final void loadInitialData() {
    }
    
    public final void updateSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void setSearchMode(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.SearchMode mode) {
    }
    
    public final void toggleDimension(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.SearchDimension dimension) {
    }
    
    public final void setProbabilityThreshold(float threshold) {
    }
    
    public final void toggleSearchMode() {
    }
    
    public final void executeSearch() {
    }
    
    private final java.lang.Object executeQuantumSearch(kotlin.coroutines.Continuation<? super com.hackernews.futuristic.data.model.QuantumSearchResult> $completion) {
        return null;
    }
    
    private final com.hackernews.futuristic.data.model.QuantumSearchResult executeRegularSearch(java.lang.String query) {
        return null;
    }
    
    private final float calculateSimpleRelevance(com.hackernews.futuristic.data.model.Story story, java.lang.String query) {
        return 0.0F;
    }
    
    private final java.util.List<java.lang.String> generateSimpleRelatedQueries(java.lang.String query) {
        return null;
    }
    
    private final void generateInitialSuggestions() {
    }
    
    private final void generateSearchSuggestions(java.lang.String query) {
    }
    
    private final java.util.List<com.hackernews.futuristic.data.model.SearchSuggestion> generateAutoCompleteSuggestions(java.lang.String query) {
        return null;
    }
    
    private final java.util.List<com.hackernews.futuristic.data.model.SearchSuggestion> generateSemanticExpansions(java.lang.String query) {
        return null;
    }
    
    private final java.util.List<com.hackernews.futuristic.data.model.SearchSuggestion> generateCrossDimensionalSuggestions(java.lang.String query) {
        return null;
    }
    
    public final void clearSearch() {
    }
    
    public final void retrySearch() {
    }
    
    public final void onSuggestionClick(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.SearchSuggestion suggestion) {
    }
    
    public final void toggleBookmark(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.Story story) {
    }
    
    public final boolean isBookmarked(long storyId) {
        return false;
    }
    
    private final void updateBookmarkedStories() {
    }
    
    public final void setTimeRange(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.RelativeTimeRange timeRange) {
    }
    
    private final java.util.List<com.hackernews.futuristic.data.model.Story> filterStoriesByTimeRange(java.util.List<com.hackernews.futuristic.data.model.Story> stories, com.hackernews.futuristic.data.model.RelativeTimeRange timeRange) {
        return null;
    }
}