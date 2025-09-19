package com.hackernews.futuristic.ui.browser

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.hackernews.futuristic.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RetroWebViewScreen(
    url: String,
    title: String,
    onBackClick: () -> Unit
) {
    var isLoading by remember { mutableStateOf(true) }
    var currentUrl by remember { mutableStateOf(url) }
    var webView by remember { mutableStateOf<WebView?>(null) }
    
    // Terminal-style loading animation
    val terminalProgress by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A0A0A),
                        Color(0xFF1A1A1A),
                        Color(0xFF0A0A0A)
                    )
                )
            )
    ) {
        // Retro Terminal Header
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF1E2A3A)
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF0D1821),
                                Color(0xFF1E2A3A),
                                Color(0xFF0D1821)
                            )
                        )
                    )
                    .border(
                        1.dp,
                        NeonGreen.copy(alpha = 0.5f),
                        RoundedCornerShape(4.dp)
                    )
                    .padding(12.dp)
            ) {
                // Terminal Title Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Terminal window controls (retro style)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(NeonRed, RoundedCornerShape(4.dp))
                        )
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(NeonYellow, RoundedCornerShape(4.dp))
                        )
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(NeonGreen, RoundedCornerShape(4.dp))
                        )
                    }
                    
                    Text(
                        text = "NEONFEED BROWSER v2.1",
                        color = NeonGreen,
                        fontSize = 11.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )
                    
                    Text(
                        text = if (isLoading) "[LOADING...]" else "[READY]",
                        color = if (isLoading) NeonYellow else NeonGreen,
                        fontSize = 10.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Terminal Command Line Style URL Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color.Black,
                            RoundedCornerShape(3.dp)
                        )
                        .border(
                            1.dp,
                            NeonGreen.copy(alpha = 0.7f),
                            RoundedCornerShape(3.dp)
                        )
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "$",
                        color = NeonGreen,
                        fontSize = 12.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = "curl -X GET \"$currentUrl\"",
                        color = NeonBlue,
                        fontSize = 11.sp,
                        fontFamily = MonospaceFont,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    
                    if (isLoading) {
                        Text(
                            text = "|",
                            color = NeonGreen.copy(alpha = if (terminalProgress > 0.5f) 1f else 0.3f),
                            fontSize = 12.sp,
                            fontFamily = MonospaceFont,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Simple Navigation
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Cyberpunk Back Button
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        NeonBlue.copy(alpha = 0.2f),
                                        Color.Black
                                    )
                                ),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .border(
                                2.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(NeonBlue, NeonCyan)
                                ),
                                shape = RoundedCornerShape(6.dp)
                            )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = NeonBlue,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    
                    // Title with cyberpunk styling
                    Text(
                        text = title,
                        color = NeonCyan,
                        fontSize = 14.sp,
                        fontFamily = MonospaceFont,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        
        // WebView Container with retro border
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .border(
                        2.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                NeonGreen.copy(alpha = 0.8f),
                                NeonBlue.copy(alpha = 0.6f),
                                NeonPurple.copy(alpha = 0.4f)
                            )
                        ),
                        RoundedCornerShape(4.dp)
                    )
                    .padding(2.dp)
            ) {
                AndroidView(
                    factory = { context ->
                        WebView(context).apply {
                            webViewClient = object : WebViewClient() {
                                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
                                    super.onPageStarted(view, url, favicon)
                                    isLoading = true
                                    currentUrl = url ?: ""
                                }
                                
                                override fun onPageFinished(view: WebView?, url: String?) {
                                    super.onPageFinished(view, url)
                                    isLoading = false
                                    currentUrl = url ?: ""
                                }
                            }
                            
                            settings.apply {
                                javaScriptEnabled = true
                                domStorageEnabled = true
                                loadWithOverviewMode = true
                                useWideViewPort = true
                            }
                            
                            // Set dark background for retro feel
                            setBackgroundColor(Color.Black.toArgb())
                            
                            webView = this
                            loadUrl(url)
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
                
                // Loading overlay with retro style
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Color.Black.copy(alpha = 0.8f)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Retro loading animation
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                repeat(8) { index ->
                                    Box(
                                        modifier = Modifier
                                            .size(4.dp)
                                            .background(
                                                NeonGreen.copy(
                                                    alpha = if ((terminalProgress * 8).toInt() == index) 1f else 0.3f
                                                ),
                                                RoundedCornerShape(2.dp)
                                            )
                                    )
                                }
                            }
                            
                            Text(
                                text = "ESTABLISHING CONNECTION...",
                                color = NeonGreen,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont,
                                fontWeight = FontWeight.Bold
                            )
                            
                            Text(
                                text = "DECRYPTING NEURAL PATHWAYS...",
                                color = NeonBlue.copy(alpha = 0.8f),
                                fontSize = 10.sp,
                                fontFamily = MonospaceFont
                            )
                        }
                    }
                }
            }
        }
    }
}