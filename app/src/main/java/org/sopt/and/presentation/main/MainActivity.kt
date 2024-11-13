package org.sopt.and.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.data.datasource.local.LocalDataSource
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localDataSource: LocalDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val isLoggedIn = localDataSource.accessToken.isNotBlank()

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
