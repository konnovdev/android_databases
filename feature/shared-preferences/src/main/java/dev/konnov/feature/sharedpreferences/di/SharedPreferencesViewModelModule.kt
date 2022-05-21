package dev.konnov.feature.sharedpreferences.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.repository.DbTestRepositoryImpl
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.feature.sharedpreferences.data.model.NewsReportDto
import dev.konnov.feature.sharedpreferences.data.model.WeatherLogDto
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class SharedPreferencesViewModelModule {

    @Provides
    @Named("Sharedprefs_usecase")
    fun provideTestSpeedUseCase(
        newsRepository: DbTestRepositoryImpl<String, NewsReport, NewsReportDto>,
        weatherRepository: DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDto>
    ): TestSpeedUseCase =
        TestSpeedUseCase(
            listOf(
                newsRepository,
                weatherRepository
            )
        )
}