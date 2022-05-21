package dev.konnov.feature.sqliteopenhelper.di

import android.content.Context
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
import dev.konnov.feature.sqliteopenhelper.data.database.SqliteOpenHelperDbManager
import dev.konnov.feature.sqliteopenhelper.data.datasource.NewsDbDataSource
import dev.konnov.feature.sqliteopenhelper.data.datasource.WeatherDbDataSource
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SqliteOpenHelperDataModule {

    @Singleton
    @Provides
    fun provideSqliteOpenHelperDbManager(
        @ApplicationContext appContext: Context
    ): SqliteOpenHelperDbManager = SqliteOpenHelperDbManager(
        appContext
    )

    @Singleton
    @Provides
    fun provideWeatherDbDataSource(
        sqliteOpenHelperDbManager: SqliteOpenHelperDbManager
    ): DbDataSource<Double, WeatherLog> =
        WeatherDbDataSource(sqliteOpenHelperDbManager)

    @Singleton
    @Provides
    fun provideNewsDbDataSource(
        sqliteOpenHelperDbManager: SqliteOpenHelperDbManager
    ): DbDataSource<String, NewsReport> =
        NewsDbDataSource(sqliteOpenHelperDbManager)

    @Singleton
    @Provides
    @Named("sqliteopenhelper_weather_repository")
    fun provideWeatherDbRepository(
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
    @Named("sqliteopenhelper_news_repository")
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
