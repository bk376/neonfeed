package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001Bi\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\u0002\u0010\u0017J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0015H\u00c6\u0003J\t\u0010-\u001a\u00020\u0015H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0006H\u00c6\u0003J\t\u00100\u001a\u00020\bH\u00c6\u0003J\u000f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u00c6\u0003J\t\u00103\u001a\u00020\u000fH\u00c6\u0003J\t\u00104\u001a\u00020\u0011H\u00c6\u0003J\t\u00105\u001a\u00020\u0013H\u00c6\u0003J\u0083\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0015H\u00c6\u0001J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010:\u001a\u00020\bH\u00d6\u0001J\t\u0010;\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0016\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010)\u00a8\u0006<"}, d2 = {"Lcom/hackernews/futuristic/data/model/UserProfile;", "", "userId", "", "username", "reputationScore", "", "level", "", "skillTrees", "", "Lcom/hackernews/futuristic/data/model/SkillTree;", "achievements", "Lcom/hackernews/futuristic/data/model/Achievement;", "userDNA", "Lcom/hackernews/futuristic/data/model/UserDNA;", "readingStats", "Lcom/hackernews/futuristic/data/model/ReadingStats;", "preferences", "Lcom/hackernews/futuristic/data/model/UserPreferences;", "createdAt", "", "lastActiveAt", "(Ljava/lang/String;Ljava/lang/String;FILjava/util/List;Ljava/util/List;Lcom/hackernews/futuristic/data/model/UserDNA;Lcom/hackernews/futuristic/data/model/ReadingStats;Lcom/hackernews/futuristic/data/model/UserPreferences;JJ)V", "getAchievements", "()Ljava/util/List;", "getCreatedAt", "()J", "getLastActiveAt", "getLevel", "()I", "getPreferences", "()Lcom/hackernews/futuristic/data/model/UserPreferences;", "getReadingStats", "()Lcom/hackernews/futuristic/data/model/ReadingStats;", "getReputationScore", "()F", "getSkillTrees", "getUserDNA", "()Lcom/hackernews/futuristic/data/model/UserDNA;", "getUserId", "()Ljava/lang/String;", "getUsername", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class UserProfile {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String userId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String username = null;
    private final float reputationScore = 0.0F;
    private final int level = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.SkillTree> skillTrees = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.Achievement> achievements = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.UserDNA userDNA = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ReadingStats readingStats = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.UserPreferences preferences = null;
    private final long createdAt = 0L;
    private final long lastActiveAt = 0L;
    
    public UserProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String username, float reputationScore, int level, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.SkillTree> skillTrees, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.Achievement> achievements, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.UserDNA userDNA, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ReadingStats readingStats, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.UserPreferences preferences, long createdAt, long lastActiveAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUsername() {
        return null;
    }
    
    public final float getReputationScore() {
        return 0.0F;
    }
    
    public final int getLevel() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.SkillTree> getSkillTrees() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.Achievement> getAchievements() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.UserDNA getUserDNA() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ReadingStats getReadingStats() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.UserPreferences getPreferences() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long getLastActiveAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    public final long component10() {
        return 0L;
    }
    
    public final long component11() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.SkillTree> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.Achievement> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.UserDNA component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ReadingStats component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.UserPreferences component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.UserProfile copy(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    java.lang.String username, float reputationScore, int level, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.SkillTree> skillTrees, @org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.Achievement> achievements, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.UserDNA userDNA, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ReadingStats readingStats, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.UserPreferences preferences, long createdAt, long lastActiveAt) {
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