package org.sopt.and.presentation.home.uistate

import androidx.compose.runtime.Immutable

@Immutable
data class HomeUiState(
    val isLoading: Boolean = false,
    val banners: List<Pair<String, String>> = emptyList(),
    val posters: List<Pair<String, List<String>>> = emptyList(),
    val rankedPosters: Pair<String, List<String>> = Pair("", emptyList())
)
