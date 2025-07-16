package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001c\u001a\u00020\tH\u00c6\u0003JE\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u001e\u001a\u00020\t2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020!H\u00d6\u0001J\t\u0010\"\u001a\u00020#H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006$"}, d2 = {"Lcom/hackernews/futuristic/data/model/ARCalibration;", "", "deviceOrientation", "Lcom/hackernews/futuristic/data/model/DeviceOrientation;", "lightingConditions", "Lcom/hackernews/futuristic/data/model/LightingCondition;", "trackingAccuracy", "", "stabilization", "", "autoFocus", "depthSensing", "(Lcom/hackernews/futuristic/data/model/DeviceOrientation;Lcom/hackernews/futuristic/data/model/LightingCondition;FZZZ)V", "getAutoFocus", "()Z", "getDepthSensing", "getDeviceOrientation", "()Lcom/hackernews/futuristic/data/model/DeviceOrientation;", "getLightingConditions", "()Lcom/hackernews/futuristic/data/model/LightingCondition;", "getStabilization", "getTrackingAccuracy", "()F", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"})
public final class ARCalibration {
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.DeviceOrientation deviceOrientation = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.LightingCondition lightingConditions = null;
    private final float trackingAccuracy = 0.0F;
    private final boolean stabilization = false;
    private final boolean autoFocus = false;
    private final boolean depthSensing = false;
    
    public ARCalibration(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.DeviceOrientation deviceOrientation, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.LightingCondition lightingConditions, float trackingAccuracy, boolean stabilization, boolean autoFocus, boolean depthSensing) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.DeviceOrientation getDeviceOrientation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.LightingCondition getLightingConditions() {
        return null;
    }
    
    public final float getTrackingAccuracy() {
        return 0.0F;
    }
    
    public final boolean getStabilization() {
        return false;
    }
    
    public final boolean getAutoFocus() {
        return false;
    }
    
    public final boolean getDepthSensing() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.DeviceOrientation component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.LightingCondition component2() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ARCalibration copy(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.DeviceOrientation deviceOrientation, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.LightingCondition lightingConditions, float trackingAccuracy, boolean stabilization, boolean autoFocus, boolean depthSensing) {
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