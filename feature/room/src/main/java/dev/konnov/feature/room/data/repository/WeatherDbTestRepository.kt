package dev.konnov.feature.room.data.repository

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepository
import dev.konnov.feature.room.data.converter.WeatherDtoConverter
import dev.konnov.feature.room.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherDbTestRepository @Inject constructor(
    private val dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
    private val dbDataSource: DbDataSource<Double, WeatherLogDto>,
    private val testResultConverter: TestResultConverter,
    private val dtoConverter: WeatherDtoConverter
) : DbTestRepository {

    private val data = mutableListOf<WeatherLog>()

    override suspend fun insert(entitiesSize: Int): TestResult {
        dbDataSource.deleteAll()
        data.clear()
        data.addAll(dataSetDataSource.get(entitiesSize))
        val dtos = data.map(dtoConverter::convert)

        return testResultConverter.convert(
            data,
            OperationType.INSERT
        ) { dbDataSource.insert(dtos) }
    }

    override suspend fun loadAll(): TestResult =
        testResultConverter.convert(
            data,
            OperationType.LOAD_ALL
        ) { dbDataSource.loadAll() }


    override suspend fun loadByParameter(): TestResult =
        testResultConverter.convert(
            data,
            OperationType.LOAD_BY_PARAM
        ) { dbDataSource.loadByParameter(dataSetDataSource.parameterToLoadBy) }


    override suspend fun update(): TestResult {
        val dtoToUpdate = dtoConverter.convert(dataSetDataSource.objectToInsertAsUpdate)

        return testResultConverter.convert(
            data,
            OperationType.UPDATE
        ) {
            dbDataSource.update(
                dataSetDataSource.oldParameterToUpdate,
                dtoToUpdate
            )
        }
    }

    override suspend fun delete(): TestResult =
        testResultConverter.convert(
            data,
            OperationType.DELETE
        ) {
            dbDataSource.delete(dataSetDataSource.parameterToDelete)
        }
}
