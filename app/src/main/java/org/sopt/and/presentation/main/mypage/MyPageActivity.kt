package org.sopt.and.presentation.main.mypage

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import org.sopt.and.presentation.theme.ANDANDROIDTheme

class MyPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val userEmail = sharedPreferences.getString("user_email", "") ?: ""

        setContent {
            ANDANDROIDTheme {
                Surface {
                    MyPageScreen(
                        userEmail = userEmail
                    )
                }
            }
        }
    }
}