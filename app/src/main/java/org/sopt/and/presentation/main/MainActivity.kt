package org.sopt.and.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.data.datasource.local.LocalPreferences
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var localPreferences: LocalPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val isLoggedIn = localPreferences.accessToken.isNotBlank()

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
