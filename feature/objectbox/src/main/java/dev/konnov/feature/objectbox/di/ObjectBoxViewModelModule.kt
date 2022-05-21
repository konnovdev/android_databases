package dev.konnov.feature.objectbox.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.repository.DbTestRepositoryImpl
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.feature.objectbox.data.model.NewsReportDto
import dev.konnov.feature.objectbox.data.model.WeatherLogDto
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class ObjectBoxViewModelModule {

    @Provides
    @Named("Objectbox_usecase")
    fun provideTestSpeedUseCase(
        weatherDbTestRepository: DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDto>,
        newsDbTestRepository: DbTestRepositoryImpl<String, NewsReport, NewsReportDto>
    ): TestSpeedUseCase =
        TestSpeedUseCase(
            weatherDbTestRepository,
            newsDbTestRepository)
}