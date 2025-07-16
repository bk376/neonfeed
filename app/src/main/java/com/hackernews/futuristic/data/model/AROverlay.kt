package com.hackernews.futuristic.data.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.hackernews.futuristic.ui.theme.*

data class AROverlayData(
    val markers: List<ARMarker>,
    val infoPanels: List<ARInfoPanel>,
    val scanLines: List<ARScanLine> = emptyList(),
    val targetingSystem: ARTargetingSystem? = null,
    val hudElements: List<ARHUDElement> = emptyList()
)

data class ARMarker(
    val id: String,
    val label: String,
    val position: Offset,
    val color: Color,
    val data: Map<String, String> = emptyMap(),
    val markerType: ARMarkerType = ARMarkerType.POINT,
    val size: Float = 1.0f,
    val isVisible: Boolean = true,
    val animationType: ARAnimationType = ARAnimationType.PULSE
)

enum class ARMarkerType {
    POINT,
    CIRCLE,
    SQUARE,
    TRIANGLE,
    DIAMOND,
    CROSSHAIR
}

enum class ARAnimationType {
    PULSE,
    ROTATE,
    SCALE,
    GLOW,
    BLINK,
    STATIC
}

data class ARInfoPanel(
    val id: String,
    val title: String,
    val position: Offset,
    val color: Color,
    val icon: ImageVector,
    val content: List<String>,
    val metrics: Map<String, String> = emptyMap(),
    val panelType: ARPanelType = ARPanelType.INFO,
    val isExpanded: Boolean = true,
    val opacity: Float = 0.9f
)

enum class ARPanelType {
    INFO,
    METRICS,
    CONTROLS,
    ANALYSIS,
    ALERTS,
    DIAGNOSTICS
}

data class ARScanLine(
    val id: String,
    val startPosition: Offset,
    val endPosition: Offset,
    val color: Color,
    val thickness: Float = 1.0f,
    val animationSpeed: Float = 1.0f,
    val scanType: ARScanType = ARScanType.HORIZONTAL
)

enum class ARScanType {
    HORIZONTAL,
    VERTICAL,
    DIAGONAL,
    CIRCULAR,
    RADAR
}

data class ARTargetingSystem(
    val centerPosition: Offset,
    val targetRadius: Float,
    val crosshairColor: Color = NeonGreen,
    val ringColor: Color = NeonBlue,
    val isLocked: Boolean = false,
    val targetType: ARTargetType = ARTargetType.STORY,
    val lockOnTime: Long = 0L
)

enum class ARTargetType {
    STORY,
    COMMENT,
    USER,
    LINK,
    METADATA
}

data class ARHUDElement(
    val id: String,
    val position: Offset,
    val content: String,
    val color: Color,
    val fontSize: Float = 12f,
    val elementType: ARHUDElementType = ARHUDElementType.TEXT,
    val isVisible: Boolean = true
)

enum class ARHUDElementType {
    TEXT,
    ICON,
    PROGRESS_BAR,
    GRAPH,
    ALERT,
    STATUS_INDICATOR
}

data class ARInteractionEvent(
    val type: ARInteractionType,
    val targetId: String,
    val position: Offset,
    val timestamp: Long = System.currentTimeMillis(),
    val data: Map<String, Any> = emptyMap()
)

enum class ARInteractionType {
    TAP,
    LONG_PRESS,
    DRAG_START,
    DRAG_END,
    PINCH_ZOOM,
    ROTATION,
    HOVER,
    FOCUS,
    UNFOCUS
}

data class ARConfiguration(
    val isEnabled: Boolean = false,
    val gridEnabled: Boolean = true,
    val scanlinesEnabled: Boolean = true,
    val markersEnabled: Boolean = true,
    val infoPanelsEnabled: Boolean = true,
    val hudEnabled: Boolean = true,
    val targetingEnabled: Boolean = true,
    val opacity: Float = 0.8f,
    val animationSpeed: Float = 1.0f,
    val colorScheme: ARColorScheme = ARColorScheme.CYBERPUNK
)

enum class ARColorScheme {
    CYBERPUNK,
    MATRIX,
    HOLOGRAM,
    NEON,
    MILITARY,
    MEDICAL
}

data class ARMetrics(
    val framerate: Int,
    val latency: Long,
    val renderTime: Long,
    val objectCount: Int,
    val memoryUsage: Long,
    val batteryImpact: Float
)

data class ARStoryEnhancement(
    val storyId: Long,
    val sentimentVisualization: SentimentVisualization,
    val topicClouds: List<TopicCloud>,
    val connectionLines: List<ConnectionLine>,
    val influenceNodes: List<InfluenceNode>,
    val trendingIndicators: List<TrendingIndicator>
)

data class SentimentVisualization(
    val position: Offset,
    val sentiment: Float, // -1.0 to 1.0
    val confidence: Float,
    val color: Color,
    val particleCount: Int = 20
)

data class TopicCloud(
    val position: Offset,
    val topics: List<String>,
    val weights: List<Float>,
    val color: Color,
    val radius: Float
)

data class ConnectionLine(
    val fromPosition: Offset,
    val toPosition: Offset,
    val connectionType: ConnectionType,
    val strength: Float,
    val color: Color,
    val isAnimated: Boolean = true
)

// ConnectionType moved to SharedEnums.kt

data class InfluenceNode(
    val position: Offset,
    val userId: String,
    val influenceScore: Float,
    val connections: Int,
    val color: Color,
    val pulseIntensity: Float
)

data class TrendingIndicator(
    val position: Offset,
    val trend: TrendDirection,
    val velocity: Float,
    val color: Color,
    val arrowSize: Float
)

// TrendDirection moved to SharedEnums.kt

data class AREffect(
    val id: String,
    val effectType: AREffectType,
    val position: Offset,
    val duration: Long,
    val intensity: Float,
    val color: Color,
    val isActive: Boolean = true
)

enum class AREffectType {
    PARTICLE_BURST,
    ENERGY_WAVE,
    HOLOGRAPHIC_GLITCH,
    MATRIX_RAIN,
    SCANNING_BEAM,
    NEURAL_PULSE,
    QUANTUM_FLICKER
}

data class ARGesture(
    val type: ARGestureType,
    val startPosition: Offset,
    val currentPosition: Offset,
    val velocity: Offset,
    val duration: Long,
    val confidence: Float
)

enum class ARGestureType {
    SWIPE_LEFT,
    SWIPE_RIGHT,
    SWIPE_UP,
    SWIPE_DOWN,
    PINCH_IN,
    PINCH_OUT,
    ROTATE_CLOCKWISE,
    ROTATE_COUNTERCLOCKWISE,
    DOUBLE_TAP,
    TRIPLE_TAP
}

data class ARCalibration(
    val deviceOrientation: DeviceOrientation,
    val lightingConditions: LightingCondition,
    val trackingAccuracy: Float,
    val stabilization: Boolean,
    val autoFocus: Boolean,
    val depthSensing: Boolean
)

enum class DeviceOrientation {
    PORTRAIT,
    LANDSCAPE_LEFT,
    LANDSCAPE_RIGHT,
    PORTRAIT_UPSIDE_DOWN
}

enum class LightingCondition {
    BRIGHT,
    NORMAL,
    DIM,
    DARK,
    MIXED
}

data class ARSession(
    val sessionId: String,
    val startTime: Long,
    val endTime: Long?,
    val interactions: List<ARInteractionEvent>,
    val configuration: ARConfiguration,
    val metrics: ARMetrics,
    val storyEnhancements: List<ARStoryEnhancement>
)

data class ARAnalytics(
    val totalSessions: Int,
    val averageSessionDuration: Long,
    val mostUsedFeatures: List<String>,
    val performanceMetrics: ARMetrics,
    val userPreferences: Map<String, Float>,
    val errorRate: Float
)