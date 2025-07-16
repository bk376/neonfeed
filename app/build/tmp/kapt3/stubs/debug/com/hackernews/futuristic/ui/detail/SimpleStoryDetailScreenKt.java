package com.hackernews.futuristic.ui.detail;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0003\u001a(\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0007\u001a\u0010\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u0010H\u0003\u001a\"\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u0003\u001a\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00130\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018H\u0002\u001a\u0010\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\tH\u0002\u001a\u001a\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\u0015H\u0002\u00a8\u0006\u001f"}, d2 = {"SimpleCommentCard", "", "comment", "Lcom/hackernews/futuristic/data/model/Comment;", "SimpleStoryContent", "text", "", "SimpleStoryDetailScreen", "storyId", "", "onBackClick", "Lkotlin/Function0;", "viewModel", "Lcom/hackernews/futuristic/ui/detail/StoryDetailViewModel;", "SimpleStoryHeader", "story", "Lcom/hackernews/futuristic/data/model/Story;", "ThreadedCommentCard", "commentNode", "Lcom/hackernews/futuristic/ui/detail/CommentNode;", "depth", "", "maxDepth", "buildCommentTree", "", "comments", "formatTimeAgo", "timestamp", "parseHtmlContent", "htmlText", "maxLength", "app_debug"})
public final class SimpleStoryDetailScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void SimpleStoryDetailScreen(long storyId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onBackClick, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.ui.detail.StoryDetailViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SimpleStoryHeader(com.hackernews.futuristic.data.model.Story story) {
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