package dev.konnov.feature.sharedpreferences.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesDataModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext appContext: Context
    ): SharedPreferences = appContext.getSharedPreferences("test_preferences", MODE_PRIVATE)
}