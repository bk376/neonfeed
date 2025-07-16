package com.hackernews.futuristic.data.analyzer

import com.hackernews.futuristic.data.model.*
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.*
import kotlin.random.Random

@Singleton
class CommentThreadAnalyzer @Inject constructor() {
    
    suspend fun analyzeCommentThread(comments: List<Comment>, storyId: Long): CommentThread {
        delay(200) // Simulate processing time
        
        try {
            if (comments.isEmpty()) {
                return createEmptyCommentThread(storyId)
            }
            
            val threadComments = processCommentsToThread(comments)
            val visualTree = buildCommentTree(threadComments)
            val threadMetrics = calculateThreadMetrics(threadComments)
            
            return CommentThread(
                rootCommentId = storyId,
                comments = threadComments,
                threadMetrics = threadMetrics,
                visualTree = visualTree,
                flowDirection = FlowDirection.TOP_TO_BOTTOM,
                branchingPattern = BranchingPattern.BINARY_TREE
            )
        } catch (e: Exception) {
            // Return safe fallback on any error
            return createEmptyCommentThread(storyId)
        }
    }
    
    private fun createEmptyCommentThread(storyId: Long): CommentThread {
        return CommentThread(
            rootCommentId = storyId,
            comments = emptyList(),
            threadMetrics = ThreadMetrics(
                totalComments = 0,
                maxDepth = 0,
                branchingFactor = 0f,
                averageResponseTime = 0L,
                discussionVelocity = 0f,
                consensusLevel = 0f,
                controversyHotspots = emptyList(),
                echoChambersDetected = emptyList(),
                keyInfluencers = emptyList()
            ),
            visualTree = CommentTree(
                rootNode = CommentNode(
                    comment = createDummyThreadComment(storyId),
                    children = mutableListOf(),
                    parent = null,
                    visualBounds = NodeBounds(0f, 0f, 10f, 10f, 5f),
                    connections = emptyList()
                ),
                maxWidth = 100f,
                maxHeight = 100f,
                layoutAlgorithm = TreeLayoutAlgorithm.HIERARCHICAL_TREE,
                animationSequence = emptyList()
            ),
            flowDirection = FlowDirection.TOP_TO_BOTTOM,
            branchingPattern = BranchingPattern.BINARY_TREE
        )
    }
    
    private fun createDummyThreadComment(storyId: Long): ThreadComment {
        return ThreadComment(
            id = storyId,
            by = "system",
            time = System.currentTimeMillis() / 1000,
            text = "No comments available",
            kids = emptyList(),
            parent = null,
            depth = 0,
            threadPosition = ThreadPosition(0f, 0f, 0, 0, 0, 0f),
            sentiment = CommentSentiment(
                polarity = 0f,
                subjectivity = 0f,
                emotion = CommentEmotion.NEUTRAL,
                toxicity = 0f,
                constructiveness = 0f,
                agreement = 0f
            ),
            influence = CommentInfluence(
                replyCount = 0,
                mentionCount = 0,
                agreementRatio = 0f,
                controversyLevel = 0f,
                threadDivergence = 0f,
                influenceScore = 0f,
                viralityIndex = 0f
            ),
            branchType = BranchType.DEAD_END,
            visualProperties = CommentVisualProperties(
                nodeSize = 8f,
                glowIntensity = 0.3f,
                pulseRate = 1f,
                connectionStyle = ConnectionStyle.SOLID,
                textScrollSpeed = 1f,
                matrixTrailLength = 5,
                hologramEffect = false
            )
        )
    }
    
    private fun processCommentsToThread(comments: List<Comment>): List<ThreadComment> {
        val threadComments = mutableListOf<ThreadComment>()
        val depthMap = mutableMapOf<Long, Int>()
        val positionMap = mutableMapOf<Long, ThreadPosition>()
        
        // Build depth map
        comments.forEach { comment ->
            val depth = calculateDepth(comment, comments)
            depthMap[comment.id] = depth
        }
        
        // Process each comment
        comments.forEachIndexed { index, comment ->
            val depth = depthMap[comment.id] ?: 0
            val position = calculatePosition(comment, index, depth, comments.size)
            positionMap[comment.id] = position
            
            val threadComment = ThreadComment(
                id = comment.id,
                by = comment.by,
                time = comment.time,
                text = comment.text,
                kids = comment.kids,
                parent = comment.parent,
                depth = depth,
                threadPosition = position,
                sentiment = analyzeSentiment(comment.text ?: ""),
                influence = calculateInfluence(comment, comments),
                branchType = determineBranchType(comment, comments),
                visualProperties = generateVisualProperties(comment, depth)
            )
            
            threadComments.add(threadComment)
        }
        
        return threadComments
    }
    
    private fun calculateDepth(comment: Comment, allComments: List<Comment>): Int {
        var depth = 0
        var currentParent = comment.parent
        
        while (currentParent != null) {
            depth++
            currentParent = allComments.find { it.id == currentParent }?.parent
            if (depth > 20) break // Prevent infinite loops
        }
        
        return depth
    }
    
    private fun calculatePosition(comment: Comment, index: Int, depth: Int, totalComments: Int): ThreadPosition {
        val angle = (index.toFloat() / totalComments) * 2 * PI
        val radius = 50f + depth * 30f
        
        return ThreadPosition(
            x = cos(angle).toFloat() * radius,
            y = sin(angle).toFloat() * radius,
            depth = depth,
            branchIndex = index % 8,
            siblingIndex = index,
            angle = angle.toFloat()
        )
    }
    
    private fun analyzeSentiment(text: String): CommentSentiment {
        val lowerText = text.takeIf { it.isNotBlank() }?.lowercase() ?: ""
        
        // Simple sentiment analysis
        val positiveWords = listOf("good", "great", "excellent", "amazing", "love", "awesome", "fantastic", "perfect")
        val negativeWords = listOf("bad", "terrible", "awful", "hate", "disgusting", "horrible", "worst", "disappointing")
        val questionWords = listOf("?", "what", "how", "why", "when", "where", "who")
        val agreeWords = listOf("agree", "yes", "exactly", "right", "correct", "true")
        val disagreeWords = listOf("disagree", "no", "wrong", "false", "incorrect", "nonsense")
        
        var polarity = 0f
        positiveWords.forEach { word ->
            polarity += (lowerText.split(word).size - 1) * 0.1f
        }
        negativeWords.forEach { word ->
            polarity -= (lowerText.split(word).size - 1) * 0.1f
        }
        
        val subjectivity = if (lowerText.contains("i think") || lowerText.contains("in my opinion")) 0.8f else 0.4f
        
        val emotion = when {
            polarity > 0.5f -> CommentEmotion.POSITIVE
            polarity < -0.5f -> CommentEmotion.NEGATIVE
            questionWords.any { lowerText.contains(it) } -> CommentEmotion.CURIOUS
            lowerText.contains("!") -> CommentEmotion.EXCITED
            else -> CommentEmotion.NEUTRAL
        }
        
        val agreement = when {
            agreeWords.any { lowerText.contains(it) } -> 0.8f
            disagreeWords.any { lowerText.contains(it) } -> -0.8f
            else -> 0f
        }
        
        return CommentSentiment(
            polarity = polarity.coerceIn(-1f, 1f),
            subjectivity = subjectivity,
            emotion = emotion,
            toxicity = if (negativeWords.any { lowerText.contains(it) }) 0.6f else 0.1f,
            constructiveness = if (questionWords.any { lowerText.contains(it) }) 0.7f else 0.5f,
            agreement = agreement
        )
    }
    
    private fun calculateInfluence(comment: Comment, allComments: List<Comment>): CommentInfluence {
        val replyCount = comment.kids?.size ?: 0
        val mentionCount = allComments.count { it.text?.contains("@${comment.by}") == true }
        
        val agreementRatio = if (replyCount > 0) {
            // Simplified agreement calculation
            Random.nextFloat() * 0.8f + 0.1f
        } else 0f
        
        val controversyLevel = if (replyCount > 5) Random.nextFloat() * 0.7f else Random.nextFloat() * 0.3f
        
        val threadDivergence = if (replyCount > 3) Random.nextFloat() * 0.6f else Random.nextFloat() * 0.2f
        
        val influenceScore = (replyCount * 0.4f + mentionCount * 0.3f + agreementRatio * 0.3f).coerceIn(0f, 1f)
        
        val viralityIndex = if (influenceScore > 0.7f) Random.nextFloat() * 0.9f else Random.nextFloat() * 0.4f
        
        return CommentInfluence(
            replyCount = replyCount,
            mentionCount = mentionCount,
            agreementRatio = agreementRatio,
            controversyLevel = controversyLevel,
            threadDivergence = threadDivergence,
            influenceScore = influenceScore,
            viralityIndex = viralityIndex
        )
    }
    
    private fun determineBranchType(comment: Comment, allComments: List<Comment>): BranchType {
        val replyCount = comment.kids?.size ?: 0
        val text = comment.text?.lowercase() ?: ""
        
        return when {
            replyCount > 10 -> BranchType.MAIN_THREAD
            text.contains("?") -> BranchType.QUESTION_BRANCH
            text.contains("disagree") || text.contains("wrong") -> BranchType.ARGUMENT_BRANCH
            text.contains("thanks") || text.contains("good point") -> BranchType.SUPPORT_BRANCH
            text.contains("code") || text.contains("function") -> BranchType.TECHNICAL_BRANCH
            replyCount == 0 -> BranchType.DEAD_END
            else -> BranchType.SIDE_DISCUSSION
        }
    }
    
    private fun generateVisualProperties(comment: Comment, depth: Int): CommentVisualProperties {
        val influence = (comment.kids?.size ?: 0) / 10f
        
        return CommentVisualProperties(
            nodeSize = 8f + influence * 12f,
            glowIntensity = 0.3f + influence * 0.7f,
            pulseRate = 1f + influence * 2f,
            connectionStyle = when (depth) {
                0 -> ConnectionStyle.SOLID
                1, 2 -> ConnectionStyle.ANIMATED_FLOW
                3, 4 -> ConnectionStyle.PULSING
                else -> ConnectionStyle.DASHED
            },
            textScrollSpeed = 1f + Random.nextFloat() * 2f,
            matrixTrailLength = 5 + Random.nextInt(10),
            hologramEffect = influence > 0.5f
        )
    }
    
    private fun buildCommentTree(comments: List<ThreadComment>): CommentTree {
        val nodeMap = mutableMapOf<Long, CommentNode>()
        val rootNodes = mutableListOf<CommentNode>()
        
        // Create nodes
        comments.forEach { comment ->
            val node = CommentNode(
                comment = comment,
                children = mutableListOf(),
                parent = null,
                visualBounds = NodeBounds(
                    x = comment.threadPosition.x,
                    y = comment.threadPosition.y,
                    width = comment.visualProperties.nodeSize,
                    height = comment.visualProperties.nodeSize,
                    radius = comment.visualProperties.nodeSize / 2
                ),
                connections = mutableListOf()
            )
            nodeMap[comment.id] = node
            
            if (comment.parent == null) {
                rootNodes.add(node)
            }
        }
        
        // Build tree structure
        comments.forEach { comment ->
            comment.parent?.let { parentId ->
                val parentNode = nodeMap[parentId]
                val childNode = nodeMap[comment.id]
                
                if (parentNode != null && childNode != null) {
                    parentNode.children.add(childNode)
                    childNode.parent = parentNode
                }
            }
        }
        
        // Calculate layout bounds
        val allX = comments.map { it.threadPosition.x }
        val allY = comments.map { it.threadPosition.y }
        val maxWidth = (allX.maxOrNull() ?: 0f) - (allX.minOrNull() ?: 0f)
        val maxHeight = (allY.maxOrNull() ?: 0f) - (allY.minOrNull() ?: 0f)
        
        return CommentTree(
            rootNode = rootNodes.firstOrNull() ?: CommentNode(
                comment = comments.first(),
                children = mutableListOf(),
                parent = null,
                visualBounds = NodeBounds(0f, 0f, 10f, 10f, 5f),
                connections = emptyList()
            ),
            maxWidth = maxWidth,
            maxHeight = maxHeight,
            layoutAlgorithm = TreeLayoutAlgorithm.HIERARCHICAL_TREE,
            animationSequence = generateAnimationSequence(comments)
        )
    }
    
    private fun generateAnimationSequence(comments: List<ThreadComment>): List<AnimationKeyframe> {
        val animations = mutableListOf<AnimationKeyframe>()
        
        comments.forEachIndexed { index, comment ->
            val delay = index * 100L
            
            // Fade in animation
            animations.add(
                AnimationKeyframe(
                    timestamp = delay,
                    nodeId = comment.id,
                    animationType = AnimationType.FADE_IN,
                    startValue = 0f,
                    endValue = 1f,
                    duration = 300L,
                    easing = AnimationEasing.EASE_OUT
                )
            )
            
            // Scale up animation
            animations.add(
                AnimationKeyframe(
                    timestamp = delay + 100L,
                    nodeId = comment.id,
                    animationType = AnimationType.SCALE_UP,
                    startValue = 0f,
                    endValue = 1f,
                    duration = 400L,
                    easing = AnimationEasing.BOUNCE
                )
            )
            
            // Glow pulse for influential comments
            if (comment.influence.influenceScore > 0.5f) {
                animations.add(
                    AnimationKeyframe(
                        timestamp = delay + 500L,
                        nodeId = comment.id,
                        animationType = AnimationType.GLOW_PULSE,
                        startValue = 0.3f,
                        endValue = 1f,
                        duration = 1000L,
                        easing = AnimationEasing.EASE_IN_OUT
                    )
                )
            }
        }
        
        return animations
    }
    
    private fun calculateThreadMetrics(comments: List<ThreadComment>): ThreadMetrics {
        val totalComments = comments.size
        val maxDepth = comments.maxOfOrNull { it.depth } ?: 0
        val branchingFactor = comments.map { it.kids?.size ?: 0 }.average().toFloat()
        
        val timeSpan = comments.maxOfOrNull { it.time } ?: 0L - (comments.minOfOrNull { it.time } ?: 0L)
        val averageResponseTime = if (totalComments > 1) timeSpan / totalComments else 0L
        
        val discussionVelocity = if (averageResponseTime > 0) 1000f / averageResponseTime else 0f
        
        val consensusLevel = comments.map { it.sentiment.agreement }.average().toFloat()
        
        val controversyHotspots = identifyControversyHotspots(comments)
        val echoChambersDetected = detectEchoChambers(comments)
        val keyInfluencers = identifyKeyInfluencers(comments)
        
        return ThreadMetrics(
            totalComments = totalComments,
            maxDepth = maxDepth,
            branchingFactor = branchingFactor,
            averageResponseTime = averageResponseTime,
            discussionVelocity = discussionVelocity,
            consensusLevel = consensusLevel,
            controversyHotspots = controversyHotspots,
            echoChambersDetected = echoChambersDetected,
            keyInfluencers = keyInfluencers
        )
    }
    
    private fun identifyControversyHotspots(comments: List<ThreadComment>): List<CommentCluster> {
        val hotspots = mutableListOf<CommentCluster>()
        
        comments.filter { it.influence.controversyLevel > 0.6f }.forEach { comment ->
            val relatedComments = comments.filter { 
                it.parent == comment.id || it.id == comment.parent 
            }.map { it.id }
            
            hotspots.add(
                CommentCluster(
                    centerCommentId = comment.id,
                    commentIds = relatedComments,
                    clusterType = ClusterType.CONTROVERSY_CLUSTER,
                    intensity = comment.influence.controversyLevel,
                    duration = 3600000L // 1 hour
                )
            )
        }
        
        return hotspots
    }
    
    private fun detectEchoChambers(comments: List<ThreadComment>): List<EchoChamber> {
        val echoChambers = mutableListOf<EchoChamber>()
        
        val userGroups = comments.filter { it.by != null }.groupBy { it.by!! }
        
        userGroups.values.filter { it.size >= 3 }.forEach { userComments ->
            val users = userComments.map { it.by!! }.distinct()
            val commentIds = userComments.map { it.id }
            val avgAgreement = userComments.map { it.sentiment.agreement }.average().toFloat()
            
            if (avgAgreement > 0.7f) {
                echoChambers.add(
                    EchoChamber(
                        participantIds = users,
                        commentIds = commentIds,
                        echoStrength = avgAgreement,
                        isolationLevel = 0.8f,
                        topicFocus = "General Agreement"
                    )
                )
            }
        }
        
        return echoChambers
    }
    
    private fun identifyKeyInfluencers(comments: List<ThreadComment>): List<InfluentialCommenter> {
        return comments
            .filter { it.influence.influenceScore > 0.6f }
            .map { comment ->
                InfluentialCommenter(
                    userId = comment.by ?: "anonymous",
                    commentId = comment.id,
                    influenceScore = comment.influence.influenceScore,
                    responseGenerated = comment.influence.replyCount,
                    discussionShift = comment.influence.threadDivergence
                )
            }
            .sortedByDescending { it.influenceScore }
            .take(10)
    }
    
    fun generateMatrixDataStream(comment: ThreadComment): MatrixDataStream {
        val text = comment.text ?: ""
        val chunks = text.chunked(20)
        
        return MatrixDataStream(
            commentId = comment.id,
            textChunks = chunks,
            scrollSpeed = comment.visualProperties.textScrollSpeed,
            color = comment.sentiment.emotion.color,
            opacity = comment.influence.influenceScore,
            trailLength = comment.visualProperties.matrixTrailLength,
            glitchEffect = comment.influence.controversyLevel > 0.7f
        )
    }
}