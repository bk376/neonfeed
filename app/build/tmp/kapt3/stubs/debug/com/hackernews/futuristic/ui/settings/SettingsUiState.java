package com.hackernews.futuristic.ui.settings;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\f\u00a2\u0006\u0002\u0010\u0012J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\fH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\fH\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\fH\u00c6\u0003J\t\u0010(\u001a\u00020\u0003H\u00c6\u0003J\u008b\u0001\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\fH\u00c6\u0001J\u0013\u0010*\u001a\u00020\u00032\b\u0010+\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010,\u001a\u00020-H\u00d6\u0001J\t\u0010.\u001a\u00020\fH\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0019R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0019R\u0011\u0010\u0011\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u000e\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017\u00a8\u0006/"}, d2 = {"Lcom/hackernews/futuristic/ui/settings/SettingsUiState;", "", "isNeuralAnalysisEnabled", "", "analysisSensitivity", "", "isBiometricEnabled", "isCognitiveTrackingEnabled", "isHolographicEnabled", "hologramIntensity", "isPredictiveFilterEnabled", "filterAggressiveness", "", "isQuantumSearchEnabled", "searchMode", "isAREnabled", "arOpacity", "privacyMode", "(ZFZZZFZLjava/lang/String;ZLjava/lang/String;ZFLjava/lang/String;)V", "getAnalysisSensitivity", "()F", "getArOpacity", "getFilterAggressiveness", "()Ljava/lang/String;", "getHologramIntensity", "()Z", "getPrivacyMode", "getSearchMode", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class SettingsUiState {
    private final boolean isNeuralAnalysisEnabled = false;
    private final float analysisSensitivity = 0.0F;
    private final boolean isBiometricEnabled = false;
    private final boolean isCognitiveTrackingEnabled = false;
    private final boolean isHolographicEnabled = false;
    private final float hologramIntensity = 0.0F;
    private final boolean isPredictiveFilterEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String filterAggressiveness = null;
    private final boolean isQuantumSearchEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String searchMode = null;
    private final boolean isAREnabled = false;
    private final float arOpacity = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String privacyMode = null;
    
    public SettingsUiState(boolean isNeuralAnalysisEnabled, float analysisSensitivity, boolean isBiometricEnabled, boolean isCognitiveTrackingEnabled, boolean isHolographicEnabled, float hologramIntensity, boolean isPredictiveFilterEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String filterAggressiveness, boolean isQuantumSearchEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String searchMode, boolean isAREnabled, float arOpacity, @org.jetbrains.annotations.NotNull()
    java.lang.String privacyMode) {
        super();
    }
    
    public final boolean isNeuralAnalysisEnabled() {
        return false;
    }
    
    public final float getAnalysisSensitivity() {
        return 0.0F;
    }
    
    public final boolean isBiometricEnabled() {
        return false;
    }
    
    public final boolean isCognitiveTrackingEnabled() {
        return false;
    }
    
    public final boolean isHolographicEnabled() {
        return false;
    }
    
    public final float getHologramIntensity() {
        return 0.0F;
    }
    
    public final boolean isPredictiveFilterEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFilterAggressiveness() {
        return null;
    }
    
    public final boolean isQuantumSearchEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSearchMode() {
        return null;
    }
    
    public final boolean isAREnabled() {
        return false;
    }
    
    public final float getArOpacity() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPrivacyMode() {
        return null;
    }
    
    public SettingsUiState() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final float component12() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component13() {
        return null;
    }
    
    public final float component2() {
        return 0.0F;
    }
    
    public final boolean component3() {
        return false;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.ui.settings.SettingsUiState copy(boolean isNeuralAnalysisEnabled, float analysisSensitivity, boolean isBiometricEnabled, boolean isCognitiveTrackingEnabled, boolean isHolographicEnabled, float hologramIntensity, boolean isPredictiveFilterEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String filterAggressiveness, boolean isQuantumSearchEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String searchMode, boolean isAREnabled, float arOpacity, @org.jetbrains.annotations.NotNull()
    java.lang.String privacyMode) {
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