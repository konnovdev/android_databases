package dev.konnov.feature.realm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.feature.realm.data.repository.NewsDbTestRepository
import dev.konnov.feature.realm.data.repository.WeatherDbTestRepository
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class RealmViewModelModule {

    @Provides
    @Named("Realm_usecase")
    fun provideTestSpeedUseCase(
        weatherDbTestRepository: WeatherDbTestRepository,
        newsDbTestRepository: NewsDbTestRepository
    ): TestSpeedUseCase =
        TestSpeedUseCase(listOf(weatherDbTestRepository, newsDbTestRepository))
}
