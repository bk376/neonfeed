package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006R\u0019\u0010\u0002\u001a\u00020\u0003\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006\u0013"}, d2 = {"Lcom/hackernews/futuristic/data/model/BranchType;", "", "color", "Landroidx/compose/ui/graphics/Color;", "strokeWidth", "", "(Ljava/lang/String;IJF)V", "getColor-0d7_KjU", "()J", "J", "getStrokeWidth", "()F", "MAIN_THREAD", "SIDE_DISCUSSION", "ARGUMENT_BRANCH", "QUESTION_BRANCH", "SUPPORT_BRANCH", "TECHNICAL_BRANCH", "DEAD_END", "app_debug"})
public enum BranchType {
    /*public static final*/ MAIN_THREAD /* = new MAIN_THREAD(0L, 0.0F) */,
    /*public static final*/ SIDE_DISCUSSION /* = new SIDE_DISCUSSION(0L, 0.0F) */,
    /*public static final*/ ARGUMENT_BRANCH /* = new ARGUMENT_BRANCH(0L, 0.0F) */,
    /*public static final*/ QUESTION_BRANCH /* = new QUESTION_BRANCH(0L, 0.0F) */,
    /*public static final*/ SUPPORT_BRANCH /* = new SUPPORT_BRANCH(0L, 0.0F) */,
    /*public static final*/ TECHNICAL_BRANCH /* = new TECHNICAL_BRANCH(0L, 0.0F) */,
    /*public static final*/ DEAD_END /* = new DEAD_END(0L, 0.0F) */;
    private final long color = 0L;
    private final float strokeWidth = 0.0F;
    
    BranchType(long color, float strokeWidth) {
    }
    
    public final float getStrokeWidth() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.hackernews.futuristic.data.model.BranchType> getEntries() {
        return null;
    }
}