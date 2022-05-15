package dev.konnov.common.dataset.weatherlogs.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.konnov.common.dataset.weatherlogs.data.datasource.WeatherLogDataSetDataSource
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource

@Module
@InstallIn(SingletonComponent::class)
class WeatherLogDataModule {

    @Provides
    fun providWeatherDataSetDataSource(): DataSetDataSource<Double, WeatherLog> =
        WeatherLogDataSetDataSource()
}
