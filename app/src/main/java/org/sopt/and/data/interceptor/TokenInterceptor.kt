package org.sopt.and.data.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.sopt.and.data.datasource.local.LocalDataSource
import javax.inject.Inject


class TokenInterceptor @Inject constructor(
    private val localDataSource: LocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = localDataSource.accessToken
        val request = if (accessToken.isNotEmpty()) chain.request().putTokenHeader() else chain.request()
        return chain.proceed(request)
    }

    private fun Request.putTokenHeader() = this.newBuilder()
        .addHeader(AUTHORIZATION, localDataSource.accessToken)
        .build()

    companion object {
        private const val AUTHORIZATION = "token"
    }
}
