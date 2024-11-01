package org.sopt.and.presentation.main

import android.util.Log
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.launch
import org.sopt.and.presentation.authentication.SignInScreen
import org.sopt.and.presentation.authentication.SignUpScreen
import org.sopt.and.presentation.home.HomeScreen
import org.sopt.and.presentation.main.components.MainBottomBar
import org.sopt.and.presentation.mypage.MyPageScreen
import org.sopt.and.presentation.navigation.MainTabRoute
import org.sopt.and.presentation.navigation.Route
import org.sopt.and.presentation.search.SearchScreen
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.theme.AndAndroidTheme


@Composable
fun MainScreen(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    val startDestination = if (isLoggedIn) MainTabRoute.Home else Route.SignIn
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        containerColor = AndAndroidTheme.colors.gray500,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = {
            MainBottomBar(
                tabs = MainBottomTab.entries,
                onTabSelected = { tab ->
                    navController.navigate(tab.route) {
                        popUpTo(0) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                navController = navController
            )
        }
    ) { innerPadding ->
        MainNavigation(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = startDestination,
            showSnackbar = { message ->
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            },
        )
    }
}

@Composable
fun MainNavigation(
    navController: NavHostController,
    startDestination: Any,
    showSnackbar: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable<MainTabRoute.Home> {
            HomeScreen()
        }
        composable<MainTabRoute.Search> {
            SearchScreen()
        }
        composable<MainTabRoute.MyPage> {
            MyPageScreen(
                onNavigateToSignIn = { message ->
                    val navOptions = navOptions {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                    navController.navigate(Route.SignIn, navOptions)
                    message?.let { showSnackbar(it) }
                }
            )
        }
        composable<Route.SignIn> {
            SignInScreen(
                onNavigateToHome = { message ->
                    val navOptions = navOptions {
                        popUpTo(Route.SignIn) {
                            inclusive = true
                        }
                    }
                    navController.navigate(MainTabRoute.Home, navOptions)
                    message?.let { showSnackbar(it) }
                },
                onNavigateToSignUp = { navController.navigate(Route.SignUp) },
            )
        }
        composable<Route.SignUp> {
            SignUpScreen(
                onNavigateToSignIn = { message ->
                    navController.navigateUp()
                    message?.let { showSnackbar(it) }
                }
            )
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    ANDANDROIDTheme {
        MainScreen(
            navController = rememberNavController(),
            isLoggedIn = false
        )
    }
}
