package com.hackernews.futuristic.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchScreen(
    onBackClick: () -> Unit,
    onStoryClick: (Story) -> Unit,
    onAuthorClick: ((String) -> Unit)? = null,
    viewModel: SimpleSearchViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val isSearching by viewModel.isSearching.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    
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
        // Header with search bar
        TopAppBar(
            title = {
                Text(
                    text = "SEARCH",
                    color = NeonBlue,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = NeonBlue
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = DarkSurface
            )
        )
        
        // Search Bar
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = DarkSurface),
            shape = RoundedCornerShape(12.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = viewModel::updateSearchQuery,
                placeholder = {
                    Text(
                        text = "Search stories, authors, or topics...",
                        color = TextSecondary,
                        fontFamily = MonospaceFont
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = NeonBlue
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = viewModel::clearSearch) {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "Clear",
                                tint = TextSecondary
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = NeonBlue,
                    unfocusedBorderColor = DarkBorder,
                    cursorColor = NeonBlue,
                    focusedTextColor = LightText,
                    unfocusedTextColor = LightText
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                singleLine = true
            )
        }
        
        // Content
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                isSearching -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CircularProgressIndicator(
                                color = NeonBlue,
                                modifier = Modifier.size(48.dp)
                            )
                            Text(
                                text = "SEARCHING...",
                                color = NeonBlue,
                                fontFamily = MonospaceFont,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                
                error != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "ERROR",
                                color = NeonRed,
                                fontSize = 18.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = error ?: "Unknown error",
                                color = TextSecondary,
                                fontSize = 14.sp,
                                fontFamily = MonospaceFont
                            )
                            Button(
                                onClick = viewModel::retry,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = NeonBlue,
                                    contentColor = Color.Black
                                )
                            ) {
                                Text(
                                    text = "RETRY",
                                    fontFamily = MonospaceFont,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
                
                searchQuery.isBlank() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = "Search",
                                tint = NeonBlue.copy(alpha = 0.5f),
                                modifier = Modifier.size(64.dp)
                            )
                            Text(
                                text = "Enter search terms to find stories",
                                color = TextSecondary,
                                fontFamily = MonospaceFont,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
                
                searchResults.isEmpty() && searchQuery.isNotBlank() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "NO RESULTS",
                                color = NeonYellow,
                                fontSize = 18.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "No stories found for \"$searchQuery\"",
                                color = TextSecondary,
                                fontSize = 14.sp,
                                fontFamily = MonospaceFont
                            )
                        }
                    }
                }
                
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        item {
                            Text(
                                text = "${searchResults.size} results for \"$searchQuery\"",
                                color = NeonGreen,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        
                        items(searchResults) { story ->
                            SearchResultCard(
                                story = story,
                                query = searchQuery,
                                onClick = { onStoryClick(story) },
                                onAuthorClick = onAuthorClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchResultCard(
    story: Story,
    query: String,
    onClick: () -> Unit,
    onAuthorClick: ((String) -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Title
            story.title?.let { title ->
                Text(
                    text = title,
                    color = NeonBlue,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MonospaceFont,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Meta information
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Score
                Text(
                    text = "▲ ${story.score ?: 0}",
                    color = NeonGreen,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                // Comments
                Text(
                    text = "💬 ${story.descendants ?: 0}",
                    color = NeonPurple,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Author
                story.by?.let { author ->
                    Text(
                        text = "by $author",
                        color = if (onAuthorClick != null) NeonCyan else TextSecondary,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        modifier = if (onAuthorClick != null) {
                            Modifier.clickable { onAuthorClick(author) }
                        } else {
                            Modifier
                        }
                    )
                }
            }
            
            // URL if available
            story.url?.let { url ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = try {
                        java.net.URL(url).host
                    } catch (e: Exception) {
                        "external link"
                    },
                    color = NeonCyan.copy(alpha = 0.7f),
                    fontSize = 11.sp,
                    fontFamily = MonospaceFont,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}