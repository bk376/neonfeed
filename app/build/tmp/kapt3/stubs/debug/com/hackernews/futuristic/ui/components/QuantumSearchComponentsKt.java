package com.hackernews.futuristic.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u0092\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a&\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a*\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a4\u0010\u0010\u001a\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001a.\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001ah\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\n2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u00142\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00122\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\"\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001al\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020%2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00010\u00142\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00142\u0006\u0010\'\u001a\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001a\"\u0010(\u001a\u00020\u00012\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001a.\u0010-\u001a\u00020\u00012\u0006\u0010.\u001a\u00020/2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001a\u001a\u00102\u001a\u00020\u00012\u0006\u00103\u001a\u0002042\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001a(\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u0002012\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001a&\u00108\u001a\u00020\u00012\u0006\u00109\u001a\u00020%2\u0006\u0010\u0004\u001a\u00020\u00052\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a \u0010:\u001a\u00020\u00012\f\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\u00122\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001a\u001e\u0010=\u001a\u00020\u00012\u0006\u0010>\u001a\u00020 2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003\u001a&\u0010?\u001a\u00020\u0001*\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0019H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\bD\u0010E\u001a.\u0010F\u001a\u00020\u0001*\u00020@2\u0006\u00103\u001a\u0002042\u0006\u0010C\u001a\u00020\u00192\u0006\u0010A\u001a\u00020BH\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\bG\u0010H\u001a,\u0010I\u001a\u00020\u0001*\u00020@2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\u00122\u0006\u0010A\u001a\u00020BH\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\bJ\u0010K\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006L"}, d2 = {"DimensionChip", "", "dimension", "Lcom/hackernews/futuristic/data/model/SearchDimension;", "isSelected", "", "onToggle", "Lkotlin/Function0;", "MetricDisplay", "label", "", "value", "color", "Landroidx/compose/ui/graphics/Color;", "MetricDisplay-mxwnekA", "(Ljava/lang/String;Ljava/lang/String;J)V", "QuantumDimensionSelector", "selectedDimensions", "", "onDimensionToggle", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "QuantumProbabilitySlider", "probability", "", "onProbabilityChange", "QuantumSearchBar", "query", "onQueryChange", "onSearch", "searchSuggestions", "Lcom/hackernews/futuristic/data/model/SearchSuggestion;", "onSuggestionClick", "isSearching", "QuantumSearchControls", "searchMode", "Lcom/hackernews/futuristic/data/model/SearchMode;", "onSearchModeChange", "probabilityThreshold", "QuantumSearchMetrics", "metrics", "Lcom/hackernews/futuristic/data/model/SearchMetrics;", "executionTime", "", "QuantumSearchResults", "searchResult", "Lcom/hackernews/futuristic/data/model/QuantumSearchResult;", "onStoryClick", "Lcom/hackernews/futuristic/data/model/Story;", "QuantumStateVisualization", "quantumState", "Lcom/hackernews/futuristic/data/model/QuantumState;", "QuantumStoryCard", "story", "onClick", "SearchModeChip", "mode", "SearchPathsVisualization", "searchPaths", "Lcom/hackernews/futuristic/data/model/SearchPath;", "SearchSuggestionItem", "suggestion", "drawQuantumField", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "size", "Landroidx/compose/ui/geometry/Size;", "animationProgress", "drawQuantumField-12SF9DM", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JF)V", "drawQuantumFieldVisualization", "drawQuantumFieldVisualization-Ug5Nnss", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Lcom/hackernews/futuristic/data/model/QuantumState;FJ)V", "drawSearchPaths", "drawSearchPaths-cSwnlzA", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Ljava/util/List;J)V", "app_debug"})
public final class QuantumSearchComponentsKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void QuantumSearchBar(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQueryChange, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onSearch, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.SearchSuggestion> searchSuggestions, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.data.model.SearchSuggestion, kotlin.Unit> onSuggestionClick, boolean isSearching, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SearchSuggestionItem(com.hackernews.futuristic.data.model.SearchSuggestion suggestion, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuantumSearchControls(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.SearchMode searchMode, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.data.model.SearchMode, kotlin.Unit> onSearchModeChange, @org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.hackernews.futuristic.data.model.SearchDimension> selectedDimensions, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.data.model.SearchDimension, kotlin.Unit> onDimensionToggle, float probabilityThreshold, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> onProbabilityChange, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SearchModeChip(com.hackernews.futuristic.data.model.SearchMode mode, boolean isSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuantumDimensionSelector(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.hackernews.futuristic.data.model.SearchDimension> selectedDimensions, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.data.model.SearchDimension, kotlin.Unit> onDimensionToggle, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DimensionChip(com.hackernews.futuristic.data.model.SearchDimension dimension, boolean isSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onToggle) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuantumProbabilitySlider(float probability, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Float, kotlin.Unit> onProbabilityChange, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuantumSearchResults(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.QuantumSearchResult searchResult, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.data.model.Story, kotlin.Unit> onStoryClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuantumSearchMetrics(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.SearchMetrics metrics, long executionTime, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuantumStateVisualization(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.QuantumState quantumState, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SearchPathsVisualization(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.SearchPath> searchPaths, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void QuantumStoryCard(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.Story story, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}