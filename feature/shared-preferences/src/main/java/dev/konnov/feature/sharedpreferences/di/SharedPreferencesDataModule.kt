package dev.konnov.feature.sharedpreferences.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
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
import dev.konnov.common.dbtestingtools.data.repository.DbTestRepositoryImpl
import dev.konnov.feature.sharedpreferences.data.converter.NewsReportConverter
import dev.konnov.feature.sharedpreferences.data.converter.WeatherLogConverter
import dev.konnov.feature.sharedpreferences.data.datasource.NewsDbDataSource
import dev.konnov.feature.sharedpreferences.data.datasource.WeatherDbDataSource
import dev.konnov.feature.sharedpreferences.data.model.NewsReportDto
import dev.konnov.feature.sharedpreferences.data.model.WeatherLogDto
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SharedPreferencesDataModule {

    @Singleton
    @Provides
    @Named("news_prefs")
    fun provideNewsSharedPreferences(
        @ApplicationContext appContext: Context
    ): SharedPreferences = appContext.getSharedPreferences("news_shared_preferences", MODE_PRIVATE)

    @Singleton
    @Provides
    @Named("weather_prefs")
    fun provideWeatherSharedPreferences(
        @ApplicationContext appContext: Context
    ): SharedPreferences = appContext.getSharedPreferences("weather_shared_preferences", MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideNewsDbDataSource(
        @Named("news_prefs")
        sharedPreferences: SharedPreferences
    ): DbDataSource<String, NewsReportDto> =
        NewsDbDataSource(sharedPreferences)

    @Singleton
    @Provides
    fun provideWeatherDbDataSource(
        @Named("news_prefs")
        sharedPreferences: SharedPreferences
    ): DbDataSource<Double, WeatherLogDto> =
        WeatherDbDataSource(sharedPreferences)


    @Singleton
    @Provides
    fun provideNewsReportDbRepository(
        dataSetDataSource: DataSetDataSource<String, NewsReport>,
        dbDataSource: DbDataSource<String, NewsReportDto>,
        testResultConverter: TestResultConverter,
        dtoConverter: NewsReportConverter
    ): DbTestRepositoryImpl<String, NewsReport, NewsReportDto> =
        DbTestRepositoryImpl(
            dataSetDataSource,
            dbDataSource,
            testResultConverter,
            dtoConverter
        )

    @Singleton
    @Provides
    fun provideWeatherLogDbRepository(
        dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
        dbDataSource: DbDataSource<Double, WeatherLogDto>,
        testResultConverter: TestResultConverter,
        dtoConverter: WeatherLogConverter
    ): DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDto> =
        DbTestRepositoryImpl(
            dataSetDataSource,
            dbDataSource,
            testResultConverter,
            dtoConverter
        )
}