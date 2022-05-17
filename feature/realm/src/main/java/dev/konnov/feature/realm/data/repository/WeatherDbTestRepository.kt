package dev.konnov.feature.realm.data.repository

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepositoryImpl
import dev.konnov.feature.realm.data.converter.WeatherDtoConverter
import dev.konnov.feature.realm.data.datasource.WeatherDbDataSource
import dev.konnov.feature.realm.data.model.WeatherLogDtoWrapper
import javax.inject.Inject

class WeatherDbTestRepository @Inject constructor(
    dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
    dbDataSource: WeatherDbDataSource,
    testResultConverter: TestResultConverter,
    dtoConverter: WeatherDtoConverter
) : DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDtoWrapper>(
    dataSetDataSource,
    dbDataSource,
    testResultConverter,
    dtoConverter
)