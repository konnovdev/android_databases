package dev.konnov.feature.room.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepositoryImpl
import dev.konnov.feature.room.data.converter.NewsReportDtoConverter
import dev.konnov.feature.room.data.converter.WeatherDtoConverter
import dev.konnov.feature.room.data.database.NewsReportDao
import dev.konnov.feature.room.data.database.RoomAppDatabase
import dev.konnov.feature.room.data.database.WeatherLogDao
import dev.konnov.feature.room.data.datasource.NewsDbDataSource
import dev.konnov.feature.room.data.datasource.WeatherDbDataSource
import dev.konnov.feature.room.data.model.NewsReportDto
import dev.konnov.feature.room.data.model.WeatherLogDto
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

    @Singleton
    @Provides
    fun provideWeatherDbDataSource(
        weatherLogDao: WeatherLogDao
    ): DbDataSource<Double, WeatherLogDto> =
        WeatherDbDataSource(weatherLogDao)

    @Singleton
    @Provides
    fun provideNewsDbDataSource(
        newsReportDao: NewsReportDao
    ): DbDataSource<String, NewsReportDto> =
        NewsDbDataSource(newsReportDao)

    @Singleton
    @Provides
    fun provideWeatherDbRepository(
        dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
        dbDataSource: DbDataSource<Double, WeatherLogDto>,
        testResultConverter: TestResultConverter,
        dtoConverter: WeatherDtoConverter
    ): DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDto> =
        DbTestRepositoryImpl(
            dataSetDataSource,
            dbDataSource,
            testResultConverter,
            dtoConverter
        )

    @Singleton
    @Provides
    fun provideNewsReportDbRepository(
        dataSetDataSource: DataSetDataSource<String, NewsReport>,
        dbDataSource: DbDataSource<String, NewsReportDto>,
        testResultConverter: TestResultConverter,
        dtoConverter: NewsReportDtoConverter
    ): DbTestRepositoryImpl<String, NewsReport, NewsReportDto> =
        DbTestRepositoryImpl(
            dataSetDataSource,
            dbDataSource,
            testResultConverter,
            dtoConverter
        )
}
