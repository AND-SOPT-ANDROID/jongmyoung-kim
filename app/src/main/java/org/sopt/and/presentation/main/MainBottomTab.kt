package org.sopt.and.presentation.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.and.R
import org.sopt.and.presentation.navigation.Route

enum class MainBottomTab(
    @DrawableRes val iconResId: Int,
    @StringRes val contentDescription: Int,
    val route: Route,
) {
    HOME(
        iconResId = R.drawable.ic_home,
        contentDescription = R.string.home,
        route = Route.Home
    ),
    SEARCH(
        iconResId = R.drawable.ic_search,
        contentDescription = R.string.search,
        route = Route.Search
    ),
    MY_PAGE(
        iconResId = R.drawable.logo_wavve_app,
        contentDescription = R.string.my_page,
        route = Route.MyPage
    )
}
