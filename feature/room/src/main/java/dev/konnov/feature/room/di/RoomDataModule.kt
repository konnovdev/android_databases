package dev.konnov.feature.room.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.konnov.feature.room.data.database.NewsReportDao
import dev.konnov.feature.room.data.database.RoomAppDatabase
import dev.konnov.feature.room.data.database.WeatherLogDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomDataModule {

    @Singleton
    @Provides
    fun provideAppDb(
        @ApplicationContext appContext: Context
    ): RoomAppDatabase = Room.databaseBuilder(
        appContext,
        RoomAppDatabase::class.java, "room-db"
    ).build()

    @Singleton
    @Provides
    fun provideWeatherLogDao(
        db: RoomAppDatabase
    ): WeatherLogDao = db.weatherLogDao()

    @Singleton
    @Provides
    fun provideNewsReportDao(
        db: RoomAppDatabase
    ): NewsReportDao = db.newsReportDao()
}