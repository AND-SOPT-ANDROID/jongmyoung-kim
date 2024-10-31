package org.sopt.and.presentation.search.uistate

import androidx.compose.runtime.Immutable

@Immutable
data class SearchUiState(
    val searchInput: String = "",
    val selectedTabIndex: Int = 0,
    val popularSeriesPosters: List<Pair<String, String>> = emptyList(),
    val popularMoviePosters: List<Pair<String, String>> = emptyList()
)
