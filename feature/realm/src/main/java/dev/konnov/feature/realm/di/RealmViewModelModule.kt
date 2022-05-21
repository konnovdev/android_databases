package dev.konnov.feature.realm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.repository.DbTestRepositoryImpl
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.feature.realm.data.model.NewsReportDtoWrapper
import dev.konnov.feature.realm.data.model.WeatherLogDtoWrapper
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class RealmViewModelModule {

    @Provides
    @Named("Realm_usecase")
    fun provideTestSpeedUseCase(
        weatherDbTestRepository: DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDtoWrapper>,
        newsDbTestRepository: DbTestRepositoryImpl<String, NewsReport, NewsReportDtoWrapper>
    ): TestSpeedUseCase =
        TestSpeedUseCase(weatherDbTestRepository, newsDbTestRepository)
}
