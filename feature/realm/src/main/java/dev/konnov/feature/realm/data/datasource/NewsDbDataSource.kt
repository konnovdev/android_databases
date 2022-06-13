package dev.konnov.feature.realm.data.datasource

import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.realm.data.model.NewsReportDto
import io.realm.Realm
import io.realm.query
import javax.inject.Inject

class NewsDbDataSource @Inject constructor(
    private val realm: Realm
) : DbDataSource<String, NewsReportDto> {

    override suspend fun insert(items: List<NewsReportDto>) {
        items
            .map {
                NewsReportDto().apply {
                    this.title = it.title
                    this.description = it.description
                }
            }.also { dtos ->
                realm.write {
                    dtos.forEach(::copyToRealm)
                }
            }
    }

    override suspend fun loadAll() {
        realm.query<NewsReportDto>().find()
    }

    override suspend fun loadByParameter(param: String) {
        realm.query<NewsReportDto>("title == $0", param).find()
    }

    override suspend fun update(param: String, objectToInsert: NewsReportDto) {
        realm.write {
            realm.query<NewsReportDto>("title == $0", param)
                .find()
                .forEach { oldNewsReportDto ->
                    findLatest(oldNewsReportDto)?.apply {
                        title = objectToInsert.title
                        description = objectToInsert.description
                    }
                }
        }
    }

    override suspend fun delete(param: String) {
        realm.write {
            val query = this.query<NewsReportDto>("title == $0", param)
            delete(query)
        }
    }

    override suspend fun deleteAll() {
        realm.write {
            val query = this.query<NewsReportDto>()
            delete(query)
        }
    }
}
