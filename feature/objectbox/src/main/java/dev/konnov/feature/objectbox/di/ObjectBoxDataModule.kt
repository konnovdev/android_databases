package dev.konnov.feature.objectbox.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.repository.DbTestRepositoryImpl
import dev.konnov.feature.objectbox.data.converter.NewsReportDtoConverter
import dev.konnov.feature.objectbox.data.converter.WeatherDtoConverter
import dev.konnov.feature.objectbox.data.datasource.NewsDbDataSource
import dev.konnov.feature.objectbox.data.datasource.WeatherDbDataSource
import dev.konnov.feature.objectbox.data.model.MyObjectBox
import dev.konnov.feature.objectbox.data.model.NewsReportDto
import dev.konnov.feature.objectbox.data.model.WeatherLogDto
import io.objectbox.Box
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

    @Singleton
    @Provides
    fun provideBoxWeather(
        boxStore: BoxStore
    ): Box<WeatherLogDto> =
        boxStore.boxFor(WeatherLogDto::class.java)

    @Singleton
    @Provides
    fun provideBoxNews(
        boxStore: BoxStore
    ): Box<NewsReportDto> =
        boxStore.boxFor(NewsReportDto::class.java)

    @Singleton
    @Provides
    fun provideWeatherDbRepository(
        dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
        dbDataSource: WeatherDbDataSource,
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
        dbDataSource: NewsDbDataSource,
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