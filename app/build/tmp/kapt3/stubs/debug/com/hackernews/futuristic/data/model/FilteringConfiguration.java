package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\nH\u00c6\u0003J\t\u0010!\u001a\u00020\fH\u00c6\u0003J\t\u0010\"\u001a\u00020\u000eH\u00c6\u0003JO\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010$\u001a\u00020\u00032\b\u0010%\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010&\u001a\u00020\'H\u00d6\u0001J\t\u0010(\u001a\u00020)H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006*"}, d2 = {"Lcom/hackernews/futuristic/data/model/FilteringConfiguration;", "", "isEnabled", "", "aggressiveness", "Lcom/hackernews/futuristic/data/model/FilterAggressiveness;", "learningRate", "", "adaptationEnabled", "explainabilityLevel", "Lcom/hackernews/futuristic/data/model/ExplainabilityLevel;", "privacyMode", "Lcom/hackernews/futuristic/data/model/PrivacyMode;", "updateFrequency", "Lcom/hackernews/futuristic/data/model/UpdateFrequency;", "(ZLcom/hackernews/futuristic/data/model/FilterAggressiveness;FZLcom/hackernews/futuristic/data/model/ExplainabilityLevel;Lcom/hackernews/futuristic/data/model/PrivacyMode;Lcom/hackernews/futuristic/data/model/UpdateFrequency;)V", "getAdaptationEnabled", "()Z", "getAggressiveness", "()Lcom/hackernews/futuristic/data/model/FilterAggressiveness;", "getExplainabilityLevel", "()Lcom/hackernews/futuristic/data/model/ExplainabilityLevel;", "getLearningRate", "()F", "getPrivacyMode", "()Lcom/hackernews/futuristic/data/model/PrivacyMode;", "getUpdateFrequency", "()Lcom/hackernews/futuristic/data/model/UpdateFrequency;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
public final class FilteringConfiguration {
    private final boolean isEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.FilterAggressiveness aggressiveness = null;
    private final float learningRate = 0.0F;
    private final boolean adaptationEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ExplainabilityLevel explainabilityLevel = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.PrivacyMode privacyMode = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.UpdateFrequency updateFrequency = null;
    
    public FilteringConfiguration(boolean isEnabled, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FilterAggressiveness aggressiveness, float learningRate, boolean adaptationEnabled, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ExplainabilityLevel explainabilityLevel, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.PrivacyMode privacyMode, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.UpdateFrequency updateFrequency) {
        super();
    }
    
    public final boolean isEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilterAggressiveness getAggressiveness() {
        return null;
    }
    
    public final float getLearningRate() {
        return 0.0F;
    }
    
    public final boolean getAdaptationEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ExplainabilityLevel getExplainabilityLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.PrivacyMode getPrivacyMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.UpdateFrequency getUpdateFrequency() {
        return null;
    }
    
    public FilteringConfiguration() {
        super();
    }
    
    public final boolean component1() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilterAggressiveness component2() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ExplainabilityLevel component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.PrivacyMode component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.UpdateFrequency component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.FilteringConfiguration copy(boolean isEnabled, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.FilterAggressiveness aggressiveness, float learningRate, boolean adaptationEnabled, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ExplainabilityLevel explainabilityLevel, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.PrivacyMode privacyMode, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.UpdateFrequency updateFrequency) {
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