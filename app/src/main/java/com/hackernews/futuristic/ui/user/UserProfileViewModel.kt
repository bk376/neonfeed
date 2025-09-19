package com.hackernews.futuristic.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.api.User
import com.hackernews.futuristic.data.model.Comment
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(UserProfileUiState())
    val uiState: StateFlow<UserProfileUiState> = _uiState.asStateFlow()
    
    fun loadUserProfile(username: String) {
        if (username.isEmpty()) return
        
        _uiState.value = _uiState.value.copy(
            isLoading = true,
            error = null
        )
        
        viewModelScope.launch {
            try {
                // Load user profile
                repository.getUser(username).fold(
                    onSuccess = { user ->
                        _uiState.value = _uiState.value.copy(
                            user = user,
                            isLoading = false
                        )
                        
                        // Load user submissions
                        loadUserSubmissions(username)
                        loadUserComments(username)
                    },
                    onFailure = { error ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = error.message ?: "Failed to load user profile"
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error"
                )
            }
        }
    }
    
    private fun loadUserSubmissions(username: String) {
        viewModelScope.launch {
            repository.getUserSubmissions(username, limit = 10).fold(
                onSuccess = { submissions ->
                    _uiState.value = _uiState.value.copy(
                        submissions = submissions,
                        isLoadingSubmissions = false
                    )
                },
                onFailure = { 
                    _uiState.value = _uiState.value.copy(
                        isLoadingSubmissions = false,
                        submissionsError = "Failed to load submissions"
                    )
                }
            )
        }
    }
    
    private fun loadUserComments(username: String) {
        viewModelScope.launch {
            repository.getUserComments(username, limit = 10).fold(
                onSuccess = { comments ->
                    _uiState.value = _uiState.value.copy(
                        comments = comments,
                        isLoadingComments = false
                    )
                },
                onFailure = { 
                    _uiState.value = _uiState.value.copy(
                        isLoadingComments = false,
                        commentsError = "Failed to load comments"
                    )
                }
            )
        }
    }
    
    fun retry(username: String) {
        loadUserProfile(username)
    }
    
    suspend fun getStoryById(storyId: Long): Story? {
        return try {
            repository.getStory(storyId).getOrNull()
        } catch (e: Exception) {
            null
        }
    }
}

data class UserProfileUiState(
    val user: User? = null,
    val submissions: List<Story> = emptyList(),
    val comments: List<Comment> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingSubmissions: Boolean = true,
    val isLoadingComments: Boolean = true,
    val error: String? = null,
    val submissionsError: String? = null,
    val commentsError: String? = null
)