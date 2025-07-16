package com.hackernews.futuristic.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b$\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\u0002\u0010\u0016J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0011H\u00c6\u0003J\t\u0010,\u001a\u00020\u0013H\u00c6\u0003J\t\u0010-\u001a\u00020\u0015H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\bH\u00c6\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\u000f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u00c6\u0003J\t\u00104\u001a\u00020\rH\u00c6\u0003J\t\u00105\u001a\u00020\u000fH\u00c6\u0003J\u008d\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u00c6\u0001J\u0013\u00107\u001a\u00020\u00152\b\u00108\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00109\u001a\u00020:H\u00d6\u0001J\t\u0010;\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\"R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)\u00a8\u0006<"}, d2 = {"Lcom/hackernews/futuristic/data/model/BusinessOpportunity;", "", "title", "", "description", "marketPotential", "revenueEstimate", "viabilityScore", "", "implementationPlan", "", "hostingRecommendations", "contextEngineering", "Lcom/hackernews/futuristic/data/model/ContextEngineeringPlan;", "distributionStrategy", "Lcom/hackernews/futuristic/data/model/DistributionStrategy;", "appOpportunity", "Lcom/hackernews/futuristic/data/model/AppOpportunity;", "talentRequirements", "Lcom/hackernews/futuristic/data/model/TalentRequirements;", "isAccepted", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;FLjava/util/List;Ljava/util/List;Lcom/hackernews/futuristic/data/model/ContextEngineeringPlan;Lcom/hackernews/futuristic/data/model/DistributionStrategy;Lcom/hackernews/futuristic/data/model/AppOpportunity;Lcom/hackernews/futuristic/data/model/TalentRequirements;Z)V", "getAppOpportunity", "()Lcom/hackernews/futuristic/data/model/AppOpportunity;", "getContextEngineering", "()Lcom/hackernews/futuristic/data/model/ContextEngineeringPlan;", "getDescription", "()Ljava/lang/String;", "getDistributionStrategy", "()Lcom/hackernews/futuristic/data/model/DistributionStrategy;", "getHostingRecommendations", "()Ljava/util/List;", "getImplementationPlan", "()Z", "getMarketPotential", "getRevenueEstimate", "getTalentRequirements", "()Lcom/hackernews/futuristic/data/model/TalentRequirements;", "getTitle", "getViabilityScore", "()F", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class BusinessOpportunity {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String title = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String marketPotential = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String revenueEstimate = null;
    private final float viabilityScore = 0.0F;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> implementationPlan = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> hostingRecommendations = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.ContextEngineeringPlan contextEngineering = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.DistributionStrategy distributionStrategy = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.AppOpportunity appOpportunity = null;
    @org.jetbrains.annotations.NotNull()
    private final com.hackernews.futuristic.data.model.TalentRequirements talentRequirements = null;
    private final boolean isAccepted = false;
    
    public BusinessOpportunity(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String marketPotential, @org.jetbrains.annotations.NotNull()
    java.lang.String revenueEstimate, float viabilityScore, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> implementationPlan, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> hostingRecommendations, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ContextEngineeringPlan contextEngineering, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.DistributionStrategy distributionStrategy, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.AppOpportunity appOpportunity, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.TalentRequirements talentRequirements, boolean isAccepted) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTitle() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMarketPotential() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRevenueEstimate() {
        return null;
    }
    
    public final float getViabilityScore() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getImplementationPlan() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getHostingRecommendations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ContextEngineeringPlan getContextEngineering() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.DistributionStrategy getDistributionStrategy() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.AppOpportunity getAppOpportunity() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TalentRequirements getTalentRequirements() {
        return null;
    }
    
    public final boolean isAccepted() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.AppOpportunity component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.TalentRequirements component11() {
        return null;
    }
    
    public final boolean component12() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    public final float component5() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.ContextEngineeringPlan component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.DistributionStrategy component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hackernews.futuristic.data.model.BusinessOpportunity copy(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String marketPotential, @org.jetbrains.annotations.NotNull()
    java.lang.String revenueEstimate, float viabilityScore, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> implementationPlan, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> hostingRecommendations, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.ContextEngineeringPlan contextEngineering, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.DistributionStrategy distributionStrategy, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.AppOpportunity appOpportunity, @org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.TalentRequirements talentRequirements, boolean isAccepted) {
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