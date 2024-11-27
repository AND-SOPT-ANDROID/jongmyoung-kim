package org.sopt.and.data.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.datasourceImpl.local.LocalPreferencesImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    private const val PREFERENCES_NAME = "prefs_name"

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideLocalPreferences(
        sharedPreferences: SharedPreferences
    ): LocalPreferencesImpl = LocalPreferencesImpl(sharedPreferences)
}
