package org.sopt.and.presentation.search.sideeffect


sealed class SearchSideEffect {
    data class ShowSnackbar(val message: String) : SearchSideEffect()
    data class Toast(val message: String) : SearchSideEffect()
}
