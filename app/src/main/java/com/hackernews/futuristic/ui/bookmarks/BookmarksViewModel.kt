package com.hackernews.futuristic.ui.bookmarks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackernews.futuristic.data.model.Story
import com.hackernews.futuristic.data.repository.HackerNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarksViewModel @Inject constructor(
    private val repository: HackerNewsRepository
) : ViewModel() {

    /** Null until the first read from the database completes. */
    val bookmarkedStories: StateFlow<List<Story>?> = repository.getBookmarkedStories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun removeBookmark(story: Story) {
        viewModelScope.launch { repository.removeBookmark(story.id) }
    }
}
