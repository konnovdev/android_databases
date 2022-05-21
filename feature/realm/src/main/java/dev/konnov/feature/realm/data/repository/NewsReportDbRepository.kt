package dev.konnov.feature.realm.data.repository

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.domain.entity.OperationType
import dev.konnov.common.dbtestingtools.domain.entity.TestResult
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepository
import dev.konnov.feature.realm.data.converter.NewsReportDtoConverter
import dev.konnov.feature.realm.data.datasource.NewsDbDataSource
import javax.inject.Inject

// TODO find how to remove this repository
/**
 * See in WeatherLogsDbRepository the explanation why i had to manually create this repository
 */

class NewsReportDbRepository @Inject constructor(
    private val dataSetDataSource: DataSetDataSource<String, NewsReport>,
    private val dbDataSource: NewsDbDataSource,
    private val testResultConverter: TestResultConverter,
    private val dtoConverter: NewsReportDtoConverter
) : DbTestRepository {

    private val data = mutableListOf<NewsReport>()

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