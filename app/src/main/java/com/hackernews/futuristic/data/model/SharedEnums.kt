package com.hackernews.futuristic.data.model

import androidx.compose.ui.graphics.Color
import com.hackernews.futuristic.ui.theme.*

enum class TrendDirection(val displayName: String, val symbol: String) {
    UP("Rising", "↗"),
    DOWN("Falling", "↘"),
    STABLE("Stable", "→"),
    VOLATILE("Volatile", "↕")
}

enum class ConnectionType {
    PARENT_CHILD,
    SIBLING,
    REFERENCE,
    AGREEMENT,
    DISAGREEMENT,
    QUESTION_ANSWER,
    SIMILARITY,
    AUTHOR_RELATION,
    TOPIC_RELATION,
    TEMPORAL,
    CAUSAL
}

enum class FilterOperator(val symbol: String, val displayName: String) {
    GREATER_THAN(">", "Greater Than"),
    LESS_THAN("<", "Less Than"),
    EQUALS("=", "Equals"),
    GREATER_THAN_OR_EQUAL(">=", "Greater Than or Equal"),
    LESS_THAN_OR_EQUAL("<=", "Less Than or Equal"),
    NOT_EQUALS("!=", "Not Equals"),
    CONTAINS("contains", "Contains"),
    NOT_CONTAINS("!contains", "Does Not Contain"),
    STARTS_WITH("starts", "Starts With"),
    ENDS_WITH("ends", "Ends With")
}