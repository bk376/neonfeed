package com.hackernews.futuristic.data.model

import androidx.compose.ui.graphics.Color
import com.hackernews.futuristic.ui.theme.*

data class CommentThread(
    val rootCommentId: Long,
    val comments: List<ThreadComment>,
    val threadMetrics: ThreadMetrics,
    val visualTree: CommentTree,
    val flowDirection: FlowDirection,
    val branchingPattern: BranchingPattern
)

data class ThreadComment(
    val id: Long,
    val by: String?,
    val time: Long,
    val text: String?,
    val kids: List<Long>?,
    val parent: Long?,
    val depth: Int,
    val threadPosition: ThreadPosition,
    val sentiment: CommentSentiment,
    val influence: CommentInfluence,
    val branchType: BranchType,
    val visualProperties: CommentVisualProperties
)

data class ThreadPosition(
    val x: Float,
    val y: Float,
    val depth: Int,
    val branchIndex: Int,
    val siblingIndex: Int,
    val angle: Float
)

data class CommentSentiment(
    val polarity: Float, // -1.0 to 1.0
    val subjectivity: Float, // 0.0 to 1.0
    val emotion: CommentEmotion,
    val toxicity: Float, // 0.0 to 1.0
    val constructiveness: Float, // 0.0 to 1.0
    val agreement: Float // -1.0 to 1.0 (with parent)
)

enum class CommentEmotion(val color: Color, val displayName: String) {
    POSITIVE(NeonGreen, "Positive"),
    NEGATIVE(NeonRed, "Negative"),
    NEUTRAL(TextSecondary, "Neutral"),
    EXCITED(NeonYellow, "Excited"),
    FRUSTRATED(CyberpunkRed, "Frustrated"),
    CURIOUS(NeonBlue, "Curious"),
    ANALYTICAL(NeonPurple, "Analytical"),
    SUPPORTIVE(NeonGreen, "Supportive"),
    CRITICAL(CyberpunkOrange, "Critical")
}

data class CommentInfluence(
    val replyCount: Int,
    val mentionCount: Int,
    val agreementRatio: Float,
    val controversyLevel: Float,
    val threadDivergence: Float, // How much this comment changed discussion direction
    val influenceScore: Float, // Overall influence on the discussion
    val viralityIndex: Float // How likely this comment is to be shared/referenced
)

enum class BranchType(val color: Color, val strokeWidth: Float) {
    MAIN_THREAD(NeonBlue, 3f),
    SIDE_DISCUSSION(NeonPurple, 2f),
    ARGUMENT_BRANCH(NeonRed, 2.5f),
    QUESTION_BRANCH(NeonYellow, 2f),
    SUPPORT_BRANCH(NeonGreen, 2f),
    TECHNICAL_BRANCH(CyberpunkOrange, 2f),
    DEAD_END(TextSecondary, 1f)
}

data class CommentVisualProperties(
    val nodeSize: Float,
    val glowIntensity: Float,
    val pulseRate: Float,
    val connectionStyle: ConnectionStyle,
    val textScrollSpeed: Float,
    val matrixTrailLength: Int,
    val hologramEffect: Boolean
)

enum class ConnectionStyle {
    SOLID,
    DASHED,
    DOTTED,
    ANIMATED_FLOW,
    PULSING,
    BRANCHING_TREE
}

data class ThreadMetrics(
    val totalComments: Int,
    val maxDepth: Int,
    val branchingFactor: Float,
    val averageResponseTime: Long,
    val discussionVelocity: Float,
    val consensusLevel: Float,
    val controversyHotspots: List<CommentCluster>,
    val echoChambersDetected: List<EchoChamber>,
    val keyInfluencers: List<InfluentialCommenter>
)

data class CommentCluster(
    val centerCommentId: Long,
    val commentIds: List<Long>,
    val clusterType: ClusterType,
    val intensity: Float,
    val duration: Long
)

enum class ClusterType(val color: Color, val displayName: String) {
    AGREEMENT_CLUSTER(NeonGreen, "Agreement"),
    CONTROVERSY_CLUSTER(NeonRed, "Controversy"),
    QUESTION_CLUSTER(NeonYellow, "Questions"),
    TECHNICAL_CLUSTER(NeonBlue, "Technical"),
    OFF_TOPIC_CLUSTER(TextSecondary, "Off-topic")
}

data class EchoChamber(
    val participantIds: List<String>,
    val commentIds: List<Long>,
    val echoStrength: Float,
    val isolationLevel: Float,
    val topicFocus: String
)

data class InfluentialCommenter(
    val userId: String,
    val commentId: Long,
    val influenceScore: Float,
    val responseGenerated: Int,
    val discussionShift: Float
)

data class CommentTree(
    val rootNode: CommentNode,
    val maxWidth: Float,
    val maxHeight: Float,
    val layoutAlgorithm: TreeLayoutAlgorithm,
    val animationSequence: List<AnimationKeyframe>
)

data class CommentNode(
    val comment: ThreadComment,
    val children: MutableList<CommentNode>,
    var parent: CommentNode?,
    val visualBounds: NodeBounds,
    val connections: List<NodeConnection>
)

data class NodeBounds(
    val x: Float,
    val y: Float,
    val width: Float,
    val height: Float,
    val radius: Float
)

data class NodeConnection(
    val fromNodeId: Long,
    val toNodeId: Long,
    val connectionType: ConnectionType,
    val strength: Float,
    val animationDelay: Long
)

// ConnectionType moved to SharedEnums.kt

enum class TreeLayoutAlgorithm {
    RADIAL_TREE,
    HIERARCHICAL_TREE,
    FORCE_DIRECTED,
    MATRIX_FLOW,
    SPIRAL_LAYOUT,
    NEURAL_NETWORK
}

data class AnimationKeyframe(
    val timestamp: Long,
    val nodeId: Long,
    val animationType: AnimationType,
    val startValue: Float,
    val endValue: Float,
    val duration: Long,
    val easing: AnimationEasing
)

enum class AnimationType {
    FADE_IN,
    SCALE_UP,
    GLOW_PULSE,
    MATRIX_RAIN,
    BRANCH_GROW,
    TEXT_SCROLL,
    CONNECTION_FLOW
}

enum class AnimationEasing {
    LINEAR,
    EASE_IN,
    EASE_OUT,
    EASE_IN_OUT,
    BOUNCE,
    ELASTIC
}

enum class FlowDirection {
    TOP_TO_BOTTOM,
    BOTTOM_TO_TOP,
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT,
    RADIAL_OUTWARD,
    RADIAL_INWARD,
    SPIRAL_CLOCKWISE,
    SPIRAL_COUNTER_CLOCKWISE
}

enum class BranchingPattern {
    BINARY_TREE,
    N_ARY_TREE,
    GRAPH_NETWORK,
    MATRIX_GRID,
    ORGANIC_GROWTH,
    NEURAL_DENDRITE
}

data class MatrixDataStream(
    val commentId: Long,
    val textChunks: List<String>,
    val scrollSpeed: Float,
    val color: Color,
    val opacity: Float,
    val trailLength: Int,
    val glitchEffect: Boolean
)

data class CommentAnalytics(
    val readingTime: Long,
    val comprehensionScore: Float,
    val emotionalImpact: Float,
    val factualAccuracy: Float,
    val languageComplexity: Float,
    val culturalReferences: List<String>,
    val technicalTerms: List<String>,
    val rhetoricalDevices: List<String>
)

data class DiscussionFlow(
    val timeline: List<DiscussionEvent>,
    val peakActivity: Long,
    val discussionPhases: List<DiscussionPhase>,
    val topicEvolution: List<TopicShift>,
    val participantFlow: List<ParticipantActivity>
)

data class DiscussionEvent(
    val timestamp: Long,
    val eventType: EventType,
    val commentId: Long,
    val impact: Float,
    val participantIds: List<String>
)

enum class EventType {
    COMMENT_POSTED,
    THREAD_STARTED,
    CONTROVERSY_SPIKE,
    CONSENSUS_REACHED,
    TOPIC_SHIFT,
    PARTICIPANT_JOIN,
    PARTICIPANT_LEAVE,
    ECHO_CHAMBER_FORMED,
    BRIDGE_COMMENT,
    DISCUSSION_PEAK
}

data class DiscussionPhase(
    val startTime: Long,
    val endTime: Long,
    val phaseType: PhaseType,
    val dominantSentiment: CommentEmotion,
    val keyTopics: List<String>,
    val participantCount: Int
)

enum class PhaseType {
    INITIAL_RESPONSE,
    DEEP_DISCUSSION,
    HEATED_DEBATE,
    RESOLUTION_ATTEMPT,
    CONSENSUS_BUILDING,
    TOPIC_DRIFT,
    DISCUSSION_DECAY
}

data class TopicShift(
    val timestamp: Long,
    val fromTopic: String,
    val toTopic: String,
    val triggerCommentId: Long,
    val shiftMagnitude: Float
)

data class ParticipantActivity(
    val userId: String,
    val joinTime: Long,
    val leaveTime: Long?,
    val commentCount: Int,
    val influenceLevel: Float,
    val topicContributions: List<String>
)