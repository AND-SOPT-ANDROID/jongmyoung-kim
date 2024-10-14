package org.sopt.and.presentation.main.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.util.Constants.Companion.PREFS_NAME
import org.sopt.and.presentation.util.Constants.Companion.USER_EMAIL

class MyPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val userEmail = sharedPreferences.getString(USER_EMAIL, "") ?: ""
            val snackbarHostState = remember { SnackbarHostState() }
            val snackbarMessage = intent.getStringExtra("snackbar_message") ?: ""

            LaunchedEffect(snackbarMessage) {
                if (snackbarMessage.isNotEmpty()) {
                    snackbarHostState.showSnackbar(snackbarMessage)
                }
            }

            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    MyPageScreen(
                        modifier = Modifier.padding(innerPadding),
                        userEmail = userEmail
                    )
                }
            }
        }
    }
}