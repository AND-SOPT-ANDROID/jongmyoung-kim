package org.sopt.and.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.data.local.UserDataSource
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userDataSource: UserDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isLoggedIn = userDataSource.getUserInfo() != Pair("", "")

        setContent {
            val navController = rememberNavController()

            ANDANDROIDTheme {
                MainScreen(
                    navController = navController,
                    isLoggedIn = isLoggedIn
                )
            }
        }
    }
}
