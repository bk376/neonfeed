# Claude API Integration Setup

This app now supports Claude API integration for advanced AI features including:

- **Daily Summary Generation**: AI-powered analysis of trending topics and stories
- **Business Opportunity Analysis**: Detailed app development plans with market insights
- **Story Analysis**: Sentiment analysis, topic classification, and innovation scoring
- **Comment Insights**: AI-generated summaries of discussion threads

## Setup Instructions

### 1. Understanding Claude API vs Claude Pro

**Important**: Claude Pro subscription (for claude.ai) is separate from the Claude API:
- **Claude Pro**: Web interface subscription ($20/month) - unlimited messages on claude.ai
- **Claude API**: Pay-per-use API access - required for this app integration

### 2. Options for API Access

**Option A: Use Claude API (Recommended)**
1. Go to [Anthropic Console](https://console.anthropic.com/)
2. Sign up or log in (can use same account as Claude Pro)
3. Navigate to API Keys section
4. Generate a new API key
5. Copy the key (it starts with `sk-ant-api03-`)

**Option B: Use Local Mock Mode (Free)**
- The app works without API key using intelligent mock responses
- All features remain functional with simulated AI responses
- Perfect for development and testing

### 3. Configure the API Key (Option A)

1. Open: `app/src/main/java/com/hackernews/futuristic/data/config/ClaudeConfig.kt`
2. Replace the placeholder:

```kotlin
const val API_KEY = "sk-ant-api03-your-actual-api-key-here"
```

### 4. Alternative: Force Mock Mode (Option B)

1. Open: `app/src/main/java/com/hackernews/futuristic/data/config/ClaudeConfig.kt`
2. Change the setting:

```kotlin
const val FORCE_MOCK_MODE = true // Forces mock mode regardless of API key
```

3. Keep the default placeholder API key
4. App will use intelligent mock responses for all AI features

### 5. Features Available

**With Claude API (Option A):**
- **Daily Summary Screen**: Real Claude 4 AI analysis and business opportunities
- **Story Analysis**: Advanced sentiment and topic classification
- **Comment Insights**: AI-generated discussion summaries
- **Business Plan Generation**: Detailed development strategies

**With Mock Mode (Option B):**
- **Daily Summary Screen**: Intelligent mock analysis with realistic patterns
- **Story Analysis**: Simulated sentiment and topic classification
- **Comment Insights**: Smart mock discussion summaries
- **Business Plan Generation**: Template-based development strategies

### 6. Cost Comparison

**Claude API (Option A):**
- Pay-per-use model
- Current model: `claude-4-20241201` (Latest Claude 4)
- Estimated cost: $0.015 per 1K input tokens, $0.075 per 1K output tokens
- Daily usage for typical app use: ~$0.50-2.00 per day
- Provides real AI analysis and insights

**Mock Mode (Option B):**
- Completely free
- No API costs or usage limits
- Intelligent mock responses based on real patterns
- Perfect for development, testing, and budget-conscious users

### 6. Privacy and Security

- API key is stored locally in the app configuration
- No user data is sent to external servers except Claude API
- All API calls are made directly to Anthropic's servers
- Consider using environment variables for production deployment

## Testing the Integration

1. Build and run the app with your API key configured
2. Go to Daily Summary screen and tap "Generate Summary"
3. Accept a business opportunity to see Claude-generated development plans
4. View stories to see AI-powered analysis
5. Check comment sections for AI insights

## Troubleshooting

- **"API not configured"**: Ensure you've replaced the placeholder API key
- **"API call failed"**: Check your internet connection and API key validity
- **Rate limits**: Claude API has rate limits; the app includes retry logic
- **Long response times**: Claude API can take 5-30 seconds for complex requests

## Development Notes

- The integration uses Claude 4 model for the best performance and reasoning capabilities
- All API calls include proper error handling and fallbacks
- The app works offline with mock data when API is unavailable
- Claude 4 provides superior analysis, reasoning, and creative capabilities
- Future versions may add more Claude-powered features

## Support

For Claude API issues:
- Check [Anthropic Documentation](https://docs.anthropic.com/claude/reference/getting-started-with-the-api)
- Review API status at [Anthropic Status](https://status.anthropic.com/)
- Contact Anthropic support for API-related problems

For app integration issues:
- Check the app logs for error messages
- Verify API key format and permissions
- Test with a simple API call first