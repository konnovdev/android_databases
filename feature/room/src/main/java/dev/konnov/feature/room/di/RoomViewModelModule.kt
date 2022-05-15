package dev.konnov.feature.room.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.feature.room.data.repository.NewsDbTestRepository
import dev.konnov.feature.room.data.repository.WeatherDbTestRepository
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class RoomViewModelModule {

    @Provides
    @Named("Room_usecase")
    fun provideTestSpeedUseCase(
        weatherDbTestRepository: WeatherDbTestRepository,
        newsDbTestRepository: NewsDbTestRepository
    ): TestSpeedUseCase =
        TestSpeedUseCase(listOf(
            weatherDbTestRepository,
            newsDbTestRepository
        ))
}
