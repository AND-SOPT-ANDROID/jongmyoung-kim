package org.sopt.and.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import org.sopt.and.presentation.authentication.SignInActivity
import org.sopt.and.presentation.main.mypage.MyPageActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)

        val intent = if (isLoggedIn) {
            Intent(this, MyPageActivity::class.java)
        } else {
            Intent(this, SignInActivity::class.java)
        }

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}