package dev.konnov.feature.room.data.repository

import dev.konnov.common.dataset.newsreports.NewsReport
import dev.konnov.common.dataset.newsreports.Title
import dev.konnov.common.dbtestingtools.*
import dev.konnov.feature.room.data.converter.NewsReportDtoConverter
import dev.konnov.feature.room.data.database.NewsReportDao
import javax.inject.Inject

class NewsReportRepository  @Inject constructor(
    private val newsReportDao: NewsReportDao,
    private val testResultCalculator: TestResultCalculator,
    private val converter: NewsReportDtoConverter
) : DbTestRepository<NewsReport, Title> {

    // TODO temporary solution to save the amount of data the repo is being tested on
    // instead of using affected rows
    private var dataSize = DataSetSize(0)

    override suspend fun insert(items: List<NewsReport>): TestResult {
        newsReportDao.deleteAllNewsReportData()
        val itemsDto = items.map(converter::convert)

        return testResultCalculator.getResult(DataSetType.STRING, OperationType.INSERT) {
            newsReportDao.addNewsReports(itemsDto)
            dataSize = DataSetSize(items.size)
            dataSize
        }
    }

    override suspend fun loadEverything(): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.LOAD_ALL) {
            newsReportDao.getAll()
            dataSize
        }

    override suspend fun loadByParameter(param: Title): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.LOAD_BY_PARAM) {
            newsReportDao.getNewsReportByTitle(param.title)
            dataSize
        }

    override suspend fun update(param: Title, item: NewsReport): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.UPDATE) {
            newsReportDao.updateByTitle(
                title = param.title,
                newTitle = item.title.title,
                newDescription = item.description
            )
            dataSize
        }

    override suspend fun delete(param: Title): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.DELETE) {
            newsReportDao.deleteByTitle(param.title)
            dataSize
        }
}