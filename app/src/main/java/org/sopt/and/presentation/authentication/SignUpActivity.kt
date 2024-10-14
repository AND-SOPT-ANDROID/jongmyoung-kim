package org.sopt.and.presentation.authentication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.sopt.and.R
import org.sopt.and.presentation.authentication.components.SignUpTopBar
import org.sopt.and.presentation.theme.ANDANDROIDTheme
import org.sopt.and.presentation.util.Constants.Companion.MIN_EMAIL
import org.sopt.and.presentation.util.Constants.Companion.MIN_PASSWORD

class SignUpActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        SignUpTopBar(
                            onCancelClick = { navigateBack() }
                        )
                    },
                    snackbarHost = { SnackbarHost(snackbarHostState) }
                ) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignUpClick = { emailInput, passwordInput ->
                            userRegister(
                                emailInput,
                                passwordInput,
                                snackbarHostState,
                                coroutineScope
                            )
                        }
                    )
                }
            }
        }
    }
    private fun userRegister(
        email: String,
        password: String,
        snackbarHostState: SnackbarHostState,
        coroutineScope: CoroutineScope
    ) {
        val isEmailValid = isValidEmail(email)
        val isPasswordValid = isValidPassword(password)
        if (isEmailValid && isPasswordValid) {
            Intent(this, SignInActivity::class.java).apply {
                putExtra("email", email)
                putExtra("password", password)
                putExtra("snackbar_message", getString(R.string.sign_up_success))
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
                finish()
            }
        } else {
            coroutineScope.launch {
                when {
                    email.length < MIN_EMAIL -> { snackbarHostState.showSnackbar(getString(R.string.sign_up_email_error_length)) }
                    !isEmailValid -> { snackbarHostState.showSnackbar(getString(R.string.sign_up_email_error_regex)) }
                    password.length < MIN_PASSWORD -> { snackbarHostState.showSnackbar(getString(R.string.sign_up_password_noti)) }
                }
            }
        }
    }

    private fun navigateBack() {
        finish()
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$".toRegex()
    return when {
        email.length < 5 -> false
        email.length >= 5 && !emailRegex.matches(email) -> false
        else -> true
    }
}

fun isValidPassword(password: String): Boolean {
    val passwordRegex = ("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#\$%^&*])|" +
            "(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])|(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%^&*]).{8,20}$").toRegex()
    return passwordRegex.matches(password)
}