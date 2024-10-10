package org.sopt.and.presentation.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.sopt.and.presentation.authentication.components.SignUpTopBar
import org.sopt.and.presentation.theme.ANDANDROIDTheme

class SignUpActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ANDANDROIDTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        SignUpTopBar(
                            onCancelClick = { navigateBack() }
                        )
                    }
                ) { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignUpClick = { email, password ->
                            registerUser(email, password)
                        }
                    )
                }
            }
        }
    }
    private fun registerUser(email: String, password: String) {
        val signInIntent = Intent(this, SignInActivity::class.java).apply {
            putExtra("EMAIL", email)
            putExtra("PASSWORD", password)
        }
        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()
        startActivity(signInIntent)
        finish()
    }

    private fun navigateBack() {
        finish()
    }
}

fun isValidEmail(email: String): Pair<Boolean, String> {
    val emailRegex = "[0-9a-zA-Z]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$".toRegex()
    return when {
        email.length < 5 -> Pair(false, "5~50자의 이메일 형식으로 입력해주세요.")
        email.length >= 5 && !emailRegex.matches(email) -> Pair(false, "입력하신 이메일 주소가 형식에 맞지 않습니다. 다시 입력해주세요.")
        else -> Pair(true, "로그인, 비밀번호 찾기, 알림에 사용되니 정확한 이메일을 입력해 주세요.")
    }
}

fun isValidPassword(password: String): Boolean {
    val passwordRegex = ("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])|(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#\$%^&*])|" +
            "(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])|(?=.*[a-z])(?=.*[0-9])(?=.*[!@#\$%^&*]).{8,20}$").toRegex()
    return passwordRegex.matches(password)
}