package dev.konnov.feature.sqliteopenhelper.data

import dev.konnov.common.dataset.newsreports.NewsReport
import dev.konnov.common.dbtestingtools.DataSetType
import dev.konnov.common.dbtestingtools.DbTestRepository
import dev.konnov.common.dbtestingtools.TestResult
import dev.konnov.common.dbtestingtools.TestType
import javax.inject.Inject

class NewsReportRepository @Inject constructor(
    private val sqliteOpenManager: SqliteOpenHelperDbManager
): DbTestRepository<NewsReport, String> {

    override fun insert(items: List<NewsReport>): TestResult {
        sqliteOpenManager.deleteAllNewsReportsData()

        val startTimestamp = System.currentTimeMillis()
        sqliteOpenManager.addNewsReports(items)
        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.STRING, items.size, TestType.INSERT)
    }

    override fun loadEverything(): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val retrievedData = sqliteOpenManager.getAllNewsData()

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.STRING, retrievedData.size, TestType.LOAD_ALL)
    }

    override fun loadByParameter(title: String): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val result = sqliteOpenManager.getNewsByTitle(title)

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.STRING, result.size, TestType.LOAD_BY_PARAM)
    }

    override fun update(title: String, item: NewsReport): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val affectedRows = sqliteOpenManager.updateByTitle(title, item)

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.STRING, affectedRows, TestType.UPDATE)
    }

    override fun delete(title: String): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val affectedRows = sqliteOpenManager.deleteNewsByTitle(title)

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.STRING, affectedRows, TestType.DELETE)
    }
}