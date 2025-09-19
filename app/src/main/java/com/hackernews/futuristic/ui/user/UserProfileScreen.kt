package com.hackernews.futuristic.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
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
import com.hackernews.futuristic.data.api.User
import com.hackernews.futuristic.data.model.Comment
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.ui.theme.*
import com.hackernews.futuristic.utils.TextUtils
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    username: String,
    onBackClick: () -> Unit,
    onStoryClick: (Story) -> Unit,
    viewModel: UserProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var selectedTab by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    
    LaunchedEffect(username) {
        viewModel.loadUserProfile(username)
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
        // Header
        TopAppBar(
            title = {
                Text(
                    text = "USER PROFILE",
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
        
        when {
            uiState.isLoading -> {
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
                            text = "LOADING USER DATA...",
                            color = NeonBlue,
                            fontFamily = MonospaceFont,
                            fontSize = 14.sp
                        )
                    }
                }
            }
            
            uiState.error != null -> {
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
                            text = uiState.error ?: "Unknown error",
                            color = TextSecondary,
                            fontSize = 14.sp,
                            fontFamily = MonospaceFont
                        )
                        Button(
                            onClick = { viewModel.retry(username) },
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
            
            uiState.user != null -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        UserProfileCard(user = uiState.user!!)
                    }
                    
                    item {
                        UserProfileTabs(
                            selectedTab = selectedTab,
                            onTabSelected = { selectedTab = it }
                        )
                    }
                    
                    when (selectedTab) {
                        0 -> {
                            // Submissions
                            if (uiState.isLoadingSubmissions) {
                                item {
                                    LoadingSection(text = "Loading submissions...")
                                }
                            } else if (uiState.submissions.isNotEmpty()) {
                                items(uiState.submissions) { story ->
                                    UserSubmissionCard(
                                        story = story,
                                        onClick = { onStoryClick(story) }
                                    )
                                }
                            } else {
                                item {
                                    EmptySection(text = "No submissions found")
                                }
                            }
                        }
                        
                        1 -> {
                            // Comments
                            if (uiState.isLoadingComments) {
                                item {
                                    LoadingSection(text = "Loading comments...")
                                }
                            } else if (uiState.comments.isNotEmpty()) {
                                items(uiState.comments) { comment ->
                                    UserCommentCard(
                                        comment = comment,
                                        onPostClick = { storyId ->
                                            scope.launch {
                                                val story = viewModel.getStoryById(storyId)
                                                story?.let { onStoryClick(it) }
                                            }
                                        }
                                    )
                                }
                            } else {
                                item {
                                    EmptySection(text = "No comments found")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UserProfileCard(user: User) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkSurface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            brush = Brush.radialGradient(
                                colors = listOf(
                                    NeonBlue.copy(alpha = 0.3f),
                                    NeonPurple.copy(alpha = 0.3f)
                                )
                            ),
                            shape = RoundedCornerShape(30.dp)
                        )
                        .border(2.dp, NeonBlue, RoundedCornerShape(30.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "User Avatar",
                        tint = NeonBlue,
                        modifier = Modifier.size(32.dp)
                    )
                }
                
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = user.id,
                        color = NeonBlue,
                        fontSize = 20.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = "KARMA: ${user.karma}",
                        color = NeonGreen,
                        fontSize = 14.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            // Stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                UserStatItem(
                    icon = Icons.Default.AccessTime,
                    label = "MEMBER SINCE",
                    value = formatDate(user.created),
                    color = NeonPurple
                )
                
                UserStatItem(
                    icon = Icons.Default.TrendingUp,
                    label = "KARMA",
                    value = user.karma.toString(),
                    color = NeonGreen
                )
            }
            
            // About
            user.about?.let { about ->
                if (about.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "ABOUT",
                        color = NeonYellow,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = TextUtils.cleanCommentText(about),
                        color = LightText,
                        fontSize = 14.sp,
                        fontFamily = MonospaceFont,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun UserStatItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    color: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = label,
            color = TextSecondary,
            fontSize = 10.sp,
            fontFamily = MonospaceFont
        )
        Text(
            text = value,
            color = color,
            fontSize = 12.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun UserProfileTabs(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TabButton(
            text = "SUBMISSIONS",
            isSelected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            modifier = Modifier.weight(1f)
        )
        
        TabButton(
            text = "COMMENTS",
            isSelected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun TabButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .clickable { onClick() }
            .background(
                brush = if (isSelected) {
                    Brush.horizontalGradient(
                        colors = listOf(
                            NeonBlue.copy(alpha = 0.3f),
                            NeonPurple.copy(alpha = 0.3f)
                        )
                    )
                } else {
                    Brush.horizontalGradient(
                        colors = listOf(DarkCard, DarkSurface)
                    )
                },
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                1.dp,
                if (isSelected) NeonBlue else DarkBorder,
                RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (isSelected) NeonBlue else TextSecondary,
            fontSize = 12.sp,
            fontFamily = MonospaceFont,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun UserSubmissionCard(
    story: Story,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            story.title?.let { title ->
                // Brief title - truncate if too long
                val briefTitle = if (title.length > 60) {
                    title.take(57) + "..."
                } else title
                
                Text(
                    text = briefTitle,
                    color = NeonBlue,
                    fontSize = 13.sp,
                    fontFamily = MonospaceFont,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "▲ ${story.score ?: 0}",
                        color = NeonGreen,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = "💬 ${story.descendants ?: 0}",
                        color = NeonPurple,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Text(
                    text = formatTimeAgo(story.time),
                    color = TextSecondary,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            // Show domain for external links
            story.url?.let { url ->
                val domain = try {
                    java.net.URL(url).host
                } catch (e: Exception) {
                    null
                }
                
                domain?.let {
                    Text(
                        text = it,
                        color = NeonCyan.copy(alpha = 0.7f),
                        fontSize = 9.sp,
                        fontFamily = MonospaceFont,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun UserCommentCard(
    comment: Comment,
    onPostClick: ((Long) -> Unit)? = null
) {
    var isExpanded by remember { mutableStateOf(false) }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            // Comment context (parent post info)
            comment.parent?.let { parentId ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(
                            if (onPostClick != null) {
                                Modifier.clickable { onPostClick(parentId) }
                            } else {
                                Modifier
                            }
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = if (onPostClick != null) {
                            NeonYellow.copy(alpha = 0.1f)
                        } else {
                            DarkSurface.copy(alpha = 0.5f)
                        }
                    ),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "↳",
                            color = if (onPostClick != null) NeonYellow else NeonYellow.copy(alpha = 0.6f),
                            fontSize = 12.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Reply to post #${parentId}",
                            color = if (onPostClick != null) NeonYellow else NeonYellow.copy(alpha = 0.6f),
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = if (onPostClick != null) FontWeight.Bold else FontWeight.Normal
                        )
                        if (onPostClick != null) {
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = "→",
                                color = NeonYellow,
                                fontSize = 10.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
            
            // Comment text with expansion
            comment.text?.let { text ->
                val cleanedText = TextUtils.cleanCommentText(text)
                val isLongComment = cleanedText.length > 200
                
                Text(
                    text = if (isExpanded || !isLongComment) {
                        cleanedText
                    } else {
                        cleanedText.take(200) + "..."
                    },
                    color = LightText,
                    fontSize = 12.sp,
                    fontFamily = MonospaceFont,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                    overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
                )
                
                // Expand/Collapse button for long comments
                if (isLongComment) {
                    TextButton(
                        onClick = { isExpanded = !isExpanded },
                        modifier = Modifier.padding(top = 4.dp),
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = NeonBlue.copy(alpha = 0.8f)
                        )
                    ) {
                        Text(
                            text = if (isExpanded) "Show less" else "Show more",
                            fontSize = 10.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            
            // Comment metadata
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = formatTimeAgo(comment.time),
                    color = TextSecondary,
                    fontSize = 10.sp,
                    fontFamily = MonospaceFont
                )
                
                Text(
                    text = "Comment ID: ${comment.id}",
                    color = TextSecondary.copy(alpha = 0.6f),
                    fontSize = 9.sp,
                    fontFamily = MonospaceFont
                )
            }
        }
    }
}

@Composable
private fun LoadingSection(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CircularProgressIndicator(
                color = NeonBlue,
                modifier = Modifier.size(32.dp)
            )
            Text(
                text = text,
                color = TextSecondary,
                fontSize = 12.sp,
                fontFamily = MonospaceFont
            )
        }
    }
}

@Composable
private fun EmptySection(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = TextSecondary,
            fontSize = 14.sp,
            fontFamily = MonospaceFont
        )
    }
}

private fun formatDate(timestamp: Long): String {
    val date = Date(timestamp * 1000)
    val formatter = SimpleDateFormat("MMM yyyy", Locale.getDefault())
    return formatter.format(date)
}

private fun formatTimeAgo(timestamp: Long): String {
    val now = System.currentTimeMillis() / 1000
    val diff = now - timestamp
    
    return when {
        diff < 60 -> "now"
        diff < 3600 -> "${diff / 60}m ago"
        diff < 86400 -> "${diff / 3600}h ago"
        diff < 604800 -> "${diff / 86400}d ago"
        else -> "${diff / 604800}w ago"
    }
}