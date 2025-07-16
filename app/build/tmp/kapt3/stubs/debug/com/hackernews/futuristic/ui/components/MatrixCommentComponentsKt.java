package com.hackernews.futuristic.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000x\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0007\u001a\u001e\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\fH\u0003\u001a0\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a,\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u001a*\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a\u001a\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0003\u001a-\u0010\u001f\u001a\u00020 2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u0013H\u0002\u00a2\u0006\u0002\u0010$\u001a\u0010\u0010%\u001a\u00020&2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a*\u0010\'\u001a\u00020 2\u0006\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020 2\u0006\u0010*\u001a\u00020\u0013H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010,\u001a4\u0010-\u001a\u00020\u0001*\u00020.2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a4\u0010/\u001a\u00020\u0001*\u00020.2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a.\u00100\u001a\u00020\u0001*\u00020.2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u00101\u001a\u000202H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b3\u00104\u001a&\u00105\u001a\u00020\u0001*\u00020.2\u0006\u00101\u001a\u0002022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b6\u00107\u001a6\u00108\u001a\u00020\u0001*\u00020.2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u00101\u001a\u0002022\u0006\u00109\u001a\u00020:H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b;\u0010<\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006="}, d2 = {"CommentThreadSelector", "", "viewMode", "Lcom/hackernews/futuristic/ui/components/CommentViewMode;", "onViewModeChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "MatrixCommentCard", "comment", "Lcom/hackernews/futuristic/data/model/ThreadComment;", "onClick", "Lkotlin/Function0;", "MatrixCommentThread", "commentThread", "Lcom/hackernews/futuristic/data/model/CommentThread;", "onCommentClick", "MatrixThreadVisualization", "animationProgress", "", "MetricItem", "label", "", "value", "color", "Landroidx/compose/ui/graphics/Color;", "MetricItem-mxwnekA", "(Ljava/lang/String;Ljava/lang/String;J)V", "ThreadMetricsHeader", "threadMetrics", "Lcom/hackernews/futuristic/data/model/ThreadMetrics;", "calculateNodePosition", "Landroidx/compose/ui/geometry/Offset;", "centerX", "centerY", "maxRadius", "(Lcom/hackernews/futuristic/data/model/ThreadComment;FFF)J", "generateMatrixDataStream", "Lcom/hackernews/futuristic/data/model/MatrixDataStream;", "lerpOffset", "start", "end", "t", "lerpOffset-Wko1d7g", "(JJF)J", "drawCommentConnections", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drawCommentNodes", "drawDataStreams", "size", "Landroidx/compose/ui/geometry/Size;", "drawDataStreams-Ug5Nnss", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Lcom/hackernews/futuristic/data/model/CommentThread;FJ)V", "drawMatrixGrid", "drawMatrixGrid-12SF9DM", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JF)V", "drawMatrixThreadNetwork", "density", "Landroidx/compose/ui/unit/Density;", "drawMatrixThreadNetwork-yzxVdVo", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Lcom/hackernews/futuristic/data/model/CommentThread;FJLandroidx/compose/ui/unit/Density;)V", "app_debug"})
public final class MatrixCommentComponentsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void MatrixCommentThread(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.CommentThread commentThread, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.data.model.ThreadComment, kotlin.Unit> onCommentClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ThreadMetricsHeader(com.hackernews.futuristic.data.model.ThreadMetrics threadMetrics, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MatrixThreadVisualization(com.hackernews.futuristic.data.model.CommentThread commentThread, float animationProgress, kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.data.model.ThreadComment, kotlin.Unit> onCommentClick) {
    }
    
    private static final void drawCommentConnections(androidx.compose.ui.graphics.drawscope.DrawScope $this$drawCommentConnections, com.hackernews.futuristic.data.model.CommentThread commentThread, float centerX, float centerY, float maxRadius, float animationProgress) {
    }
    
    private static final void drawCommentNodes(androidx.compose.ui.graphics.drawscope.DrawScope $this$drawCommentNodes, com.hackernews.futuristic.data.model.CommentThread commentThread, float centerX, float centerY, float maxRadius, float animationProgress) {
    }
    
    private static final com.hackernews.futuristic.data.model.MatrixDataStream generateMatrixDataStream(com.hackernews.futuristic.data.model.ThreadComment comment) {
        return null;
    }
    
    private static final long calculateNodePosition(com.hackernews.futuristic.data.model.ThreadComment comment, float centerX, float centerY, float maxRadius) {
        return 0L;
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MatrixCommentCard(com.hackernews.futuristic.data.model.ThreadComment comment, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void CommentThreadSelector(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.ui.components.CommentViewMode viewMode, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.hackernews.futuristic.ui.components.CommentViewMode, kotlin.Unit> onViewModeChange, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
}