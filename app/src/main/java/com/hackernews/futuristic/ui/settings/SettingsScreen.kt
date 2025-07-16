package com.hackernews.futuristic.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hackernews.futuristic.ui.theme.*

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
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
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = DarkSurface
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            NeonBlue.copy(alpha = 0.1f),
                            RoundedCornerShape(6.dp)
                        )
                        .border(
                            1.dp,
                            NeonBlue,
                            RoundedCornerShape(6.dp)
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = NeonBlue,
                        modifier = Modifier.size(18.dp)
                    )
                }
                
                Text(
                    text = "AI FEATURES CONTROL",
                    color = NeonBlue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MonospaceFont
                )
            }
        }
        
        // Settings Content
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SettingsSection(
                    title = "NEURAL ANALYSIS",
                    description = "AI-powered story analysis and insights"
                ) {
                    SettingsToggle(
                        title = "Neural Network Analysis",
                        description = "Enable AI story classification and analysis",
                        icon = Icons.Default.Memory,
                        isEnabled = uiState.isNeuralAnalysisEnabled,
                        onToggle = { viewModel.toggleNeuralAnalysis() },
                        accentColor = NeonGreen
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    SettingsSlider(
                        title = "Analysis Sensitivity",
                        description = "How detailed the AI analysis should be",
                        value = uiState.analysisSensitivity,
                        onValueChange = { viewModel.updateAnalysisSensitivity(it) },
                        enabled = uiState.isNeuralAnalysisEnabled,
                        accentColor = NeonGreen
                    )
                }
            }
            
            item {
                SettingsSection(
                    title = "BIOMETRIC MONITORING",
                    description = "Real-time reading analytics and cognitive tracking"
                ) {
                    SettingsToggle(
                        title = "Biometric Reading Analysis",
                        description = "Monitor reading patterns and engagement",
                        icon = Icons.Default.Favorite,
                        isEnabled = uiState.isBiometricEnabled,
                        onToggle = { viewModel.toggleBiometric() },
                        accentColor = NeonRed
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    SettingsToggle(
                        title = "Cognitive Load Tracking",
                        description = "Track mental effort during reading",
                        icon = Icons.Default.Psychology,
                        isEnabled = uiState.isCognitiveTrackingEnabled,
                        onToggle = { viewModel.toggleCognitiveTracking() },
                        accentColor = NeonRed,
                        enabled = uiState.isBiometricEnabled
                    )
                }
            }
            
            item {
                SettingsSection(
                    title = "HOLOGRAPHIC DISPLAY",
                    description = "Advanced visual story previews"
                ) {
                    SettingsToggle(
                        title = "Holographic Mode",
                        description = "Enable 3D story previews with effects",
                        icon = Icons.Default.ViewInAr,
                        isEnabled = uiState.isHolographicEnabled,
                        onToggle = { viewModel.toggleHolographic() },
                        accentColor = NeonPurple
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    SettingsSlider(
                        title = "Hologram Intensity",
                        description = "Visual effect intensity level",
                        value = uiState.hologramIntensity,
                        onValueChange = { viewModel.updateHologramIntensity(it) },
                        enabled = uiState.isHolographicEnabled,
                        accentColor = NeonPurple
                    )
                }
            }
            
            item {
                SettingsSection(
                    title = "PREDICTIVE FILTERING",
                    description = "ML-powered content curation"
                ) {
                    SettingsToggle(
                        title = "Predictive Content Filter",
                        description = "AI learns your preferences and filters content",
                        icon = Icons.Default.FilterList,
                        isEnabled = uiState.isPredictiveFilterEnabled,
                        onToggle = { viewModel.togglePredictiveFilter() },
                        accentColor = NeonYellow
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    SettingsDropdown(
                        title = "Filter Aggressiveness",
                        description = "How strictly to filter content",
                        selectedValue = uiState.filterAggressiveness,
                        options = listOf("Conservative", "Moderate", "Aggressive", "Very Aggressive"),
                        onValueChange = { viewModel.updateFilterAggressiveness(it) },
                        enabled = uiState.isPredictiveFilterEnabled,
                        accentColor = NeonYellow
                    )
                }
            }
            
            item {
                SettingsSection(
                    title = "QUANTUM SEARCH",
                    description = "Multi-dimensional content discovery"
                ) {
                    SettingsToggle(
                        title = "Quantum Search Engine",
                        description = "Enable advanced search with AI assistance",
                        icon = Icons.Default.Search,
                        isEnabled = uiState.isQuantumSearchEnabled,
                        onToggle = { viewModel.toggleQuantumSearch() },
                        accentColor = NeonCyan
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    SettingsDropdown(
                        title = "Search Mode",
                        description = "Type of search algorithm to use",
                        selectedValue = uiState.searchMode,
                        options = listOf("Classical", "Quantum", "Neural", "Hybrid"),
                        onValueChange = { viewModel.updateSearchMode(it) },
                        enabled = uiState.isQuantumSearchEnabled,
                        accentColor = NeonCyan
                    )
                }
            }
            
            item {
                SettingsSection(
                    title = "AUGMENTED REALITY",
                    description = "AR overlays and visual enhancements"
                ) {
                    SettingsToggle(
                        title = "AR Overlay System",
                        description = "Enable augmented reality interface elements",
                        icon = Icons.Default.CameraAlt,
                        isEnabled = uiState.isAREnabled,
                        onToggle = { viewModel.toggleAR() },
                        accentColor = CyberpunkOrange
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    SettingsSlider(
                        title = "AR Opacity",
                        description = "Transparency of AR overlay elements",
                        value = uiState.arOpacity,
                        onValueChange = { viewModel.updateAROpacity(it) },
                        enabled = uiState.isAREnabled,
                        accentColor = CyberpunkOrange
                    )
                }
            }
            
            item {
                SettingsSection(
                    title = "PRIVACY & DATA",
                    description = "Control how your data is used for AI features"
                ) {
                    SettingsDropdown(
                        title = "Privacy Mode",
                        description = "Level of data collection for personalization",
                        selectedValue = uiState.privacyMode,
                        options = listOf("Minimal", "Standard", "Enhanced", "Full"),
                        onValueChange = { viewModel.updatePrivacyMode(it) },
                        enabled = true,
                        accentColor = NeonWhite
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    SettingsButton(
                        title = "Clear AI Training Data",
                        description = "Remove all personalization data",
                        icon = Icons.Default.Delete,
                        onClick = { viewModel.clearTrainingData() },
                        accentColor = NeonRed
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = DarkSurface
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = title,
                color = NeonBlue,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MonospaceFont
            )
            
            Text(
                text = description,
                color = TextSecondary,
                fontSize = 12.sp,
                fontFamily = MonospaceFont,
                modifier = Modifier.padding(top = 4.dp)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            content()
        }
    }
}

@Composable
private fun SettingsToggle(
    title: String,
    description: String,
    icon: ImageVector,
    isEnabled: Boolean,
    onToggle: () -> Unit,
    accentColor: Color,
    enabled: Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = enabled) { onToggle() }
            .background(
                if (isEnabled && enabled) accentColor.copy(alpha = 0.1f) else Color.Transparent,
                RoundedCornerShape(8.dp)
            )
            .border(
                1.dp,
                if (enabled) {
                    if (isEnabled) accentColor else DarkBorder
                } else {
                    DarkBorder.copy(alpha = 0.5f)
                },
                RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = if (enabled) {
                if (isEnabled) accentColor else TextSecondary
            } else {
                TextSecondary.copy(alpha = 0.5f)
            },
            modifier = Modifier.size(20.dp)
        )
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = if (enabled) {
                    if (isEnabled) accentColor else TextPrimary
                } else {
                    TextPrimary.copy(alpha = 0.5f)
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MonospaceFont
            )
            
            Text(
                text = description,
                color = if (enabled) TextSecondary else TextSecondary.copy(alpha = 0.5f),
                fontSize = 11.sp,
                fontFamily = MonospaceFont
            )
        }
        
        Text(
            text = if (isEnabled) "ON" else "OFF",
            color = if (enabled) {
                if (isEnabled) accentColor else TextSecondary
            } else {
                TextSecondary.copy(alpha = 0.5f)
            },
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = MonospaceFont
        )
    }
}

@Composable
private fun SettingsSlider(
    title: String,
    description: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    enabled: Boolean,
    accentColor: Color
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    color = if (enabled) TextPrimary else TextPrimary.copy(alpha = 0.5f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = MonospaceFont
                )
                
                Text(
                    text = description,
                    color = if (enabled) TextSecondary else TextSecondary.copy(alpha = 0.5f),
                    fontSize = 11.sp,
                    fontFamily = MonospaceFont
                )
            }
            
            Text(
                text = "${(value * 100).toInt()}%",
                color = if (enabled) accentColor else accentColor.copy(alpha = 0.5f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = MonospaceFont
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Slider(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            colors = SliderDefaults.colors(
                thumbColor = if (enabled) accentColor else accentColor.copy(alpha = 0.5f),
                activeTrackColor = if (enabled) accentColor else accentColor.copy(alpha = 0.5f),
                inactiveTrackColor = DarkBorder
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun SettingsDropdown(
    title: String,
    description: String,
    selectedValue: String,
    options: List<String>,
    onValueChange: (String) -> Unit,
    enabled: Boolean,
    accentColor: Color
) {
    var expanded by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            color = if (enabled) TextPrimary else TextPrimary.copy(alpha = 0.5f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = MonospaceFont
        )
        
        Text(
            text = description,
            color = if (enabled) TextSecondary else TextSecondary.copy(alpha = 0.5f),
            fontSize = 11.sp,
            fontFamily = MonospaceFont,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(enabled = enabled) { expanded = true }
                    .background(
                        DarkSurface,
                        RoundedCornerShape(8.dp)
                    )
                    .border(
                        1.dp,
                        if (enabled) accentColor else accentColor.copy(alpha = 0.5f),
                        RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedValue,
                    color = if (enabled) accentColor else accentColor.copy(alpha = 0.5f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = MonospaceFont
                )
                
                Icon(
                    imageVector = Icons.Default.ExpandMore,
                    contentDescription = "Expand",
                    tint = if (enabled) accentColor else accentColor.copy(alpha = 0.5f),
                    modifier = Modifier.size(16.dp)
                )
            }
            
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(DarkSurface)
                    .border(1.dp, accentColor, RoundedCornerShape(8.dp))
            ) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option,
                                color = if (option == selectedValue) accentColor else TextPrimary,
                                fontSize = 12.sp,
                                fontFamily = MonospaceFont
                            )
                        },
                        onClick = {
                            onValueChange(option)
                            expanded = false
                        },
                        modifier = Modifier.background(
                            if (option == selectedValue) accentColor.copy(alpha = 0.1f) else Color.Transparent
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsButton(
    title: String,
    description: String,
    icon: ImageVector,
    onClick: () -> Unit,
    accentColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                accentColor.copy(alpha = 0.1f),
                RoundedCornerShape(8.dp)
            )
            .border(
                1.dp,
                accentColor,
                RoundedCornerShape(8.dp)
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = accentColor,
            modifier = Modifier.size(20.dp)
        )
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = accentColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = MonospaceFont
            )
            
            Text(
                text = description,
                color = TextSecondary,
                fontSize = 11.sp,
                fontFamily = MonospaceFont
            )
        }
        
        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Action",
            tint = accentColor,
            modifier = Modifier.size(16.dp)
        )
    }
}