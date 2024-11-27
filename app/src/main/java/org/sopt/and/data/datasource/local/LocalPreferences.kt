package org.sopt.and.data.datasource.local


interface LocalPreferences {
    var accessToken: String
    fun clearInfo()
}
