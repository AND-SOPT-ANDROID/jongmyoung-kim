package org.sopt.and.data.datasourceImpl.local

import android.content.SharedPreferences
import org.sopt.and.data.datasource.local.LocalDataSource
import javax.inject.Inject


class LocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalDataSource {

    override var accessToken: String
        get() = sharedPreferences.getString(ACCESS_TOKEN, DEFAULT).orEmpty()
        set(value) = sharedPreferences.edit().putString(ACCESS_TOKEN, value).apply()

    override fun clearInfo() = sharedPreferences.edit().clear().apply()

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val DEFAULT = ""
    }
}
