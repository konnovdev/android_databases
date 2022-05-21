package dev.konnov.feature.sqldelight.data.datasource

import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.sqldelight.NewsReport
import dev.konnov.feature.sqldelight.NewsReportQueries
import javax.inject.Inject

class NewsDbDataSource @Inject constructor(
    private val newsReportQueries: NewsReportQueries
) : DbDataSource<String, NewsReport> {

    override suspend fun insert(items: List<NewsReport>) {
        items.forEach {
            newsReportQueries.insert(
                it.title,
                it.description,
            )
        }
    }

    override suspend fun loadAll() {
        newsReportQueries.selectAll()
    }

    override suspend fun loadByParameter(param: String) {
        newsReportQueries.selectByTitle(param)
    }

    override suspend fun update(param: String, objectToInsert: NewsReport) {
        newsReportQueries.updateByTitle(
            objectToInsert.title,
            objectToInsert.description
        )
    }

    override suspend fun delete(param: String) {
        newsReportQueries.deleteByTitle(param)
    }

    override suspend fun deleteAll() {
        newsReportQueries.selectAll()
    }
}