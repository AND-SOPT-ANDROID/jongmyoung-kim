package org.sopt.and.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.datasource.local.LocalPreferences
import org.sopt.and.data.datasource.remote.AuthRemoteDataSource
import org.sopt.and.data.datasource.remote.MyPageRemoteDataSource
import org.sopt.and.data.datasourceImpl.local.LocalPreferencesImpl
import org.sopt.and.data.datasourceImpl.remote.AuthRemoteDataSourceImpl
import org.sopt.and.data.datasourceImpl.remote.MyPageRemoteDataSourceImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindAuthRemoteDataSource(
        authRemoteDataSourceImpl: AuthRemoteDataSourceImpl
    ): AuthRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindMyPageRemoteDataSource(
        myPageRemoteDataSourceImpl: MyPageRemoteDataSourceImpl
    ): MyPageRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalPreferences(
        localPreferencesImpl: LocalPreferencesImpl
    ): LocalPreferences
}
