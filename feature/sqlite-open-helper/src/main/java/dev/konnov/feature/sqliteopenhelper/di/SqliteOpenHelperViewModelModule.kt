package dev.konnov.feature.sqliteopenhelper.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.feature.sqliteopenhelper.data.repository.NewsDbTestRepository
import dev.konnov.feature.sqliteopenhelper.data.repository.WeatherDbTestRepository
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class SqliteOpenHelperViewModelModule {

    @Provides
    @Named("Sqliteopenhelper_usecase")
    fun provideTestSpeedUseCase(
        weatherDbTestRepository: WeatherDbTestRepository,
        newsDbTestRepository: NewsDbTestRepository
    ): TestSpeedUseCase =
        TestSpeedUseCase(listOf(weatherDbTestRepository, newsDbTestRepository))
}
