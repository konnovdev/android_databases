package dev.konnov.feature.objectbox.data.datasource

import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.objectbox.data.model.NewsReportDto
import dev.konnov.feature.objectbox.data.model.NewsReportDto_
import io.objectbox.Box
import io.objectbox.query.QueryBuilder
import javax.inject.Inject

class NewsDbDataSource @Inject constructor(
    private val box: Box<NewsReportDto>
) : DbDataSource<String, NewsReportDto> {

    private val caseInsensitiveStringOrder = QueryBuilder.StringOrder.CASE_INSENSITIVE

    override suspend fun insert(items: List<NewsReportDto>) {
        box.put(items)
    }

    override suspend fun loadAll() {
        box.all
    }

    override suspend fun loadByParameter(param: String) {
        box
            .query()
            .contains(NewsReportDto_.title, param, caseInsensitiveStringOrder)
            .build()
            .find()
    }

    override suspend fun update(param: String, objectToInsert: NewsReportDto) {
        box
            .query()
            .contains(NewsReportDto_.title, param, caseInsensitiveStringOrder)
            .build()
            .find()
            .also {
                box.put(it)
            }
    }

    override suspend fun delete(param: String) {
        box
            .query()
            .contains(NewsReportDto_.title, param, caseInsensitiveStringOrder)
            .build()
            .remove()
    }

    override suspend fun deleteAll() {
        box.removeAll()
    }
}