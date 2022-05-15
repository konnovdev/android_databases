package dev.konnov.feature.sqliteopenhelper.data.repository

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepository
import javax.inject.Inject

class WeatherDbTestRepository @Inject constructor(
    private val dataSetDataSource: DataSetDataSource<Double, WeatherLog>,
    private val dbDataSource: DbDataSource<Double, WeatherLog>,
    private val testResultConverter: TestResultConverter,
) : DbTestRepository {

    private val data = mutableListOf<WeatherLog>()

    override suspend fun insert(entitiesSize: Int): TestResult {
        dbDataSource.deleteAll()
        data.clear()
        data.addAll(dataSetDataSource.get(entitiesSize))

        return testResultConverter.convert(
            data,
            OperationType.INSERT
        ) { dbDataSource.insert(data) }
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


    override suspend fun update(): TestResult =
        testResultConverter.convert(
            data,
            OperationType.UPDATE
        ) {
            dbDataSource.update(
                dataSetDataSource.oldParameterToUpdate,
                dataSetDataSource.objectToInsertAsUpdate
            )
        }

    override suspend fun delete(): TestResult =
        testResultConverter.convert(
            data,
            OperationType.DELETE
        ) {
            dbDataSource.delete(dataSetDataSource.parameterToDelete)
        }
}
