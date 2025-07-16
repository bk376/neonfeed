package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u0006\u0010\u000b\u001a\u00020\b\u00a2\u0006\u0002\u0010\fJ\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\bH\u00c6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003JQ\u0010\u001c\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0002\u0010\u000b\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020\u0006H\u00d6\u0001J\t\u0010!\u001a\u00020\bH\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\""}, d2 = {"Lcom/hackernews/futuristic/data/model/TalentRequirements;", "", "roles", "", "Lcom/hackernews/futuristic/data/model/TalentRole;", "totalTeamSize", "", "estimatedCost", "", "timeToHire", "keySkills", "experienceLevel", "(Ljava/util/List;ILjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getEstimatedCost", "()Ljava/lang/String;", "getExperienceLevel", "getKeySkills", "()Ljava/util/List;", "getRoles", "getTimeToHire", "getTotalTeamSize", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class TalentRequirements {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.hackernews.futuristic.data.model.TalentRole> roles = null;
    private final int totalTeamSize = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String estimatedCost = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String timeToHire = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> keySkills = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String experienceLevel = null;
    
    public TalentRequirements(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.TalentRole> roles, int totalTeamSize, @org.jetbrains.annotations.NotNull()
    java.lang.String estimatedCost, @org.jetbrains.annotations.NotNull()
    java.lang.String timeToHire, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> keySkills, @org.jetbrains.annotations.NotNull()
    java.lang.String experienceLevel) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.TalentRole> getRoles() {
        return null;
    }
    
    public final int getTotalTeamSize() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEstimatedCost() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTimeToHire() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getKeySkills() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getExperienceLevel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.hackernews.futuristic.data.model.TalentRole> component1() {
        return null;
    }
    
    public final int component2() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TalentRequirements copy(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.TalentRole> roles, int totalTeamSize, @org.jetbrains.annotations.NotNull()
    java.lang.String estimatedCost, @org.jetbrains.annotations.NotNull()
    java.lang.String timeToHire, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> keySkills, @org.jetbrains.annotations.NotNull()
    java.lang.String experienceLevel) {
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