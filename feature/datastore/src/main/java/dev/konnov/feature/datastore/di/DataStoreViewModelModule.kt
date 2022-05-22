package dev.konnov.feature.datastore.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DataStoreViewModelModule {

    @Provides
    @Named("Datastore_usecase")
    fun provideTestSpeedUseCase(
//        newsRepository: DbTestRepositoryImpl<String, NewsReport, NewsReportDto>,
//        weatherRepository: DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDto>
    ): TestSpeedUseCase =
        TestSpeedUseCase(
//            newsRepository, weatherRepository
        )

}