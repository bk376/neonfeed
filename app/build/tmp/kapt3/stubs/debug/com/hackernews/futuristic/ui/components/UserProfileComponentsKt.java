package com.hackernews.futuristic.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u008e\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001a \u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a\"\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000e\u0010\u000f\u001a \u0010\u0010\u001a\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0003\u001a\u0010\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0003\u001a*\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\rH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a\u0010\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\u001eH\u0003\u001a\u0018\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\"H\u0003\u001a\u0010\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020%H\u0003\u001a2\u0010&\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\'\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u000bH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b)\u0010*\u001a\u0010\u0010+\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-H\u0003\u001a \u0010.\u001a\u00020\u00012\f\u0010/\u001a\b\u0012\u0004\u0012\u00020-0\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a*\u00100\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\rH\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b1\u0010\u001b\u001a&\u00102\u001a\u00020\u00012\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020\u000b042\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0003\u001a\u001a\u00106\u001a\u00020\u00012\u0006\u00107\u001a\u0002082\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a\u001a\u00109\u001a\u00020\u00012\u0006\u0010:\u001a\u00020;2\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007\u001a:\u0010<\u001a\u00020\u0001*\u00020=2\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020\u000b042\u0006\u0010>\u001a\u00020\u000b2\u0006\u0010?\u001a\u00020@H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\bA\u0010B\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006C"}, d2 = {"AchievementCard", "", "achievement", "Lcom/hackernews/futuristic/data/model/Achievement;", "AchievementGrid", "achievements", "", "modifier", "Landroidx/compose/ui/Modifier;", "AchievementProgressBar", "progress", "", "color", "Landroidx/compose/ui/graphics/Color;", "AchievementProgressBar-4WTKRHQ", "(FJ)V", "ActivityHeatmap", "activityHeatmap", "Lcom/hackernews/futuristic/data/model/ActivityHour;", "EngagementStyleIndicator", "engagementStyle", "Lcom/hackernews/futuristic/data/model/EngagementStyle;", "PatternItem", "label", "", "value", "PatternItem-mxwnekA", "(Ljava/lang/String;Ljava/lang/String;J)V", "ReadingPatternsDisplay", "readingPatterns", "Lcom/hackernews/futuristic/data/model/ReadingPattern;", "ReputationScoreDisplay", "score", "level", "", "SkillNode", "skill", "Lcom/hackernews/futuristic/data/model/Skill;", "SkillProgressBar", "experience", "maxExperience", "SkillProgressBar-RPmYEkk", "(FJFF)V", "SkillTreeCard", "skillTree", "Lcom/hackernews/futuristic/data/model/SkillTree;", "SkillTreeVisualization", "skillTrees", "StatItem", "StatItem-mxwnekA", "TopicInterestsRadar", "topicsOfInterest", "", "Lcom/hackernews/futuristic/data/model/Topic;", "UserDNAVisualization", "userDNA", "Lcom/hackernews/futuristic/data/model/UserDNA;", "UserProfileCard", "profile", "Lcom/hackernews/futuristic/data/model/UserProfile;", "drawRadarChart", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "animationProgress", "size", "Landroidx/compose/ui/geometry/Size;", "drawRadarChart-Ug5Nnss", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Ljava/util/Map;FJ)V", "app_debug"})
public final class UserProfileComponentsKt {
    
    @androidx.compose.runtime.Composable()
    public static final void UserProfileCard(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.UserProfile profile, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ReputationScoreDisplay(float score, int level) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EngagementStyleIndicator(com.hackernews.futuristic.data.model.EngagementStyle engagementStyle) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void SkillTreeVisualization(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.SkillTree> skillTrees, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SkillTreeCard(com.hackernews.futuristic.data.model.SkillTree skillTree) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void SkillNode(com.hackernews.futuristic.data.model.Skill skill) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void AchievementGrid(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.model.Achievement> achievements, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AchievementCard(com.hackernews.futuristic.data.model.Achievement achievement) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void UserDNAVisualization(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.model.UserDNA userDNA, @org.jetbrains.annotations.NotNull()
    androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void TopicInterestsRadar(java.util.Map<com.hackernews.futuristic.data.model.Topic, java.lang.Float> topicsOfInterest, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ReadingPatternsDisplay(com.hackernews.futuristic.data.model.ReadingPattern readingPatterns) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ActivityHeatmap(java.util.List<com.hackernews.futuristic.data.model.ActivityHour> activityHeatmap, androidx.compose.ui.Modifier modifier) {
    }
}