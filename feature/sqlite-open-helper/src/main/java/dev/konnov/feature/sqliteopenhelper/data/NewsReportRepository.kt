package dev.konnov.feature.sqliteopenhelper.data

import dev.konnov.common.dataset.newsreports.NewsReport
import dev.konnov.common.dataset.newsreports.Title
import dev.konnov.common.dbtestingtools.*
import javax.inject.Inject

class NewsReportRepository @Inject constructor(
    private val sqliteOpenManager: SqliteOpenHelperDbManager,
    private val testResultCalculator: TestResultCalculator
) : DbTestRepository<NewsReport, Title> {

    override fun insert(items: List<NewsReport>): TestResult {
        sqliteOpenManager.deleteAllNewsReportsData()

        return testResultCalculator.getResult(DataSetType.STRING, OperationType.INSERT) {
            sqliteOpenManager.addNewsReports(items)
            DataSetSize(items.size)
        }
    }

    override fun loadEverything(): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.LOAD_ALL) {
            val retrievedData = sqliteOpenManager.getAllNewsData()
            DataSetSize(retrievedData.size)
        }

    override fun loadByParameter(param: Title): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.LOAD_BY_PARAM) {
            val result = sqliteOpenManager.getNewsByTitle(param.title)
            DataSetSize(result.size)
        }

    override fun update(param: Title, item: NewsReport): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.UPDATE) {
            val affectedRows = sqliteOpenManager.updateByTitle(param.title, item)
            DataSetSize(affectedRows)
        }

    override fun delete(param: Title): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.DELETE) {
            val affectedRows = sqliteOpenManager.deleteNewsByTitle(param.title)
            DataSetSize(affectedRows)
        }
}