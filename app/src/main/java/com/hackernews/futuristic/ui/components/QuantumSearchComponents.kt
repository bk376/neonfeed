package com.hackernews.futuristic.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hackernews.futuristic.data.model.*
import com.hackernews.futuristic.ui.theme.*
import kotlin.math.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuantumSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    searchSuggestions: List<SearchSuggestion>,
    onSuggestionClick: (SearchSuggestion) -> Unit,
    isSearching: Boolean = false,
    isRegularMode: Boolean = false,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var showSuggestions by remember { mutableStateOf(false) }
    
    // Animated glow effect
    val glowProgress by rememberInfiniteTransition().animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    
    val searchBarColor by animateColorAsState(
        targetValue = if (isSearching) NeonBlue.copy(alpha = 0.2f) else Color.Black.copy(alpha = 0.9f),
        animationSpec = tween(300)
    )
    
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            NeonBlue.copy(alpha = glowProgress * 0.1f),
                            NeonPurple.copy(alpha = glowProgress * 0.1f),
                            NeonGreen.copy(alpha = glowProgress * 0.1f)
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 2.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            NeonBlue.copy(alpha = glowProgress),
                            NeonPurple.copy(alpha = glowProgress),
                            NeonGreen.copy(alpha = glowProgress)
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = searchBarColor),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Header with cyberpunk styling
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = ">> NEONFEED_QUANTUM_SEARCH",
                            color = NeonGreen,
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold
                        )
                        
                        if (isSearching) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Text(
                                    text = "PROCESSING",
                                    color = NeonBlue,
                                    fontSize = 8.sp,
                                    fontFamily = MonospaceFont
                                )
                                CircularProgressIndicator(
                                    modifier = Modifier.size(12.dp),
                                    color = NeonBlue,
                                    strokeWidth = 1.dp
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    // Search input with cyberpunk styling
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                DarkBackground.copy(alpha = 0.8f),
                                RoundedCornerShape(8.dp)
                            )
                            .border(
                                1.dp,
                                if (query.isNotEmpty()) NeonBlue else DarkBorder,
                                RoundedCornerShape(8.dp)
                            )
                    ) {
                        OutlinedTextField(
                            value = query,
                            onValueChange = { 
                                onQueryChange(it)
                                showSuggestions = it.isNotEmpty()
                            },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(
                                    text = "[ENTER_NEONFEED_QUERY]",
                                    color = TextSecondary.copy(alpha = 0.7f),
                                    fontFamily = MonospaceFont,
                                    fontSize = 14.sp
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = "Search",
                                    tint = NeonBlue,
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            trailingIcon = if (query.isNotEmpty()) {
                                {
                                    IconButton(onClick = { 
                                        onQueryChange("")
                                        showSuggestions = false
                                    }) {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = "Clear",
                                            tint = NeonRed,
                                            modifier = Modifier.size(16.dp)
                                        )
                                    }
                                }
                            } else null,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                focusedTextColor = NeonGreen,
                                unfocusedTextColor = LightText,
                                cursorColor = NeonBlue
                            ),
                            textStyle = androidx.compose.ui.text.TextStyle(
                                fontFamily = MonospaceFont,
                                color = NeonGreen,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    onSearch()
                                    keyboardController?.hide()
                                    showSuggestions = false
                                }
                            ),
                            singleLine = true
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    // Cyberpunk search button
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(
                            onClick = {
                                onSearch()
                                keyboardController?.hide()
                                showSuggestions = false
                            },
                            modifier = Modifier
                                .background(
                                    brush = Brush.horizontalGradient(
                                        colors = listOf(
                                            NeonBlue.copy(alpha = 0.3f),
                                            NeonPurple.copy(alpha = 0.3f)
                                        )
                                    ),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .border(
                                    1.dp,
                                    NeonBlue,
                                    RoundedCornerShape(8.dp)
                                ),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = NeonBlue
                            ),
                            shape = RoundedCornerShape(8.dp),
                            enabled = !isSearching && query.isNotEmpty()
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                if (isSearching) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        color = NeonBlue,
                                        strokeWidth = 2.dp
                                    )
                                    Text(
                                        text = "PROCESSING...",
                                        fontFamily = MonospaceFont,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = NeonBlue
                                    )
                                } else {
                                    Text(
                                        text = ">> EXECUTE_QUANTUM_SEARCH",
                                        fontFamily = MonospaceFont,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        color = if (query.isNotEmpty()) NeonBlue else TextSecondary
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        
        // Search suggestions
        if (showSuggestions && searchSuggestions.isNotEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCard),
                shape = RoundedCornerShape(8.dp)
            ) {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 200.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(searchSuggestions.take(5)) { suggestion ->
                        SearchSuggestionItem(
                            suggestion = suggestion,
                            onClick = {
                                onSuggestionClick(suggestion)
                                showSuggestions = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchSuggestionItem(
    suggestion: SearchSuggestion,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(suggestion.suggestionType.color, CircleShape)
        )
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = suggestion.query,
                color = LightText,
                fontSize = 14.sp,
                fontFamily = MonospaceFont,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            Text(
                text = suggestion.reasoning,
                color = TextSecondary,
                fontSize = 10.sp,
                fontFamily = MonospaceFont,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        
        Text(
            text = "${(suggestion.confidence * 100).toInt()}%",
            color = suggestion.suggestionType.color,
            fontSize = 10.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun QuantumSearchControls(
    searchMode: SearchMode,
    onSearchModeChange: (SearchMode) -> Unit,
    selectedDimensions: List<SearchDimension>,
    onDimensionToggle: (SearchDimension) -> Unit,
    probabilityThreshold: Float,
    onProbabilityChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    
    Column(modifier = modifier) {
        // Search mode selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "SEARCH MODE:",
                color = NeonBlue,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(SearchMode.values()) { mode ->
                    SearchModeChip(
                        mode = mode,
                        isSelected = mode == searchMode,
                        onClick = { onSearchModeChange(mode) }
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            IconButton(
                onClick = { expanded = !expanded },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    Icons.Default.Settings,
                    contentDescription = "Advanced Settings",
                    tint = if (expanded) NeonBlue else TextSecondary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        
        if (expanded) {
            Spacer(modifier = Modifier.height(16.dp))
            
            // Dimensional controls
            QuantumDimensionSelector(
                selectedDimensions = selectedDimensions,
                onDimensionToggle = onDimensionToggle
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Probability threshold
            QuantumProbabilitySlider(
                probability = probabilityThreshold,
                onProbabilityChange = onProbabilityChange
            )
        }
    }
}

@Composable
private fun SearchModeChip(
    mode: SearchMode,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val chipColor by animateColorAsState(
        targetValue = if (isSelected) NeonBlue else DarkSurface,
        animationSpec = tween(200)
    )
    
    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color.Black else NeonBlue,
        animationSpec = tween(200)
    )
    
    Surface(
        modifier = Modifier
            .clickable { onClick() }
            .padding(2.dp),
        shape = RoundedCornerShape(12.dp),
        color = chipColor
    ) {
        Text(
            text = mode.displayName,
            color = textColor,
            fontSize = 10.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun QuantumDimensionSelector(
    selectedDimensions: List<SearchDimension>,
    onDimensionToggle: (SearchDimension) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "SEARCH DIMENSIONS:",
            color = NeonGreen,
            fontSize = 12.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(SearchDimension.values()) { dimension ->
                DimensionChip(
                    dimension = dimension,
                    isSelected = dimension in selectedDimensions,
                    onToggle = { onDimensionToggle(dimension) }
                )
            }
        }
    }
}

@Composable
private fun DimensionChip(
    dimension: SearchDimension,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    val chipColor by animateColorAsState(
        targetValue = if (isSelected) dimension.color.copy(alpha = 0.3f) else DarkCard,
        animationSpec = tween(200)
    )
    
    val borderColor by animateColorAsState(
        targetValue = if (isSelected) dimension.color else TextSecondary,
        animationSpec = tween(200)
    )
    
    Surface(
        modifier = Modifier
            .clickable { onToggle() }
            .border(1.dp, borderColor, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        color = chipColor
    ) {
        Text(
            text = dimension.displayName,
            color = if (isSelected) dimension.color else TextSecondary,
            fontSize = 10.sp,
            fontFamily = MonospaceFont,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun QuantumProbabilitySlider(
    probability: Float,
    onProbabilityChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "QUANTUM THRESHOLD:",
                color = NeonPurple,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "${(probability * 100).toInt()}%",
                color = NeonPurple,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Slider(
            value = probability,
            onValueChange = onProbabilityChange,
            valueRange = 0f..1f,
            colors = SliderDefaults.colors(
                thumbColor = NeonPurple,
                activeTrackColor = NeonPurple,
                inactiveTrackColor = DarkSurface
            )
        )
    }
}

@Composable
fun QuantumSearchResults(
    searchResult: QuantumSearchResult,
    onStoryClick: (Story) -> Unit,
    isBookmarked: (Long) -> Boolean = { false },
    onBookmarkClick: ((Story) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Search metrics
        QuantumSearchMetrics(
            metrics = searchResult.searchMetrics,
            executionTime = searchResult.executionTime
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Quantum state visualization
        QuantumStateVisualization(
            quantumState = searchResult.quantumState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Search paths
        if (searchResult.searchPaths.isNotEmpty()) {
            SearchPathsVisualization(
                searchPaths = searchResult.searchPaths,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        // Results header with enhanced count display
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SEARCH RESULTS",
                color = NeonBlue,
                fontSize = 16.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Card(
                colors = CardDefaults.cardColors(containerColor = NeonBlue.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "${searchResult.stories.size} FOUND",
                    color = NeonBlue,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(searchResult.stories) { story ->
                QuantumStoryCard(
                    story = story,
                    onClick = { onStoryClick(story) },
                    isBookmarked = isBookmarked(story.id),
                    onBookmarkClick = onBookmarkClick?.let { { it(story) } }
                )
            }
        }
    }
}

@Composable
fun QuantumSearchMetrics(
    metrics: SearchMetrics,
    executionTime: Long,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "QUANTUM SEARCH METRICS",
                color = NeonYellow,
                fontSize = 14.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MetricDisplay(
                    label = "ACCURACY",
                    value = "${(metrics.searchAccuracy * 100).toInt()}%",
                    color = NeonGreen
                )
                
                MetricDisplay(
                    label = "COHERENCE",
                    value = "${(metrics.quantumCoherence * 100).toInt()}%",
                    color = NeonBlue
                )
                
                MetricDisplay(
                    label = "COVERAGE",
                    value = "${(metrics.dimensionalCoverage * 100).toInt()}%",
                    color = NeonPurple
                )
                
                MetricDisplay(
                    label = "TIME",
                    value = "${executionTime}ms",
                    color = NeonCyan
                )
            }
            
            // Quantum uncertainty indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "UNCERTAINTY:",
                    color = TextSecondary,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
                
                LinearProgressIndicator(
                    progress = metrics.uncertaintyLevel,
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp),
                    color = NeonRed,
                    trackColor = DarkBackground
                )
                
                Text(
                    text = "${(metrics.uncertaintyLevel * 100).toInt()}%",
                    color = NeonRed,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun MetricDisplay(
    label: String,
    value: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = color,
            fontSize = 12.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 8.sp,
            fontFamily = MonospaceFont
        )
    }
}

@Composable
fun QuantumStateVisualization(
    quantumState: QuantumState,
    modifier: Modifier = Modifier
) {
    val animationProgress by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = Color.Black),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawQuantumFieldVisualization(
                    quantumState = quantumState,
                    animationProgress = animationProgress,
                    size = size
                )
            }
            
            Text(
                text = "QUANTUM STATE SUPERPOSITION",
                color = NeonBlue,
                fontSize = 10.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }
    }
}

private fun DrawScope.drawQuantumFieldVisualization(
    quantumState: QuantumState,
    animationProgress: Float,
    size: androidx.compose.ui.geometry.Size
) {
    val centerX = size.width / 2
    val centerY = size.height / 2
    val maxRadius = minOf(size.width, size.height) / 3
    
    // Draw quantum field background
    drawQuantumField(size, animationProgress)
    
    // Draw superposition levels for each dimension
    SearchDimension.values().forEachIndexed { index, dimension ->
        val angle = (index.toFloat() / SearchDimension.values().size) * 2 * PI
        val superposition = quantumState.superpositionLevels[dimension] ?: 0f
        val radius = maxRadius * superposition
        
        val x = centerX + cos(angle).toFloat() * radius
        val y = centerY + sin(angle).toFloat() * radius
        
        // Draw dimension node
        val nodeRadius = 8f + superposition * 12f
        val alpha = 0.7f + 0.3f * sin(animationProgress * 2 * PI + index).toFloat()
        
        drawCircle(
            color = dimension.color.copy(alpha = alpha),
            radius = nodeRadius,
            center = Offset(x, y)
        )
        
        // Draw connection to center
        drawLine(
            color = dimension.color.copy(alpha = 0.3f),
            start = Offset(centerX, centerY),
            end = Offset(x, y),
            strokeWidth = 2f
        )
        
        // Draw entanglement connections
        quantumState.entanglementMatrix[index].forEachIndexed { otherIndex, entanglement ->
            if (otherIndex > index && entanglement > 0.5f) {
                val otherAngle = (otherIndex.toFloat() / SearchDimension.values().size) * 2 * PI
                val otherSuperposition = quantumState.superpositionLevels[SearchDimension.values()[otherIndex]] ?: 0f
                val otherRadius = maxRadius * otherSuperposition
                
                val otherX = centerX + cos(otherAngle).toFloat() * otherRadius
                val otherY = centerY + sin(otherAngle).toFloat() * otherRadius
                
                drawLine(
                    color = Color.White.copy(alpha = entanglement * 0.5f),
                    start = Offset(x, y),
                    end = Offset(otherX, otherY),
                    strokeWidth = entanglement * 3f,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), animationProgress * 20f)
                )
            }
        }
    }
    
    // Draw central quantum core
    val coreRadius = 15f + 5f * sin(animationProgress * 4 * PI).toFloat()
    drawCircle(
        color = NeonWhite.copy(alpha = 0.8f),
        radius = coreRadius,
        center = Offset(centerX, centerY),
        style = Stroke(width = 2f)
    )
}

private fun DrawScope.drawQuantumField(size: androidx.compose.ui.geometry.Size, animationProgress: Float) {
    val gridSize = 30f
    val alpha = 0.05f + 0.03f * sin(animationProgress * 2 * PI).toFloat()
    
    // Draw grid
    var x = 0f
    while (x < size.width) {
        drawLine(
            color = NeonBlue.copy(alpha = alpha),
            start = Offset(x, 0f),
            end = Offset(x, size.height),
            strokeWidth = 1f
        )
        x += gridSize
    }
    
    var y = 0f
    while (y < size.height) {
        drawLine(
            color = NeonBlue.copy(alpha = alpha),
            start = Offset(0f, y),
            end = Offset(size.width, y),
            strokeWidth = 1f
        )
        y += gridSize
    }
}

@Composable
fun SearchPathsVisualization(
    searchPaths: List<SearchPath>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawSearchPaths(searchPaths, size)
            }
            
            Text(
                text = "SEARCH TRAJECTORY PATHS",
                color = NeonGreen,
                fontSize = 10.sp,
                fontFamily = MonospaceFont,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }
    }
}

private fun DrawScope.drawSearchPaths(
    searchPaths: List<SearchPath>,
    size: androidx.compose.ui.geometry.Size
) {
    val pathHeight = size.height / (searchPaths.size + 1)
    
    searchPaths.forEachIndexed { pathIndex, path ->
        val y = pathHeight * (pathIndex + 1)
        val stepWidth = size.width / (path.steps.size + 1)
        
        path.steps.forEachIndexed { stepIndex, step ->
            val x = stepWidth * (stepIndex + 1)
            val radius = 6f + step.confidence * 8f
            
            // Draw step node
            drawCircle(
                color = step.dimension.color.copy(alpha = 0.8f),
                radius = radius,
                center = Offset(x, y)
            )
            
            // Draw connection to next step
            if (stepIndex < path.steps.size - 1) {
                val nextX = stepWidth * (stepIndex + 2)
                drawLine(
                    color = step.dimension.color.copy(alpha = 0.5f),
                    start = Offset(x + radius, y),
                    end = Offset(nextX - radius, y),
                    strokeWidth = path.probability * 4f
                )
            }
        }
        
        // Draw path probability indicator
        drawLine(
            color = NeonWhite.copy(alpha = path.probability),
            start = Offset(0f, y),
            end = Offset(size.width * path.probability, y),
            strokeWidth = 2f
        )
    }
}

@Composable
fun QuantumStoryCard(
    story: Story,
    onClick: () -> Unit,
    isBookmarked: Boolean = false,
    onBookmarkClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val cardColor by animateColorAsState(
        targetValue = DarkCard,
        animationSpec = tween(300)
    )
    
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Title with quantum glow effect
            story.title?.let { title ->
                Text(
                    text = title,
                    color = LightText,
                    fontSize = 16.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            // Metadata row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Score with quantum enhancement
                    Box(
                        modifier = Modifier
                            .background(NeonBlue.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "${story.score ?: 0}",
                            color = NeonBlue,
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    // Author
                    story.by?.let { author ->
                        Text(
                            text = "by $author",
                            color = TextSecondary,
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont
                        )
                    }
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Quantum relevance indicator
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            Icons.Default.TrendingUp,
                            contentDescription = "Quantum Relevance",
                            tint = NeonGreen,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = "QUANTUM MATCH",
                            color = NeonGreen,
                            fontSize = 8.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    // Bookmark button
                    onBookmarkClick?.let { bookmarkClickHandler ->
                        IconButton(
                            onClick = bookmarkClickHandler,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = if (isBookmarked) "Remove bookmark" else "Add bookmark",
                                tint = if (isBookmarked) NeonYellow else NeonCyan.copy(alpha = 0.7f),
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeRangeDropdown(
    selectedTimeRange: RelativeTimeRange,
    onTimeRangeChange: (RelativeTimeRange) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    
    Box(modifier = modifier) {
        Card(
            modifier = Modifier
                .clickable { expanded = true }
                .border(
                    1.dp,
                    NeonCyan.copy(alpha = 0.5f),
                    RoundedCornerShape(8.dp)
                ),
            colors = CardDefaults.cardColors(containerColor = DarkCard),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    Icons.Default.Schedule,
                    contentDescription = "Time Range",
                    tint = NeonCyan,
                    modifier = Modifier.size(16.dp)
                )
                
                Text(
                    text = selectedTimeRange.displayName,
                    color = NeonCyan,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Text(
                    text = "▼",
                    color = NeonCyan.copy(alpha = 0.7f),
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
            }
        }
        
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(
                color = DarkBackground,
                shape = RoundedCornerShape(8.dp)
            )
        ) {
            RelativeTimeRange.values().forEach { timeRange ->
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(
                                        if (timeRange == selectedTimeRange) NeonCyan else Color.Transparent,
                                        CircleShape
                                    )
                            )
                            
                            Text(
                                text = timeRange.displayName,
                                color = if (timeRange == selectedTimeRange) NeonCyan else LightText,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = if (timeRange == selectedTimeRange) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    },
                    onClick = {
                        onTimeRangeChange(timeRange)
                        expanded = false
                    },
                    modifier = Modifier.background(
                        if (timeRange == selectedTimeRange) NeonCyan.copy(alpha = 0.1f) else Color.Transparent
                    )
                )
            }
        }
    }
}