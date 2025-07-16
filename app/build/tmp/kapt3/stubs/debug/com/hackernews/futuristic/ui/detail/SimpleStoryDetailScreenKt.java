package com.hackernews.futuristic.ui.detail;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000P\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0003\u001aF\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u001c\b\u0002\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007\u001a.\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u001c\b\u0002\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0001\u0018\u00010\rH\u0003\u001a\"\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0003\u001a\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00150\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\u001aH\u0002\u001a\u0010\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\tH\u0002\u001a\u001a\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\b\b\u0002\u0010 \u001a\u00020\u0017H\u0002\u00a8\u0006!"}, d2 = {"SimpleCommentCard", "", "comment", "Lcom/hackernews/futuristic/data/model/Comment;", "SimpleStoryContent", "text", "", "SimpleStoryDetailScreen", "storyId", "", "onBackClick", "Lkotlin/Function0;", "onUrlClick", "Lkotlin/Function2;", "viewModel", "Lcom/hackernews/futuristic/ui/detail/StoryDetailViewModel;", "SimpleStoryHeader", "story", "Lcom/hackernews/futuristic/data/model/Story;", "ThreadedCommentCard", "commentNode", "Lcom/hackernews/futuristic/ui/detail/CommentNode;", "depth", "", "maxDepth", "buildCommentTree", "", "comments", "formatTimeAgo", "timestamp", "parseHtmlContent", "htmlText", "maxLength", "app_debug"})
public final class SimpleStoryDetailScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SimpleStoryDetailScreen(long storyId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBackClick, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> onUrlClick, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.ui.detail.StoryDetailViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SimpleStoryHeader(com.hackernews.futuristic.data.model.Story story, kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> onUrlClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SimpleStoryContent(java.lang.String text) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SimpleCommentCard(com.hackernews.futuristic.data.model.Comment comment) {
    }
    
    private static final java.util.List<com.hackernews.futuristic.ui.detail.CommentNode> buildCommentTree(java.util.List<com.hackernews.futuristic.data.model.Comment> comments) {
        return null;
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ThreadedCommentCard(com.hackernews.futuristic.ui.detail.CommentNode commentNode, int depth, int maxDepth) {
    }
    
    private static final java.lang.String formatTimeAgo(long timestamp) {
        return null;
    }
    
    private static final java.lang.String parseHtmlContent(java.lang.String htmlText, int maxLength) {
        return null;
    }
}