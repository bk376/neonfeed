package com.hackernews.futuristic.data.config

import javax.inject.Singleton

@Singleton
object GeminiConfig {
    // CONFIGURATION GUIDE:
    // 1. Get FREE Google AI Studio API key: https://aistudio.google.com/
    // 2. Replace the API_KEY below with your key
    // 3. Gemini 1.5 Flash has generous free tier: 15 RPM, 1 million tokens per minute
    // 4. Alternative: Set FORCE_MOCK_MODE = true for offline intelligent analysis
    
    const val API_KEY = "AIzaSyBQumfrv3Q6rshsgMD4FMECw0_g50vxvNE" // Replace with your key
    
    // Google AI Studio API configuration
    const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models"
    const val MODEL = "gemini-1.5-flash-latest" // Fast and free model
    const val MAX_TOKENS = 8192
    
    // Multi-tier fallback system settings
    const val ENABLE_GEMINI_API = true
    const val FALLBACK_TO_MOCK = true
    const val FORCE_MOCK_MODE = false // Set true for offline intelligent analysis
    const val ENABLE_DEBUG_LOGGING = true
    
    // Free tier limits (Gemini 1.5 Flash)
    const val FREE_TIER_RPM = 15 // Requests per minute
    const val FREE_TIER_TPM = 1_000_000 // Tokens per minute
    
    fun isConfigured(): Boolean {
        val configured = !FORCE_MOCK_MODE && API_KEY.isNotBlank() && API_KEY.startsWith("AIza") && API_KEY.length > 30
        if (ENABLE_DEBUG_LOGGING) {
            println("Gemini API Configuration:")
            println("- API Key: ${if (API_KEY.length > 10) "${API_KEY.take(8)}..." else "INVALID"}")
            println("- Configured: $configured")
            println("- Force Mock: $FORCE_MOCK_MODE")
            println("- Model: $MODEL")
        }
        return configured
    }
    
    fun getApiKey(): String {
        return API_KEY
    }
    
    fun shouldUseMockMode(): Boolean {
        val useMock = FORCE_MOCK_MODE || !isConfigured()
        if (ENABLE_DEBUG_LOGGING && useMock) {
            println("Using mock mode. Reasons:")
            if (FORCE_MOCK_MODE) println("- Force mock mode enabled")
            if (!isConfigured()) println("- API not properly configured")
            println("- Get free API key: https://aistudio.google.com/")
        }
        return useMock
    }
    
    fun getConfigurationGuide(): String {
        return """
        🤖 DAILY NEURAL SYNTHESIS SETUP GUIDE
        
        Current Status: ${if (isConfigured()) "✅ Configured" else "❌ Not Configured"}
        
        📋 QUICK SETUP (FREE):
        1. Visit: https://aistudio.google.com/
        2. Sign in with Google account
        3. Click "Get API Key" → "Create API Key"
        4. Copy your key (starts with AIza...)
        5. Replace API_KEY in GeminiConfig.kt
        
        🆓 FREE TIER BENEFITS:
        • 15 requests per minute
        • 1 million tokens per minute
        • No credit card required
        • Production-ready Gemini 1.5 Flash model
        
        🔄 FALLBACK OPTIONS:
        • Intelligent local analysis (always available)
        • Real data-driven insights
        • Community trend detection
        • Business opportunity generation
        
        🛠 ALTERNATIVE: Set FORCE_MOCK_MODE = true for offline mode
        """.trimIndent()
    }
}