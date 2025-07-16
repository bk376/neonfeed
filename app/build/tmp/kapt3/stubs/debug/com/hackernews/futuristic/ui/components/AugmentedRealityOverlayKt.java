package com.hackernews.futuristic.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000^\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\u0010\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\nH\u0003\u001a\u001a\u0010\u000b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\"\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\"\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\"\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\u0018\u0010\u0016\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u0003\u001a\u001a\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0003\u001a0\u0010\u0018\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002\u001a\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a&\u0010 \u001a\u00020\u0001*\u00020!2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b$\u0010%\u001a.\u0010&\u001a\u00020\u0001*\u00020!2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\'\u0010(\u001a&\u0010)\u001a\u00020\u0001*\u00020!2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b*\u0010%\u001a&\u0010+\u001a\u00020\u0001*\u00020!2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\"\u001a\u00020#H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b,\u0010%\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006-"}, d2 = {"ARControlPanel", "", "isAREnabled", "", "onARToggle", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "ARControlsDisplay", "scanlineProgress", "", "ARGridBackground", "ARHUDComponent", "story", "Lcom/hackernews/futuristic/data/model/Story;", "ARInfoPanelComponent", "panel", "Lcom/hackernews/futuristic/data/model/ARInfoPanel;", "ARMarkerComponent", "marker", "Lcom/hackernews/futuristic/data/model/ARMarker;", "hologramGlow", "ARMetadataDisplay", "ARTargetingSystem", "AugmentedRealityOverlay", "calculateVelocity", "", "formatTimeAgo", "timestamp", "", "generateARData", "Lcom/hackernews/futuristic/data/model/AROverlayData;", "drawARGrid", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "size", "Landroidx/compose/ui/geometry/Size;", "drawARGrid-cSwnlzA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;FJ)V", "drawARMarker", "drawARMarker-Ug5Nnss", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Lcom/hackernews/futuristic/data/model/ARMarker;FJ)V", "drawARTargetingReticle", "drawARTargetingReticle-cSwnlzA", "drawHolographicScanlines", "drawHolographicScanlines-cSwnlzA", "app_debug"})
public final class AugmentedRealityOverlayKt {
    
    @androidx.compose.runtime.Composable()
    public static final void AugmentedRealityOverlay(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.Story story, boolean isAREnabled, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onARToggle, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ARGridBackground(float scanlineProgress, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ARMarkerComponent(com.hackernews.futuristic.data.model.ARMarker marker, float hologramGlow, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ARInfoPanelComponent(com.hackernews.futuristic.data.model.ARInfoPanel panel, float scanlineProgress, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ARHUDComponent(com.hackernews.futuristic.data.model.Story story, float scanlineProgress, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ARMetadataDisplay(com.hackernews.futuristic.data.model.Story story, float scanlineProgress) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ARControlsDisplay(float scanlineProgress) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ARTargetingSystem(float hologramGlow, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ARControlPanel(boolean isAREnabled, kotlin.jvm.functions.Function0<kotlin.Unit> onARToggle, androidx.compose.ui.Modifier modifier) {
    }
    
    private static final com.hackernews.futuristic.data.model.AROverlayData generateARData(com.hackernews.futuristic.data.model.Story story) {
        return null;
    }
    
    private static final java.lang.String formatTimeAgo(long timestamp) {
        return null;
    }
    
    private static final java.lang.String calculateVelocity(com.hackernews.futuristic.data.model.Story story) {
        return null;
    }
}