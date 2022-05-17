package dev.konnov.feature.realm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.repository.DbTestRepositoryImpl
import dev.konnov.feature.realm.data.converter.NewsReportDtoConverter
import dev.konnov.feature.realm.data.converter.WeatherDtoConverter
import dev.konnov.feature.realm.data.datasource.NewsDbDataSource
import dev.konnov.feature.realm.data.datasource.WeatherDbDataSource
import dev.konnov.feature.realm.data.model.NewsReportDto
import dev.konnov.feature.realm.data.model.NewsReportDtoWrapper
import dev.konnov.feature.realm.data.model.WeatherLogDto
import dev.konnov.feature.realm.data.model.WeatherLogDtoWrapper
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RealmDataModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val configuration = RealmConfiguration.with(
            schema = setOf(
                NewsReportDto::class,
                WeatherLogDto::class
            )
        )
        return Realm.open(configuration) // can also use RealmConfiguration.Builder for more options
    }

    @Singleton
    @Provides
    fun provideWeatherDbRepository(
        dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
        dbDataSource: WeatherDbDataSource,
        testResultConverter: TestResultConverter,
        dtoConverter: WeatherDtoConverter
    ): DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDtoWrapper> =
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
    ): DbTestRepositoryImpl<String, NewsReport, NewsReportDtoWrapper> =
        DbTestRepositoryImpl(
            dataSetDataSource,
            dbDataSource,
            testResultConverter,
            dtoConverter
        )
}