package org.sopt.and.presentation.home

import androidx.annotation.StringRes
import org.sopt.and.R

enum class HomeTab(
    @StringRes val title: Int,
) {
    NEW_CLASSIC(
        title = R.string.new_classic
    ),
    DRAMA(
        title = R.string.drama
    ),
    ENTERTAINMENT(
        title = R.string.entertainment
    ),
    MOVIE(
        title = R.string.movie
    ),
    ANIMATION(
        title = R.string.animation
    ),
    OVERSEAS(
        title = R.string.overseas
    ),
    CURRENT_AFFAIRS(
        title = R.string.current_affairs
    ),
    KIDS(
        title = R.string.kids
    ),
    MOVIE_PLUS(
        title = R.string.movie_plus
    )
}