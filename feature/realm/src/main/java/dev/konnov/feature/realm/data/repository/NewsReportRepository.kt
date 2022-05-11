package dev.konnov.feature.realm.data.repository

import dev.konnov.common.dataset.newsreports.NewsReport
import dev.konnov.common.dataset.newsreports.Title
import dev.konnov.common.dbtestingtools.*
import dev.konnov.feature.realm.data.converter.NewsReportDtoConverter
import dev.konnov.feature.realm.data.model.NewsReportDto
import io.realm.Realm
import io.realm.query
import javax.inject.Inject

class NewsReportRepository @Inject constructor(
    private val realm: Realm,
    private val testResultCalculator: TestResultCalculator
) : DbTestRepository<NewsReport, Title> {

    // TODO temporary solution to save the amount of data the repo is being tested on
    // instead of using affected rows
    private var dataSize = DataSetSize(0)

    override suspend fun insert(items: List<NewsReport>): TestResult {
        realm.write {
            val query = this.query<NewsReportDto>()
            delete(query)
        }

        return testResultCalculator.getResult(DataSetType.STRING, OperationType.INSERT) {
            items.map {
                NewsReportDto().apply {
                    this.title = it.title.title
                    this.description = it.description
                }
            }.also { dtos ->
                realm.write {
                    dtos.forEach(::copyToRealm)
                }
            }
            dataSize = DataSetSize(items.size)
            dataSize
        }
    }

    override suspend fun loadEverything(): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.LOAD_ALL) {
            realm.query<NewsReportDto>().find()
            dataSize
        }

    override suspend fun loadByParameter(param: Title): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.LOAD_BY_PARAM) {
            realm.query<NewsReportDto>("title == $0", param.title)
            dataSize
        }

    override suspend fun update(param: Title, item: NewsReport): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.UPDATE) {
            realm.query<NewsReportDto>("title == $0", param.title)
                .find()
                .forEach { oldNewsReportDto ->
                    realm.write {
                        findLatest(oldNewsReportDto)?.apply {
                            title = item.title.title
                            description = item.description
                        }
                    }
                }
            dataSize
        }

    override suspend fun delete(param: Title): TestResult =
        testResultCalculator.getResult(DataSetType.STRING, OperationType.DELETE) {
            realm.write {
                val query = this.query<NewsReportDto>("title == $0", param.title)
                delete(query)
            }
            dataSize
        }
}