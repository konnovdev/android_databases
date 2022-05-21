package dev.konnov.feature.sqldelight.di

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
class SqlDelightViewModelModule {

    @Provides
    @Named("Sqldelight_usecase")
    fun provideTestSpeedUseCase(
        @Named("sqldelight_news_repository")
        newsRepository: DbTestRepositoryImpl<String, NewsReport, NewsReport>,
        @Named("sqldelight_weather_repository")
        weatherRepository: DbTestRepositoryImpl<Double, WeatherLog, WeatherLog>
    ): TestSpeedUseCase =
        TestSpeedUseCase(newsRepository, weatherRepository)
}