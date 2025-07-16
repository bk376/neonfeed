package com.hackernews.futuristic.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.Comment
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.model.BiometricReadingData
import com.hackernews.futuristic.data.model.ReadingSession
import com.hackernews.futuristic.data.model.CognitiveMetrics
import com.hackernews.futuristic.data.model.AttentionPattern
import com.hackernews.futuristic.ui.components.BiometricReadingDisplay
import com.hackernews.futuristic.ui.theme.*
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Biotech

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleStoryDetailScreen(
    storyId: Long,
    onBackClick: () -> Unit,
    onUrlClick: ((String, String) -> Unit)? = null,
    viewModel: StoryDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    // Biometric reading state
    var isBiometricEnabled by remember { mutableStateOf(false) }
    var readingStartTime by remember { mutableStateOf(0L) }
    var isCurrentlyReading by remember { mutableStateOf(false) }
    
    LaunchedEffect(storyId) {
        viewModel.loadStory(storyId)
        // Start reading session when story loads
        readingStartTime = System.currentTimeMillis()
        isCurrentlyReading = true
    }
    
    // Stop reading when leaving the screen
    DisposableEffect(Unit) {
        onDispose {
            isCurrentlyReading = false
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        DarkBackground,
                        DarkSurface
                    )
                )
            )
    ) {
        TopAppBar(
            title = { Text("Story Details") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = LightText
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = { isBiometricEnabled = !isBiometricEnabled }
                ) {
                    Icon(
                        Icons.Default.Biotech,
                        contentDescription = "Toggle Biometric Reading",
                        tint = if (isBiometricEnabled) NeonGreen else LightText
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = DarkSurface,
                titleContentColor = LightText
            )
        )
        
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = NeonBlue)
                }
            }
            uiState.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = uiState.error ?: "Unknown error",
                            color = ErrorRed
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = viewModel::retry,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = NeonBlue
                            )
                        ) {
                            Text("Retry")
                        }
                    }
                }
            }
            uiState.story != null -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                    item {
                        SimpleStoryHeader(
                            story = uiState.story!!,
                            onUrlClick = onUrlClick
                        )
                    }
                    
                    uiState.story?.text?.let { text ->
                        if (text.isNotBlank()) {
                            item {
                                SimpleStoryContent(text = text)
                            }
                        }
                    }
                    
                    item {
                        Text(
                            text = "Comments (${uiState.comments.size})",
                            color = NeonBlue,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    // Build comment tree and display
                    val commentTree = buildCommentTree(uiState.comments)
                    items(
                        items = commentTree,
                        key = { commentNode -> commentNode.comment.id }
                    ) { commentNode ->
                        ThreadedCommentCard(
                            commentNode = commentNode,
                            depth = 0
                        )
                    }
                } // End LazyColumn
                    
                // Biometric Reading Display Overlay
                if (isBiometricEnabled && isCurrentlyReading) {
                    val currentTime = System.currentTimeMillis()
                    val readingDuration = (currentTime - readingStartTime) / 1000f
                    
                    // Simulate biometric data (in a real app, this would come from sensors)
                    val biometricData = BiometricReadingData(
                        timestamp = currentTime,
                        heartRate = (65 + kotlin.math.sin(readingDuration / 10.0) * 15).toInt(),
                        eyeMovement = 0.7f + kotlin.math.sin(readingDuration / 3.0).toFloat() * 0.2f,
                        blinkRate = 0.3f + kotlin.math.cos(readingDuration / 5.0).toFloat() * 0.1f,
                        focusIntensity = 0.8f + kotlin.math.sin(readingDuration / 7.0).toFloat() * 0.15f,
                        comprehensionLevel = 0.75f + kotlin.math.cos(readingDuration / 8.0).toFloat() * 0.1f,
                        stressLevel = 0.2f + kotlin.math.sin(readingDuration / 12.0).toFloat() * 0.1f,
                        interestLevel = 0.85f + kotlin.math.cos(readingDuration / 6.0).toFloat() * 0.1f,
                        cognitiveLoad = 0.6f + kotlin.math.sin(readingDuration / 4.0).toFloat() * 0.2f
                    )
                    
                    val cognitiveMetrics = CognitiveMetrics(
                        averageHeartRate = 70f + kotlin.math.sin(readingDuration / 15.0).toFloat() * 10f,
                        peakHeartRate = 85f + kotlin.math.sin(readingDuration / 8.0).toFloat() * 15f,
                        averageFocusIntensity = 0.8f,
                        peakFocusIntensity = 0.95f,
                        averageComprehension = 0.75f,
                        averageStressLevel = 0.2f,
                        averageInterestLevel = 0.85f,
                        cognitiveEfficiency = 0.82f
                    )
                    
                    val attentionPattern = AttentionPattern(
                        attentionSpan = (readingDuration * 1000).toLong(),
                        focusBreaks = (readingDuration / 45).toInt(),
                        deepFocusPeriods = (readingDuration / 60).toInt() + 1,
                        distractionEvents = (readingDuration / 90).toInt(),
                        readingVelocity = 0.75f + kotlin.math.sin(readingDuration / 20.0).toFloat() * 0.15f,
                        comprehensionRate = 0.8f
                    )
                    
                    val readingSession = ReadingSession(
                        storyId = storyId,
                        startTime = readingStartTime,
                        endTime = null,
                        totalReadingTime = (currentTime - readingStartTime),
                        cognitiveMetrics = cognitiveMetrics,
                        attentionPattern = attentionPattern,
                        biometricData = listOf(biometricData)
                    )
                    
                    BiometricReadingDisplay(
                        biometricData = biometricData,
                        readingSession = readingSession,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .width(300.dp)
                            .height(200.dp)
                    )
                }
            } // End Box
            }
        }
    }
}

@Composable
private fun SimpleStoryHeader(
    story: Story,
    onUrlClick: ((String, String) -> Unit)? = null
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            story.title?.let { title ->
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = LightText,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                story.by?.let { author ->
                    Text(
                        text = "by $author",
                        style = MaterialTheme.typography.bodyMedium,
                        color = NeonPurple
                    )
                }
                
                Text(
                    text = "Score: ${story.score ?: 0}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = NeonGreen
                )
            }
            
            // Clickable "Read Full Article" section for external URLs
            story.url?.let { url ->
                Spacer(modifier = Modifier.height(16.dp))
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { 
                            onUrlClick?.invoke(url, story.title ?: "Untitled")
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = NeonBlue.copy(alpha = 0.15f)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Icon(
                            Icons.Default.OpenInNew,
                            contentDescription = "Read Full Article",
                            tint = NeonBlue,
                            modifier = Modifier.size(20.dp)
                        )
                        
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Read Full Article",
                                color = NeonBlue,
                                fontSize = 14.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = FontWeight.Bold
                            )
                            
                            Text(
                                text = try {
                                    java.net.URL(url).host
                                } catch (e: Exception) {
                                    "External source"
                                },
                                color = TextSecondary,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont
                            )
                        }
                        
                        Icon(
                            Icons.Default.KeyboardArrowUp,
                            contentDescription = null,
                            tint = NeonBlue.copy(alpha = 0.6f),
                            modifier = Modifier
                                .size(16.dp)
                                .rotate(45f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SimpleStoryContent(text: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Content",
                style = MaterialTheme.typography.titleMedium,
                color = NeonGreen,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            val displayText = parseHtmlContent(text, maxLength = 1500)
            
            Text(
                text = displayText,
                style = MaterialTheme.typography.bodyMedium,
                color = LightText,
                maxLines = 15,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun SimpleCommentCard(comment: Comment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = comment.by ?: "Anonymous",
                    style = MaterialTheme.typography.labelMedium,
                    color = NeonPurple,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = formatTimeAgo(comment.time),
                    style = MaterialTheme.typography.bodySmall,
                    color = DimText
                )
            }
            
            comment.text?.let { text ->
                if (text.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    val displayText = parseHtmlContent(text, maxLength = 600)
                    
                    Text(
                        text = displayText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = LightText,
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

// Comment tree data structure
data class CommentNode(
    val comment: Comment,
    val children: MutableList<CommentNode> = mutableListOf()
)

private fun buildCommentTree(comments: List<Comment>): List<CommentNode> {
    val nodeMap = mutableMapOf<Long, CommentNode>()
    val rootNodes = mutableListOf<CommentNode>()
    
    // Create nodes for all comments
    comments.forEach { comment ->
        nodeMap[comment.id] = CommentNode(comment)
    }
    
    // Build tree structure
    comments.forEach { comment ->
        val node = nodeMap[comment.id]!!
        
        if (comment.parent != null) {
            val parentNode = nodeMap[comment.parent]
            if (parentNode != null) {
                parentNode.children.add(node)
            } else {
                // Parent not found, treat as root
                rootNodes.add(node)
            }
        } else {
            // Root level comment
            rootNodes.add(node)
        }
    }
    
    return rootNodes
}

@Composable
private fun ThreadedCommentCard(
    commentNode: CommentNode,
    depth: Int,
    maxDepth: Int = 6
) {
    var isExpanded by remember { mutableStateOf(true) }
    val comment = commentNode.comment
    
    Column {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = (depth * 24).dp,
                    top = 4.dp,
                    end = 4.dp,
                    bottom = 4.dp
                ),
            colors = CardDefaults.cardColors(
                containerColor = when (depth % 3) {
                    0 -> DarkCard
                    1 -> DarkSurface.copy(alpha = 0.9f)
                    else -> DarkBackground.copy(alpha = 0.8f)
                }
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                // Depth indicator
                if (depth > 0) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        repeat(depth) { level ->
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(
                                        when (level % 4) {
                                            0 -> NeonBlue.copy(alpha = 0.6f)
                                            1 -> NeonPurple.copy(alpha = 0.6f)
                                            2 -> NeonGreen.copy(alpha = 0.6f)
                                            else -> NeonYellow.copy(alpha = 0.6f)
                                        },
                                        shape = RoundedCornerShape(2.dp)
                                    )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                // Header with collapse/expand button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = comment.by ?: "Anonymous",
                            style = MaterialTheme.typography.labelMedium,
                            color = when (depth % 4) {
                                0 -> NeonPurple
                                1 -> NeonBlue
                                2 -> NeonGreen
                                else -> NeonYellow
                            },
                            fontWeight = FontWeight.Bold
                        )
                        
                        if (commentNode.children.isNotEmpty()) {
                            Text(
                                text = "(${commentNode.children.size} replies)",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary
                            )
                        }
                    }
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = formatTimeAgo(comment.time),
                            style = MaterialTheme.typography.bodySmall,
                            color = DimText
                        )
                        
                        if (commentNode.children.isNotEmpty()) {
                            IconButton(
                                onClick = { isExpanded = !isExpanded },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                                    tint = NeonBlue,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
                
                // Comment content
                comment.text?.let { text ->
                    if (text.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        val displayText = text
                            .replace("<p>", "\n")
                            .replace("</p>", "")
                            .replace("<br>", "\n")
                            .replace("&gt;", ">")
                            .replace("&lt;", "<")
                            .replace("&amp;", "&")
                            .replace("&#x27;", "'")
                            .replace("&quot;", "\"")
                            .take(800)
                        
                        Text(
                            text = displayText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = LightText,
                            maxLines = if (depth > 3) 4 else 8,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
        
        // Child comments
        if (isExpanded && commentNode.children.isNotEmpty() && depth < maxDepth) {
            commentNode.children.forEach { childNode ->
                ThreadedCommentCard(
                    commentNode = childNode,
                    depth = depth + 1,
                    maxDepth = maxDepth
                )
            }
        } else if (commentNode.children.isNotEmpty() && depth >= maxDepth) {
            // Show "View more replies" button for deeply nested comments
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = ((depth + 1) * 16).dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
                    .clickable { /* TODO: Navigate to detailed comment view */ },
                colors = CardDefaults.cardColors(containerColor = DarkBorder),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = "View ${commentNode.children.size} more replies...",
                    color = NeonBlue,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

private fun formatTimeAgo(timestamp: Long): String {
    val now = System.currentTimeMillis() / 1000
    val diff = now - timestamp
    
    return when {
        diff < 60 -> "now"
        diff < 3600 -> "${diff / 60}m ago"
        diff < 86400 -> "${diff / 3600}h ago"
        else -> "${diff / 86400}d ago"
    }
}

// Utility function for consistent HTML parsing
private fun parseHtmlContent(htmlText: String, maxLength: Int = 600): String {
    return try {
        val htmlParsed = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_COMPACT)
        htmlParsed.toString()
            .trim()
            .replace(Regex("\\s+"), " ") // Replace multiple whitespaces with single space
            .replace(Regex("\\n\\s*\\n"), "\n") // Replace multiple newlines with single newline
            .take(maxLength)
    } catch (e: Exception) {
        // Fallback to simple text cleaning if HTML parsing fails
        htmlText
            .replace(Regex("<[^>]*>"), "") // Remove all HTML tags
            .replace("&gt;", ">")
            .replace("&lt;", "<")
            .replace("&amp;", "&")
            .replace("&#x27;", "'")
            .replace("&quot;", "\"")
            .trim()
            .replace(Regex("\\s+"), " ")
            .take(maxLength)
    }
}