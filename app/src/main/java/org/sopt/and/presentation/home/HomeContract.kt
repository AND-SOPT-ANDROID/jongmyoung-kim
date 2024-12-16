package org.sopt.and.presentation.home

import androidx.compose.runtime.Immutable
import org.sopt.and.presentation.util.UiEvent
import org.sopt.and.presentation.util.UiSideEffect
import org.sopt.and.presentation.util.UiState

class HomeContract {
    @Immutable
    data class HomeUiState(
        val isLoading: Boolean = false,
        val banners: List<Pair<String, String>> = emptyList(),
        val posters: List<Pair<String, List<String>>> = emptyList(),
        val rankedPosters: Pair<String, List<String>> = Pair("", emptyList())
    ) : UiState

    sealed class HomeSideEffect : UiSideEffect

    sealed class HomeEvent : UiEvent {
        data class FetchMainBannersAndPosters(
            val banners: List<Pair<String, String>>,
            val posters: List<Pair<String, List<String>>>,
            val rankedPosters: Pair<String, List<String>>
        ) : HomeEvent()
    }
}
