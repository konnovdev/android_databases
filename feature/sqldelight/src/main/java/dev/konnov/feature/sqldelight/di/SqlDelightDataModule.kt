package dev.konnov.feature.sqldelight.di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.MockDtoConverter
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.common.dbtestingtools.data.repository.DbTestRepositoryImpl
import dev.konnov.feature.sqldelight.Database
import dev.konnov.feature.sqldelight.NewsReportQueries
import dev.konnov.feature.sqldelight.WeatherLogQueries
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SqlDelightDataModule {

    @Singleton
    @Provides
    fun provideSqlDelightDriver(
        @ApplicationContext appContext: Context
    ): AndroidSqliteDriver =
        AndroidSqliteDriver(Database.Schema, appContext, "sqldelightspeedtest.db")

    @Singleton
    @Provides
    fun provideDatabase(
        driver: AndroidSqliteDriver
    ): Database = Database(driver)

    @Singleton
    @Provides
    fun provideWeatherLogQueries(
        sqldelightDb: Database
    ): WeatherLogQueries = sqldelightDb.weatherLogQueries

    @Singleton
    @Provides
    fun provideNewsReportQueries(
        sqldelightDb: Database
    ): NewsReportQueries = sqldelightDb.newsReportQueries

    @Singleton
    @Provides
    @Named("sqldelight_weather_repository")
    fun provideWeatherLogDbRepository(
        dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
        dbDataSource: DbDataSource<Double, WeatherLog>,
        testResultConverter: TestResultConverter,
        dtoConverter: MockDtoConverter<WeatherLog>
    ): DbTestRepositoryImpl<Double, WeatherLog, WeatherLog> =
        DbTestRepositoryImpl(
            dataSetDataSource,
            dbDataSource,
            testResultConverter,
            dtoConverter
        )

    @Singleton
    @Provides
    @Named("sqldelight_news_repository")
    fun provideNewsReportDbRepository(
        dataSetDataSource: DataSetDataSource<String, NewsReport>,
        dbDataSource: DbDataSource<String, NewsReport>,
        testResultConverter: TestResultConverter,
        dtoConverter: MockDtoConverter<NewsReport>
    ): DbTestRepositoryImpl<String, NewsReport, NewsReport> =
        DbTestRepositoryImpl(
            dataSetDataSource,
            dbDataSource,
            testResultConverter,
            dtoConverter
        )
}