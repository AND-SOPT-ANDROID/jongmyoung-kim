package org.sopt.and.presentation.authentication

import android.content.Context
import android.content.Intent
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.presentation.main.mypage.MyPageActivity
import org.sopt.and.presentation.theme.ANDANDROIDTheme

class SignInActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val email = intent.getStringExtra("email") ?: ""
            val password = intent.getStringExtra("password") ?: ""
            val snackbarMessage = intent.getStringExtra("snackbar_message") ?: ""
            val coroutineScope = rememberCoroutineScope()

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
                    SignInScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignUpClick = { navigateToSignUpScreen() },
                        onSignInClick = { emailInput, passwordInput->
                            userSignIn(
                                emailInput,
                                passwordInput,
                                email,
                                password,
                                snackbarHostState,
                                coroutineScope
                            )
                        }
                    )
                }
            }
        }
    }

    private fun navigateToSignUpScreen() {
        val signUpIntent = Intent(this, SignUpActivity::class.java)
        startActivity(signUpIntent)
    }

    private fun userSignIn(
        emailInput: String,
        passwordInput: String,
        correctEmail: String,
        correctPassword: String,
        snackbarHostState: SnackbarHostState,
        coroutineScope: CoroutineScope
    ) {
        if (emailInput == correctEmail && passwordInput == correctPassword && emailInput.isNotEmpty() && passwordInput.isNotEmpty()) {
            val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
            with(sharedPreferences.edit()) {
                putBoolean("is_logged_in", true)
                putString("user_email", emailInput)
                apply()
            }
            Intent(this, MyPageActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra("snackbar_message", getString(R.string.sign_in_success))
                startActivity(this)
                finish()
            }
        } else {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(getString(R.string.sign_in_failed))
            }
        }
    }
}

