package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003JE\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\"H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r\u00a8\u0006#"}, d2 = {"Lcom/hackernews/futuristic/data/model/ReadingPattern;", "", "averageReadingTime", "", "preferredTimeOfDay", "Lcom/hackernews/futuristic/data/model/TimeOfDay;", "readingSpeed", "Lcom/hackernews/futuristic/data/model/ReadingSpeed;", "completionRate", "skipRate", "bookmarkRate", "(FLcom/hackernews/futuristic/data/model/TimeOfDay;Lcom/hackernews/futuristic/data/model/ReadingSpeed;FFF)V", "getAverageReadingTime", "()F", "getBookmarkRate", "getCompletionRate", "getPreferredTimeOfDay", "()Lcom/hackernews/futuristic/data/model/TimeOfDay;", "getReadingSpeed", "()Lcom/hackernews/futuristic/data/model/ReadingSpeed;", "getSkipRate", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
public final class ReadingPattern {
    private final float averageReadingTime = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.TimeOfDay preferredTimeOfDay = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ReadingSpeed readingSpeed = null;
    private final float completionRate = 0.0F;
    private final float skipRate = 0.0F;
    private final float bookmarkRate = 0.0F;
    
    public ReadingPattern(float averageReadingTime, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.TimeOfDay preferredTimeOfDay, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ReadingSpeed readingSpeed, float completionRate, float skipRate, float bookmarkRate) {
        super();
    }
    
    public final float getAverageReadingTime() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TimeOfDay getPreferredTimeOfDay() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ReadingSpeed getReadingSpeed() {
        return null;
    }
    
    public final float getCompletionRate() {
        return 0.0F;
    }
    
    public final float getSkipRate() {
        return 0.0F;
    }
    
    public final float getBookmarkRate() {
        return 0.0F;
    }
    
    public final float component1() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TimeOfDay component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ReadingSpeed component3() {
        return null;
    }
    
    public final float component4() {
        return 0.0F;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ReadingPattern copy(float averageReadingTime, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.TimeOfDay preferredTimeOfDay, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ReadingSpeed readingSpeed, float completionRate, float skipRate, float bookmarkRate) {
        return null;
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