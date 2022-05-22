package dev.konnov.feature.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
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
import dev.konnov.feature.datastore.NewsReportDto
import dev.konnov.feature.datastore.NewsReportList
import dev.konnov.feature.datastore.WeatherLogDto
import dev.konnov.feature.datastore.WeatherLogList
import dev.konnov.feature.datastore.data.converter.NewsConverter
import dev.konnov.feature.datastore.data.converter.WeatherConverter
import dev.konnov.feature.datastore.data.datasource.NewsDbDataSource
import dev.konnov.feature.datastore.data.datasource.WeatherDbDataSource
import dev.konnov.feature.datastore.data.serializer.newsReportListDataStore
import dev.konnov.feature.datastore.data.serializer.weatherLogListDataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreDataModule {

    @Singleton
    @Provides
    fun provideWeatherDataStore(
        @ApplicationContext context: Context
    ): DataStore<WeatherLogList> = context.weatherLogListDataStore

    @Singleton
    @Provides
    fun provideNewsDataStore(
        @ApplicationContext context: Context
    ): DataStore<NewsReportList> = context.newsReportListDataStore

    @Singleton
    @Provides
    fun provideWeatherDbRepository(
        dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
        dbDataSource: WeatherDbDataSource,
        testResultConverter: TestResultConverter,
        dtoConverter: WeatherConverter
    ): DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDto> =
        DbTestRepositoryImpl(
            dataSetDataSource,
            dbDataSource,
            testResultConverter,
            dtoConverter
        )

    @Singleton
    @Provides
    fun provideNewsDbRepository(
        dataSetDataSource: DataSetDataSource<String, NewsReport>,
        dbDataSource: NewsDbDataSource,
        testResultConverter: TestResultConverter,
        dtoConverter: NewsConverter
    ): DbTestRepositoryImpl<String, NewsReport, NewsReportDto> =
        DbTestRepositoryImpl(
            dataSetDataSource,
            dbDataSource,
            testResultConverter,
            dtoConverter
        )
}