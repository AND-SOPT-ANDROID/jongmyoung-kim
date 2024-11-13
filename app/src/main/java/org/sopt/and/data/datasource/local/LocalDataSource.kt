package org.sopt.and.data.datasource.local


interface LocalDataSource {
    var accessToken: String
    fun clearInfo()
}
