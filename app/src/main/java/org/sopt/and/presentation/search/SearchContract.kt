package org.sopt.and.presentation.search

import androidx.compose.runtime.Immutable
import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiSideEffect
import org.sopt.and.presentation.util.UiState

class SearchContract {
    @Immutable
    data class SearchUiState(
        val searchInput: String = "",
        val selectedTabIndex: Int = 0,
        val popularSeriesPosters: List<Pair<String, String>> = emptyList(),
        val popularMoviePosters: List<Pair<String, String>> = emptyList()
    ) : UiState

    sealed class SearchSideEffect : UiSideEffect {
        data class ShowSnackbar(val message: String) : SearchSideEffect()
        data class Toast(val message: String) : SearchSideEffect()
    }

    sealed class SearchEvent : UiEvent {
        data class FetchSearchPosters(
            val popularSeriesPosters: List<Pair<String, String>>,
            val popularMoviePosters: List<Pair<String, String>>
        ) : SearchEvent()

        data class OnTabClicked(val index: Int) : SearchEvent()
        data class OnHobbySearched(val no: String) : SearchEvent()
        data class OnSearchInputChanged(val searchInput: String) : SearchEvent()
    }
}
