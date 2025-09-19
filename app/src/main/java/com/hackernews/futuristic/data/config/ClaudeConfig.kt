package com.hackernews.futuristic.data.config

import javax.inject.Singleton

@Singleton
object ClaudeConfig {
    // TODO: User needs to replace this with their actual Claude API key
    // Get your API key from: https://console.anthropic.com/
    const val API_KEY = "sk-ant-api03-your-api-key-here"
    
    // Claude API configuration
    const val BASE_URL = "https://api.anthropic.com/v1/messages"
    const val MODEL = "claude-4-20241201" // Latest Claude 4 model
    const val VERSION = "2023-06-01"
    const val MAX_TOKENS = 8192
    
    // Feature flags
    const val ENABLE_CLAUDE_API = true
    const val FALLBACK_TO_MOCK = true
    const val FORCE_MOCK_MODE = false // Set to true to always use mock responses (free mode)
    
    fun isConfigured(): Boolean {
        return !FORCE_MOCK_MODE && API_KEY != "sk-ant-api03-your-api-key-here" && API_KEY.isNotBlank()
    }
    
    fun getApiKey(): String {
        return API_KEY
    }
    
    fun shouldUseMockMode(): Boolean {
        return FORCE_MOCK_MODE || !isConfigured()
    }
}