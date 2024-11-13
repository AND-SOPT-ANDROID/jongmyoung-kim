package org.sopt.and.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.repository.AuthRepositoryImpl
import org.sopt.and.data.repository.MyPageRepositoryImpl
import org.sopt.and.domain.repository.AuthRepository
import org.sopt.and.domain.repository.MyPageRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindMyPageRepository(
        myPageRepositoryImpl: MyPageRepositoryImpl
    ): MyPageRepository
}