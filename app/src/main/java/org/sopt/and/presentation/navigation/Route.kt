package org.sopt.and.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object SignIn : Route()

    @Serializable
    data object SignUp : Route()

    @Serializable
    data object Home : Route()

    @Serializable
    data object Search : Route()

    @Serializable
    data object MyPage : Route()
}
