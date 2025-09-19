package com.hackernews.futuristic.data.model

import androidx.compose.ui.graphics.Color
import com.hackernews.futuristic.ui.theme.*

data class QuantumSearchQuery(
    val query: String,
    val searchDimensions: List<SearchDimension>,
    val timeRange: TimeRange,
    val quantumFilters: List<QuantumFilter>,
    val searchMode: SearchMode,
    val probabilityThreshold: Float = 0.5f
)

data class QuantumSearchResult(
    val stories: List<Story>,
    val searchMetrics: SearchMetrics,
    val suggestions: List<SearchSuggestion>,
    val searchPaths: List<SearchPath>,
    val quantumState: QuantumState,
    val executionTime: Long
)

enum class SearchDimension(val displayName: String, val color: Color) {
    TITLE("Title", NeonBlue),
    CONTENT("Content", NeonGreen),
    AUTHOR("Author", NeonPurple),
    COMMENTS("Comments", NeonYellow),
    SENTIMENT("Sentiment", CyberpunkOrange),
    POPULARITY("Popularity", NeonRed),
    RECENCY("Recency", NeonCyan),
    TECHNICAL_DEPTH("Technical Depth", CyberpunkMagenta),
    CONTROVERSY("Controversy", CyberpunkRed),
    INNOVATION("Innovation", NeonWhite)
}

data class TimeRange(
    val startTime: Long?,
    val endTime: Long?,
    val relativeRange: RelativeTimeRange?
)

enum class RelativeTimeRange(val displayName: String, val hours: Long) {
    TODAY("Today", 24),
    LAST_WEEK("Last Week", 168),
    LAST_MONTH("Last Month", 720),
    LAST_YEAR("Last Year", 8760),
    ALL_TIME("All Time", -1)
}

data class QuantumFilter(
    val dimension: SearchDimension,
    val operator: FilterOperator,
    val value: Any,
    val weight: Float = 1.0f,
    val quantumUncertainty: Float = 0.1f
)

// FilterOperator moved to SharedEnums.kt

enum class SearchMode(val displayName: String, val description: String) {
    CLASSICAL("Classical", "Traditional keyword-based search"),
    QUANTUM("Quantum", "Probability-based multi-dimensional search"),
    NEURAL("Neural", "AI-powered semantic search"),
    HYBRID("Hybrid", "Combined classical and quantum approach"),
    PREDICTIVE("Predictive", "Future-trend prediction search"),
    DIMENSIONAL("Dimensional", "Cross-dimensional content discovery")
}

data class SearchMetrics(
    val totalResults: Int,
    val searchAccuracy: Float,
    val quantumCoherence: Float,
    val dimensionalCoverage: Float,
    val temporalRelevance: Float,
    val semanticDepth: Float,
    val uncertaintyLevel: Float,
    val searchComplexity: Int
)

data class SearchSuggestion(
    val query: String,
    val confidence: Float,
    val suggestionType: SuggestionType,
    val reasoning: String,
    val estimatedResults: Int,
    val dimensions: List<SearchDimension>
)

enum class SuggestionType(val displayName: String, val color: Color) {
    AUTO_COMPLETE("Auto Complete", NeonBlue),
    SEMANTIC_EXPANSION("Semantic Expansion", NeonGreen),
    TREND_BASED("Trend Based", NeonYellow),
    USER_PATTERN("User Pattern", NeonPurple),
    QUANTUM_LEAP("Quantum Leap", NeonRed),
    CROSS_DIMENSIONAL("Cross Dimensional", NeonCyan)
}

data class SearchPath(
    val pathId: String,
    val steps: List<SearchStep>,
    val probability: Float,
    val dimensionalTraversal: List<SearchDimension>,
    val quantumEntanglement: Float
)

data class SearchStep(
    val stepId: String,
    val query: String,
    val dimension: SearchDimension,
    val transformation: String,
    val confidence: Float
)

data class QuantumState(
    val superpositionLevels: Map<SearchDimension, Float>,
    val entanglementMatrix: Array<FloatArray>,
    val coherenceTime: Long,
    val observationCount: Int,
    val quantumInterference: Float
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as QuantumState
        
        if (superpositionLevels != other.superpositionLevels) return false
        if (!entanglementMatrix.contentDeepEquals(other.entanglementMatrix)) return false
        if (coherenceTime != other.coherenceTime) return false
        if (observationCount != other.observationCount) return false
        if (quantumInterference != other.quantumInterference) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        var result = superpositionLevels.hashCode()
        result = 31 * result + entanglementMatrix.contentDeepHashCode()
        result = 31 * result + coherenceTime.hashCode()
        result = 31 * result + observationCount
        result = 31 * result + quantumInterference.hashCode()
        return result
    }
}

data class SearchAnalytics(
    val queryFrequency: Map<String, Int>,
    val popularDimensions: List<SearchDimension>,
    val userSearchPatterns: List<SearchPattern>,
    val quantumEfficiency: Float,
    val predictionAccuracy: Float
)

data class SearchPattern(
    val patternId: String,
    val userId: String?,
    val querySequence: List<String>,
    val dimensionFlow: List<SearchDimension>,
    val timePattern: List<Long>,
    val successRate: Float
)

data class SemanticVector(
    val dimensions: Int,
    val values: FloatArray,
    val magnitude: Float,
    val category: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as SemanticVector
        
        if (dimensions != other.dimensions) return false
        if (!values.contentEquals(other.values)) return false
        if (magnitude != other.magnitude) return false
        if (category != other.category) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        var result = dimensions
        result = 31 * result + values.contentHashCode()
        result = 31 * result + magnitude.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }
}

data class QuantumIndex(
    val indexId: String,
    val dimension: SearchDimension,
    val quantumStates: Map<String, QuantumIndexState>,
    val entanglements: List<QuantumEntanglement>,
    val lastUpdate: Long,
    val coherenceLevel: Float
)

data class QuantumIndexState(
    val term: String,
    val probability: Float,
    val superposition: List<String>,
    val observationHistory: List<Long>,
    val quantumWeight: Float
)

data class QuantumEntanglement(
    val term1: String,
    val term2: String,
    val entanglementStrength: Float,
    val sharedDimensions: List<SearchDimension>,
    val correlationHistory: List<Float>
)

data class PredictiveSearchTrend(
    val query: String,
    val currentPopularity: Float,
    val predictedPopularity: Float,
    val trendDirection: TrendDirection,
    val confidence: Float,
    val timeHorizon: Long,
    val influencingFactors: List<String>
)

// TrendDirection moved to SharedEnums.kt

data class CrossDimensionalLink(
    val sourceStory: Story,
    val targetStory: Story,
    val linkType: LinkType,
    val strength: Float,
    val dimensions: List<SearchDimension>,
    val quantumProbability: Float
)

enum class LinkType(val displayName: String, val color: Color) {
    SEMANTIC_SIMILARITY("Semantic Similarity", NeonBlue),
    AUTHOR_CONNECTION("Author Connection", NeonPurple),
    TEMPORAL_PROXIMITY("Temporal Proximity", NeonGreen),
    COMMENT_OVERLAP("Comment Overlap", NeonYellow),
    TOPIC_EVOLUTION("Topic Evolution", NeonCyan),
    CONTROVERSY_CHAIN("Controversy Chain", NeonRed),
    TECHNICAL_THREAD("Technical Thread", CyberpunkOrange),
    QUANTUM_ENTANGLEMENT("Quantum Entanglement", NeonWhite)
}

data class SearchVisualization(
    val searchSpace: SearchSpace,
    val dimensionalProjections: List<DimensionalProjection>,
    val quantumFieldMap: QuantumFieldMap,
    val resultClusters: List<ResultCluster>,
    val searchTrajectory: List<SearchTrajectoryPoint>
)

data class SearchSpace(
    val dimensions: Int,
    val bounds: List<Pair<Float, Float>>,
    val quantumUncertaintyRegions: List<UncertaintyRegion>,
    val densityMap: Map<String, Float>
)

data class DimensionalProjection(
    val fromDimension: SearchDimension,
    val toDimension: SearchDimension,
    val projectionMatrix: Array<FloatArray>,
    val informationLoss: Float
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as DimensionalProjection
        
        if (fromDimension != other.fromDimension) return false
        if (toDimension != other.toDimension) return false
        if (!projectionMatrix.contentDeepEquals(other.projectionMatrix)) return false
        if (informationLoss != other.informationLoss) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        var result = fromDimension.hashCode()
        result = 31 * result + toDimension.hashCode()
        result = 31 * result + projectionMatrix.contentDeepHashCode()
        result = 31 * result + informationLoss.hashCode()
        return result
    }
}

data class QuantumFieldMap(
    val fieldStrength: Array<FloatArray>,
    val quantumFluctuations: Array<FloatArray>,
    val coherenceRegions: List<CoherenceRegion>,
    val interferencePatterns: List<InterferencePattern>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        
        other as QuantumFieldMap
        
        if (!fieldStrength.contentDeepEquals(other.fieldStrength)) return false
        if (!quantumFluctuations.contentDeepEquals(other.quantumFluctuations)) return false
        if (coherenceRegions != other.coherenceRegions) return false
        if (interferencePatterns != other.interferencePatterns) return false
        
        return true
    }
    
    override fun hashCode(): Int {
        var result = fieldStrength.contentDeepHashCode()
        result = 31 * result + quantumFluctuations.contentDeepHashCode()
        result = 31 * result + coherenceRegions.hashCode()
        result = 31 * result + interferencePatterns.hashCode()
        return result
    }
}

data class UncertaintyRegion(
    val centerX: Float,
    val centerY: Float,
    val radius: Float,
    val uncertaintyLevel: Float,
    val affectedDimensions: List<SearchDimension>
)

data class CoherenceRegion(
    val boundary: List<Pair<Float, Float>>,
    val coherenceLevel: Float,
    val stability: Float,
    val resonantFrequency: Float
)

data class InterferencePattern(
    val patternType: InterferenceType,
    val nodes: List<Pair<Float, Float>>,
    val amplitude: Float,
    val frequency: Float
)

enum class InterferenceType {
    CONSTRUCTIVE,
    DESTRUCTIVE,
    PARTIAL,
    QUANTUM_SUPERPOSITION
}

data class ResultCluster(
    val centroid: List<Float>,
    val stories: List<Story>,
    val clusterRadius: Float,
    val coherence: Float,
    val dominantDimensions: List<SearchDimension>
)

data class SearchTrajectoryPoint(
    val timestamp: Long,
    val position: List<Float>,
    val velocity: List<Float>,
    val quantumState: String,
    val dimensionWeights: Map<SearchDimension, Float>
)