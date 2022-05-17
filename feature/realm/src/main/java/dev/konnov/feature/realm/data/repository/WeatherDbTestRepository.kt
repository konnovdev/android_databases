package dev.konnov.feature.realm.data.repository

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepositoryImpl
import dev.konnov.feature.realm.data.converter.WeatherDtoConverter
import dev.konnov.feature.realm.data.datasource.WeatherDbDataSource
import dev.konnov.feature.realm.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherDbTestRepository @Inject constructor(
    dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
//    private val dbDataSource: DbDataSource<Double, WeatherLogDto>,
    dbDataSource: WeatherDbDataSource, // todo rewrite it like in the line above
    testResultConverter: TestResultConverter,
    dtoConverter: WeatherDtoConverter
) : DbTestRepositoryImpl<Double, WeatherLog, WeatherLogDto>(
    dataSetDataSource,
    dbDataSource,
    testResultConverter,
    dtoConverter
)