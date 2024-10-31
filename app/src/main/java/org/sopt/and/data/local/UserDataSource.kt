package org.sopt.and.data.local

import android.content.SharedPreferences
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    // 임시 회원가입 저장소
    fun setRemoteUserInfo(email: String, password: String) {
        sharedPreferences.edit().putString(REMOTE_USER_EMAIL, email).apply()
        sharedPreferences.edit().putString(REMOTE_USER_PASSWORD, password).apply()
    }

    fun getRemoteUserInfo(): Pair<String, String> {
        val email = sharedPreferences.getString(REMOTE_USER_EMAIL, "") ?: ""
        val password = sharedPreferences.getString(REMOTE_USER_PASSWORD, "") ?: ""
        return Pair(email, password)
    }

    fun setUserInfo(email: String, password: String) {
        sharedPreferences.edit().putString(USER_EMAIL, email).apply()
        sharedPreferences.edit().putString(USER_PASSWORD, password).apply()
    }

    fun getUserInfo(): Pair<String, String> {
        val email = sharedPreferences.getString(USER_EMAIL, "") ?: ""
        val password = sharedPreferences.getString(USER_PASSWORD, "") ?: ""
        return Pair(email, password)
    }

    fun clearUserInfo() {
        sharedPreferences.edit().remove(USER_EMAIL).apply()
        sharedPreferences.edit().remove(USER_PASSWORD).apply()
    }

    companion object {
        private const val REMOTE_USER_EMAIL = "remote_user_email"
        private const val REMOTE_USER_PASSWORD = "remote_user_password"
        private const val USER_EMAIL = "user_email"
        private const val USER_PASSWORD = "user_password"
    }
}