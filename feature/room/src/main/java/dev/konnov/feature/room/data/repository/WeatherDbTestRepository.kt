package dev.konnov.feature.room.data.repository

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepositoryImpl
import dev.konnov.feature.room.data.converter.WeatherDtoConverter
import dev.konnov.feature.room.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherDbTestRepository @Inject constructor(
    dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
    dbDataSource: DbDataSource<Double, WeatherLogDto>,
    testResultConverter: TestResultConverter,
    dtoConverter: WeatherDtoConverter
) : DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDto>(
    dataSetDataSource,
    dbDataSource,
    testResultConverter,
    dtoConverter
)