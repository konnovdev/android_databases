package dev.konnov.feature.sqliteopenhelper.data.repository

import dev.konnov.common.dataset.newsreports.NewsReport
import dev.konnov.common.dataset.newsreports.Title
import dev.konnov.common.dbtestingtools.*
import dev.konnov.feature.sqliteopenhelper.data.database.SqliteOpenHelperDbManager
import javax.inject.Inject

class NewsReportRepository @Inject constructor(
    private val sqliteOpenManager: SqliteOpenHelperDbManager,
    private val testResultCalculator: TestResultCalculator
) : DbTestRepository<NewsReport, Title> {

    // TODO temporary solution to save the amount of data the repo is being tested on
    // instead of using affected rows
    private var dataSize = DataSetSize(0)

    override suspend fun insert(items: List<NewsReport>): TestResult {
        sqliteOpenManager.deleteAllNewsReportsData()

        return testResultCalculator.getResult(DataSetType.STRING, OperationType.INSERT) {
            sqliteOpenManager.addNewsReports(items)
            dataSize = DataSetSize(items.size)
            dataSize
        }
    }

    override suspend fun loadEverything(): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.LOAD_ALL) {
            sqliteOpenManager.getAllNewsData()
            dataSize
        }

    override suspend fun loadByParameter(param: Title): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.LOAD_BY_PARAM) {
            sqliteOpenManager.getNewsByTitle(param.title)
            dataSize
        }

    override suspend fun update(param: Title, item: NewsReport): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.UPDATE) {
            sqliteOpenManager.updateByTitle(param.title, item)
            dataSize
        }

    override suspend fun delete(param: Title): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.DELETE) {
            sqliteOpenManager.deleteNewsByTitle(param.title)
            dataSize
        }
}