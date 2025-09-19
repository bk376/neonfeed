package com.hackernews.futuristic.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0010\u001a\u00020\rJ\u0006\u0010\u0011\u001a\u00020\rJ\u0006\u0010\u0012\u001a\u00020\rJ\u0006\u0010\u0013\u001a\u00020\rJ\u0006\u0010\u0014\u001a\u00020\rJ\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u001aJ\u0010\u0010\u001d\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u000e\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u001aR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u001f"}, d2 = {"Lcom/hackernews/futuristic/ui/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lcom/hackernews/futuristic/data/repository/SettingsRepository;", "(Lcom/hackernews/futuristic/data/repository/SettingsRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hackernews/futuristic/ui/settings/SettingsUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearTrainingData", "", "toggleAR", "toggleBiometric", "toggleCognitiveTracking", "toggleHolographic", "toggleNeuralAnalysis", "togglePredictiveFilter", "toggleQuantumSearch", "updateAROpacity", "value", "", "updateAnalysisSensitivity", "updateFilterAggressiveness", "", "updateHologramIntensity", "updatePrivacyMode", "updateRepository", "updateSearchMode", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.repository.SettingsRepository settingsRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hackernews.futuristic.ui.settings.SettingsUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.settings.SettingsUiState> uiState = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.repository.SettingsRepository settingsRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.settings.SettingsUiState> getUiState() {
        return null;
    }
    
    public final void toggleNeuralAnalysis() {
    }
    
    public final void updateAnalysisSensitivity(float value) {
    }
    
    public final void toggleBiometric() {
    }
    
    public final void toggleCognitiveTracking() {
    }
    
    public final void toggleHolographic() {
    }
    
    public final void updateHologramIntensity(float value) {
    }
    
    public final void togglePredictiveFilter() {
    }
    
    public final void updateFilterAggressiveness(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void toggleQuantumSearch() {
    }
    
    public final void updateSearchMode(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void toggleAR() {
    }
    
    public final void updateAROpacity(float value) {
    }
    
    public final void updatePrivacyMode(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    public final void clearTrainingData() {
    }
    
    private final void updateRepository(com.hackernews.futuristic.ui.settings.SettingsUiState uiState) {
    }
}