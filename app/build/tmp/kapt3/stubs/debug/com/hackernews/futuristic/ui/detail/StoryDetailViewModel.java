package com.hackernews.futuristic.ui.detail;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J\u0016\u0010\u0017\u001a\u00020\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0002J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u0016J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u001a\u001a\u00020\u0016H\u0082@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u00132\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0015H\u0002J\u0016\u0010 \u001a\u00020\u00132\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0015H\u0002J\u000e\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020\u001fJ\u0006\u0010$\u001a\u00020\u0013J\u000e\u0010%\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\tR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f\u00a8\u0006\'"}, d2 = {"Lcom/hackernews/futuristic/ui/detail/StoryDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/hackernews/futuristic/data/repository/HackerNewsRepository;", "commentThreadAnalyzer", "Lcom/hackernews/futuristic/data/analyzer/CommentThreadAnalyzer;", "(Lcom/hackernews/futuristic/data/repository/HackerNewsRepository;Lcom/hackernews/futuristic/data/analyzer/CommentThreadAnalyzer;)V", "_commentViewMode", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/hackernews/futuristic/ui/components/CommentViewMode;", "_uiState", "Lcom/hackernews/futuristic/ui/detail/StoryDetailUiState;", "commentViewMode", "Lkotlinx/coroutines/flow/StateFlow;", "getCommentViewMode", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "analyzeCommentThread", "", "comments", "", "Lcom/hackernews/futuristic/data/model/Comment;", "loadAllChildComments", "parentComments", "loadChildComments", "parentComment", "loadChildCommentsRecursively", "(Lcom/hackernews/futuristic/data/model/Comment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadComments", "commentIds", "", "loadRemainingComments", "remainingIds", "loadStory", "storyId", "retry", "setCommentViewMode", "mode", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class StoryDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.repository.HackerNewsRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.analyzer.CommentThreadAnalyzer commentThreadAnalyzer = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hackernews.futuristic.ui.detail.StoryDetailUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.detail.StoryDetailUiState> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.hackernews.futuristic.ui.components.CommentViewMode> _commentViewMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.components.CommentViewMode> commentViewMode = null;
    
    @javax.inject.Inject()
    public StoryDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.repository.HackerNewsRepository repository, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.analyzer.CommentThreadAnalyzer commentThreadAnalyzer) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.detail.StoryDetailUiState> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.hackernews.futuristic.ui.components.CommentViewMode> getCommentViewMode() {
        return null;
    }
    
    public final void loadStory(long storyId) {
    }
    
    private final void loadComments(java.util.List<java.lang.Long> commentIds) {
    }
    
    private final void loadAllChildComments(java.util.List<com.hackernews.futuristic.data.model.Comment> parentComments) {
    }
    
    private final void loadRemainingComments(java.util.List<java.lang.Long> remainingIds) {
    }
    
    private final java.lang.Object loadChildCommentsRecursively(com.hackernews.futuristic.data.model.Comment parentComment, kotlin.coroutines.Continuation<? super java.util.List<com.hackernews.futuristic.data.model.Comment>> $completion) {
        return null;
    }
    
    public final void loadChildComments(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.Comment parentComment) {
    }
    
    public final void retry() {
    }
    
    public final void setCommentViewMode(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.ui.components.CommentViewMode mode) {
    }
    
    private final void analyzeCommentThread(java.util.List<com.hackernews.futuristic.data.model.Comment> comments) {
    }
}