package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003\u00f8\u0001\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0016\u0010\u000fJ\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0007H\u00c6\u0003J\u0016\u0010\u0019\u001a\u00020\tH\u00c6\u0003\u00f8\u0001\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0007H\u00c6\u0001\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020%H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\b\u001a\u00020\t\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0002\u001a\u00020\u0003\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r\u0082\u0002\u000b\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b!\u00a8\u0006&"}, d2 = {"Lcom/hackernews/futuristic/data/model/TrendingIndicator;", "", "position", "Landroidx/compose/ui/geometry/Offset;", "trend", "Lcom/hackernews/futuristic/data/model/TrendDirection;", "velocity", "", "color", "Landroidx/compose/ui/graphics/Color;", "arrowSize", "(JLcom/hackernews/futuristic/data/model/TrendDirection;FJFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getArrowSize", "()F", "getColor-0d7_KjU", "()J", "J", "getPosition-F1C5BW0", "getTrend", "()Lcom/hackernews/futuristic/data/model/TrendDirection;", "getVelocity", "component1", "component1-F1C5BW0", "component2", "component3", "component4", "component4-0d7_KjU", "component5", "copy", "copy-CYSnLuk", "(JLcom/hackernews/futuristic/data/model/TrendDirection;FJF)Lcom/hackernews/futuristic/data/model/TrendingIndicator;", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class TrendingIndicator {
    private final long position = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.TrendDirection trend = null;
    private final float velocity = 0.0F;
    private final long color = 0L;
    private final float arrowSize = 0.0F;
    
    private TrendingIndicator(long position, com.hackernews.futuristic.data.model.TrendDirection trend, float velocity, long color, float arrowSize) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TrendDirection getTrend() {
        return null;
    }
    
    public final float getVelocity() {
        return 0.0F;
    }
    
    public final float getArrowSize() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TrendDirection component2() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}