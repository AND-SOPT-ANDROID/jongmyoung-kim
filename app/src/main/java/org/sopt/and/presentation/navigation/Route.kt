package org.sopt.and.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object SignIn : Route

    @Serializable
    data object SignUp : Route

    @Serializable
    data object MainTabRoute : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Search : MainTabRoute

    @Serializable
    data object MyPage : MainTabRoute
}
//
//sealed class Route {
//    @Serializable
//    data object SignIn : Route()
//
//    @Serializable
//    data object SignUp : Route()
//
//    @Serializable
//    data object Home : Route()
//
//    @Serializable
//    data object Search : Route()
//
//    @Serializable
//    data object MyPage : Route()
//}
