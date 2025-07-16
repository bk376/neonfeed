package com.hackernews.futuristic.ui.stories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000L\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a(\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a\u0012\u0010\u000b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a(\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a\u0010\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a&\u0010\u0011\u001a\u00020\u00012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00010\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007\u001a\u001e\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\bH\u0007\u001a.\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001c2\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00010\u00132\b\b\u0002\u0010\t\u001a\u00020\nH\u0007\u001a\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020 H\u0002\u00a8\u0006!"}, d2 = {"CyberpunkScoreChip", "", "score", "", "ErrorMessage", "message", "", "onRetry", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "LoadingIndicator", "NeuralAnalysisToggle", "isEnabled", "", "onToggle", "ScoreChip", "StoriesScreen", "onStoryClick", "Lkotlin/Function1;", "Lcom/hackernews/futuristic/data/model/Story;", "viewModel", "Lcom/hackernews/futuristic/ui/stories/StoriesViewModel;", "StoryCard", "story", "onClick", "StoryTypeSelector", "selectedType", "Lcom/hackernews/futuristic/ui/stories/StoryType;", "onTypeSelected", "formatTimeAgo", "timestamp", "", "app_debug"})
public final class StoriesScreenKt {
    
    @androidx.compose.runtime.Composable()
    public static final void StoriesScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.data.model.Story, kotlin.Unit> onStoryClick, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.ui.stories.StoriesViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StoryTypeSelector(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.ui.stories.StoryType selectedType, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.ui.stories.StoryType, kotlin.Unit> onTypeSelected, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StoryCard(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.Story story, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CyberpunkScoreChip(int score) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ScoreChip(int score) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void LoadingIndicator(@org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ErrorMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRetry, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void NeuralAnalysisToggle(boolean isEnabled, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onToggle, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.String formatTimeAgo(long timestamp) {
        return null;
    }
}