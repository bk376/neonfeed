package com.hackernews.futuristic.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(
        SettingsUiState(
            isNeuralAnalysisEnabled = settingsRepository.settingsState.value.isNeuralAnalysisEnabled,
            analysisSensitivity = settingsRepository.settingsState.value.analysisSensitivity,
            isBiometricEnabled = settingsRepository.settingsState.value.isBiometricEnabled,
            isCognitiveTrackingEnabled = settingsRepository.settingsState.value.isCognitiveTrackingEnabled,
            isHolographicEnabled = settingsRepository.settingsState.value.isHolographicEnabled,
            hologramIntensity = settingsRepository.settingsState.value.hologramIntensity,
            isPredictiveFilterEnabled = settingsRepository.settingsState.value.isPredictiveFilterEnabled,
            filterAggressiveness = settingsRepository.settingsState.value.filterAggressiveness,
            isQuantumSearchEnabled = settingsRepository.settingsState.value.isQuantumSearchEnabled,
            searchMode = settingsRepository.settingsState.value.searchMode,
            isAREnabled = settingsRepository.settingsState.value.isAREnabled,
            arOpacity = settingsRepository.settingsState.value.arOpacity,
            privacyMode = settingsRepository.settingsState.value.privacyMode
        )
    )
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()
    
    fun toggleNeuralAnalysis() {
        val newState = _uiState.value.copy(
            isNeuralAnalysisEnabled = !_uiState.value.isNeuralAnalysisEnabled
        )
        _uiState.value = newState
        updateRepository(newState)
    }
    
    fun updateAnalysisSensitivity(value: Float) {
        val newState = _uiState.value.copy(
            analysisSensitivity = value
        )
        _uiState.value = newState
        updateRepository(newState)
    }
    
    fun toggleBiometric() {
        val newState = _uiState.value.copy(
            isBiometricEnabled = !_uiState.value.isBiometricEnabled
        )
        _uiState.value = newState
        updateRepository(newState)
    }
    
    fun toggleCognitiveTracking() {
        _uiState.value = _uiState.value.copy(
            isCognitiveTrackingEnabled = !_uiState.value.isCognitiveTrackingEnabled
        )
    }
    
    fun toggleHolographic() {
        val newState = _uiState.value.copy(
            isHolographicEnabled = !_uiState.value.isHolographicEnabled
        )
        _uiState.value = newState
        updateRepository(newState)
    }
    
    fun updateHologramIntensity(value: Float) {
        _uiState.value = _uiState.value.copy(
            hologramIntensity = value
        )
    }
    
    fun togglePredictiveFilter() {
        _uiState.value = _uiState.value.copy(
            isPredictiveFilterEnabled = !_uiState.value.isPredictiveFilterEnabled
        )
    }
    
    fun updateFilterAggressiveness(value: String) {
        _uiState.value = _uiState.value.copy(
            filterAggressiveness = value
        )
    }
    
    fun toggleQuantumSearch() {
        _uiState.value = _uiState.value.copy(
            isQuantumSearchEnabled = !_uiState.value.isQuantumSearchEnabled
        )
    }
    
    fun updateSearchMode(value: String) {
        _uiState.value = _uiState.value.copy(
            searchMode = value
        )
    }
    
    fun toggleAR() {
        _uiState.value = _uiState.value.copy(
            isAREnabled = !_uiState.value.isAREnabled
        )
    }
    
    fun updateAROpacity(value: Float) {
        _uiState.value = _uiState.value.copy(
            arOpacity = value
        )
    }
    
    fun updatePrivacyMode(value: String) {
        _uiState.value = _uiState.value.copy(
            privacyMode = value
        )
    }
    
    fun clearTrainingData() {
        viewModelScope.launch {
            // TODO: Implement clearing training data
            // This would clear user preferences, analysis history, etc.
        }
    }
    
    private fun updateRepository(uiState: SettingsUiState) {
        val appSettings = com.hackernews.futuristic.data.repository.AppSettings(
            isNeuralAnalysisEnabled = uiState.isNeuralAnalysisEnabled,
            analysisSensitivity = uiState.analysisSensitivity,
            isBiometricEnabled = uiState.isBiometricEnabled,
            isCognitiveTrackingEnabled = uiState.isCognitiveTrackingEnabled,
            isHolographicEnabled = uiState.isHolographicEnabled,
            hologramIntensity = uiState.hologramIntensity,
            isPredictiveFilterEnabled = uiState.isPredictiveFilterEnabled,
            filterAggressiveness = uiState.filterAggressiveness,
            isQuantumSearchEnabled = uiState.isQuantumSearchEnabled,
            searchMode = uiState.searchMode,
            isAREnabled = uiState.isAREnabled,
            arOpacity = uiState.arOpacity,
            privacyMode = uiState.privacyMode
        )
        settingsRepository.updateSettings(appSettings)
    }
}

data class SettingsUiState(
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