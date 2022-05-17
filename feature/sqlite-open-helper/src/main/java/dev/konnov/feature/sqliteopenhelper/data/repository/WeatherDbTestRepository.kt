package dev.konnov.feature.sqliteopenhelper.data.repository

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.MockDtoConverter
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepositoryImpl
import javax.inject.Inject

class WeatherDbTestRepository @Inject constructor(
    dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
    dbDataSource: DbDataSource<Double, WeatherLog>,
    testResultConverter: TestResultConverter,
    dtoConverter: MockDtoConverter<WeatherLog>
) : DbTestRepositoryImpl<Double, WeatherLog, WeatherLog>(
    dataSetDataSource,
    dbDataSource,
    testResultConverter,
    dtoConverter
)
