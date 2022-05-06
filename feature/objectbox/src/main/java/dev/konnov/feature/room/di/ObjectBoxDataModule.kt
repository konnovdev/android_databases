package dev.konnov.feature.room.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.konnov.feature.room.testik.MyObjectBox
import io.objectbox.BoxStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ObjectBoxDataModule {

    @Singleton
    @Provides
    fun provideBoxStore(
        @ApplicationContext appContext: Context
    ): BoxStore =
        MyObjectBox.builder().androidContext(appContext).build()
}