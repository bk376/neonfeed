package com.hackernews.futuristic.data.repository

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)
    
    private val _settingsState = MutableStateFlow(
        AppSettings(
            isNeuralAnalysisEnabled = prefs.getBoolean("neural_analysis", false),
            analysisSensitivity = prefs.getFloat("analysis_sensitivity", 0.7f),
            isBiometricEnabled = prefs.getBoolean("biometric", false),
            isCognitiveTrackingEnabled = prefs.getBoolean("cognitive_tracking", false),
            isHolographicEnabled = prefs.getBoolean("holographic", false),
            hologramIntensity = prefs.getFloat("hologram_intensity", 0.8f),
            isPredictiveFilterEnabled = prefs.getBoolean("predictive_filter", false),
            filterAggressiveness = prefs.getString("filter_aggressiveness", "Moderate") ?: "Moderate",
            isQuantumSearchEnabled = prefs.getBoolean("quantum_search", false),
            searchMode = prefs.getString("search_mode", "Hybrid") ?: "Hybrid",
            isAREnabled = prefs.getBoolean("ar_enabled", false),
            arOpacity = prefs.getFloat("ar_opacity", 0.8f),
            privacyMode = prefs.getString("privacy_mode", "Standard") ?: "Standard"
        )
    )
    
    val settingsState: StateFlow<AppSettings> = _settingsState.asStateFlow()
    
    fun updateSettings(settings: AppSettings) {
        _settingsState.value = settings
        saveToPrefs(settings)
    }
    
    private fun saveToPrefs(settings: AppSettings) {
        prefs.edit().apply {
            putBoolean("neural_analysis", settings.isNeuralAnalysisEnabled)
            putFloat("analysis_sensitivity", settings.analysisSensitivity)
            putBoolean("biometric", settings.isBiometricEnabled)
            putBoolean("cognitive_tracking", settings.isCognitiveTrackingEnabled)
            putBoolean("holographic", settings.isHolographicEnabled)
            putFloat("hologram_intensity", settings.hologramIntensity)
            putBoolean("predictive_filter", settings.isPredictiveFilterEnabled)
            putString("filter_aggressiveness", settings.filterAggressiveness)
            putBoolean("quantum_search", settings.isQuantumSearchEnabled)
            putString("search_mode", settings.searchMode)
            putBoolean("ar_enabled", settings.isAREnabled)
            putFloat("ar_opacity", settings.arOpacity)
            putString("privacy_mode", settings.privacyMode)
            apply()
        }
    }
}

data class AppSettings(
    val isNeuralAnalysisEnabled: Boolean = false,
    val analysisSensitivity: Float = 0.7f,
    val isBiometricEnabled: Boolean = false,
    val isCognitiveTrackingEnabled: Boolean = false,
    val isHolographicEnabled: Boolean = false,
    val hologramIntensity: Float = 0.8f,
    val isPredictiveFilterEnabled: Boolean = false,
    val filterAggressiveness: String = "Moderate",
    val isQuantumSearchEnabled: Boolean = false,
    val searchMode: String = "Hybrid",
    val isAREnabled: Boolean = false,
    val arOpacity: Float = 0.8f,
    val privacyMode: String = "Standard"
)