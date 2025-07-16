package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u000eJ\u0015\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u00c6\u0003J\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012J\t\u0010\u001c\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\fH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003JR\u0010\u001f\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0005H\u00c6\u0001\u00a2\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010$\u001a\u00020\fH\u0016J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\'"}, d2 = {"Lcom/hackernews/futuristic/data/model/QuantumState;", "", "superpositionLevels", "", "Lcom/hackernews/futuristic/data/model/SearchDimension;", "", "entanglementMatrix", "", "", "coherenceTime", "", "observationCount", "", "quantumInterference", "(Ljava/util/Map;[[FJIF)V", "getCoherenceTime", "()J", "getEntanglementMatrix", "()[[F", "[[F", "getObservationCount", "()I", "getQuantumInterference", "()F", "getSuperpositionLevels", "()Ljava/util/Map;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/util/Map;[[FJIF)Lcom/hackernews/futuristic/data/model/QuantumState;", "equals", "", "other", "hashCode", "toString", "", "app_debug"})
public final class QuantumState {
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<com.hackernews.futuristic.data.model.SearchDimension, java.lang.Float> superpositionLevels = null;
    @org.jetbrains.annotations.NotNull()
    private final float[][] entanglementMatrix = null;
    private final long coherenceTime = 0L;
    private final int observationCount = 0;
    private final float quantumInterference = 0.0F;
    
    public QuantumState(@org.jetbrains.annotations.NotNull()
    java.util.Map<com.hackernews.futuristic.data.model.SearchDimension, java.lang.Float> superpositionLevels, @org.jetbrains.annotations.NotNull()
    float[][] entanglementMatrix, long coherenceTime, int observationCount, float quantumInterference) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<com.hackernews.futuristic.data.model.SearchDimension, java.lang.Float> getSuperpositionLevels() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final float[][] getEntanglementMatrix() {
        return null;
    }
    
    public final long getCoherenceTime() {
        return 0L;
    }
    
    public final int getObservationCount() {
        return 0;
    }
    
    public final float getQuantumInterference() {
        return 0.0F;
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<com.hackernews.futuristic.data.model.SearchDimension, java.lang.Float> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final float[][] component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.QuantumState copy(@org.jetbrains.annotations.NotNull()
    java.util.Map<com.hackernews.futuristic.data.model.SearchDimension, java.lang.Float> superpositionLevels, @org.jetbrains.annotations.NotNull()
    float[][] entanglementMatrix, long coherenceTime, int observationCount, float quantumInterference) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}