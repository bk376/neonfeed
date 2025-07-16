package com.hackernews.futuristic.ui.detail;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0018\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a(\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a\u0010\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u000bH\u0007\u001a(\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\r2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007\u001a$\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u001b2\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00010\u001dH\u0007\u001a\u0010\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u0015H\u0002\u00a8\u0006 "}, d2 = {"CommentCard", "", "comment", "Lcom/hackernews/futuristic/data/model/Comment;", "CommentsHeader", "commentCount", "", "isLoading", "", "ErrorMessage", "message", "", "onRetry", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "SafeCommentCard", "StoryContent", "text", "StoryDetailScreen", "storyId", "", "onBackClick", "viewModel", "Lcom/hackernews/futuristic/ui/detail/StoryDetailViewModel;", "StoryHeader", "story", "Lcom/hackernews/futuristic/data/model/Story;", "onUrlClick", "Lkotlin/Function1;", "formatTimeAgo", "timestamp", "app_debug"})
public final class StoryDetailScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void StoryDetailScreen(long storyId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBackClick, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.ui.detail.StoryDetailViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StoryHeader(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.Story story, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onUrlClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void StoryContent(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CommentsHeader(int commentCount, boolean isLoading) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SafeCommentCard(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.Comment comment) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CommentCard(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.Comment comment) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void ErrorMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRetry, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    private static final java.lang.String formatTimeAgo(long timestamp) {
        return null;
    }
}