package dev.konnov.feature.sqliteopenhelper.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.repository.DbTestRepositoryImpl
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class SqliteOpenHelperViewModelModule {

    @Provides
    @Named("Sqliteopenhelper_usecase")
    fun provideTestSpeedUseCase(
        @Named("sqliteopenhelper_weather_repository")
        weatherDbTestRepository: DbTestRepositoryImpl<Double, WeatherLog, WeatherLog>,
        @Named("sqliteopenhelper_news_repository")
        newsDbTestRepository: DbTestRepositoryImpl<String, NewsReport, NewsReport>
    ): TestSpeedUseCase =
        TestSpeedUseCase(weatherDbTestRepository, newsDbTestRepository)
}
