package dev.konnov.common.dbtestingtools.data.repository

import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepository

open class DbTestRepositoryImpl<Param, Entity, DTO>(
    private val dataSetDataSource: DataSetDataSource<Param, Entity>,
    private val dbDataSource: DbDataSource<Param, DTO>,
    private val testResultConverter: TestResultConverter,
    private val dtoConverter: DtoConverter<Entity, DTO>
) : DbTestRepository {

    private val data = mutableListOf<Entity>()

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