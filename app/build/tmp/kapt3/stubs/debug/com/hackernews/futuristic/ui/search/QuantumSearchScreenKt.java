package com.hackernews.futuristic.ui.search;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0003\u001a(\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0003\u001a\u0012\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0003\u001aT\u0010\n\u001a\u00020\u00012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f2,\b\u0002\u0010\u000e\u001a&\u0012\u001a\u0012\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007\u001a\b\u0010\u0013\u001a\u00020\u0001H\u0003\u001a\u001c\u0010\u0014\u001a\u00020\u00012\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\fH\u0003\u001a\b\u0010\u0016\u001a\u00020\u0001H\u0003\u001a\b\u0010\u0017\u001a\u00020\u0001H\u0003\u001a.\u0010\u0018\u001a\u00020\u00012\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0003\u001a$\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\fH\u0003\u001a(\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0003\u001a.\u0010 \u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\f2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0003\u00a8\u0006!"}, d2 = {"QuantumFeaturesList", "", "QuantumSearchError", "message", "", "onRetry", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "QuantumSearchLoadingIndicator", "QuantumSearchScreen", "onStoryClick", "Lkotlin/Function1;", "Lcom/hackernews/futuristic/data/model/Story;", "onScrollToTopCallback", "Lkotlin/coroutines/Continuation;", "", "viewModel", "Lcom/hackernews/futuristic/ui/search/QuantumSearchViewModel;", "QuantumSearchTips", "RegularQuickSearchSuggestions", "onQuickSearch", "RegularSearchFeaturesList", "RegularSearchTips", "RegularSearchWelcome", "scrollState", "Landroidx/compose/foundation/ScrollState;", "SimpleQuickSearchSuggestions", "isRegularMode", "", "SimpleSearchHeader", "onModeToggle", "SimpleSearchWelcome", "app_debug"})
public final class QuantumSearchScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void QuantumSearchScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.data.model.Story, kotlin.Unit> onStoryClick, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object>, kotlin.Unit> onScrollToTopCallback, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.ui.search.QuantumSearchViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SimpleSearchHeader(boolean isRegularMode, kotlin.jvm.functions.Function0<kotlin.Unit> onModeToggle, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuantumSearchLoadingIndicator(androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuantumSearchError(java.lang.String message, kotlin.jvm.functions.Function0<kotlin.Unit> onRetry, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SimpleSearchWelcome(boolean isRegularMode, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQuickSearch, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuantumFeaturesList() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SimpleQuickSearchSuggestions(boolean isRegularMode, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQuickSearch) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuantumSearchTips() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RegularSearchWelcome(kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQuickSearch, androidx.compose.foundation.ScrollState scrollState, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RegularSearchFeaturesList() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RegularQuickSearchSuggestions(kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onQuickSearch) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void RegularSearchTips() {
    }
}