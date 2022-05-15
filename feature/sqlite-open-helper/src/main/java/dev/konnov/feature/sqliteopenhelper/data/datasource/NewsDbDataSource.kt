package dev.konnov.feature.sqliteopenhelper.data.datasource

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.sqliteopenhelper.data.database.SqliteOpenHelperDbManager
import javax.inject.Inject

class NewsDbDataSource @Inject constructor(
    private val dbManager: SqliteOpenHelperDbManager
) : DbDataSource<String, NewsReport> {

    override suspend fun insert(items: List<NewsReport>) {
        dbManager.addNewsReports(items)
    }

    override suspend fun loadAll() {
        dbManager.getAllNewsData()
    }

    override suspend fun loadByParameter(param: String) {
        dbManager.getNewsByTitle(param)
    }

    override suspend fun update(param: String, objectToInsert: NewsReport) {
        dbManager.updateByTitle(param, objectToInsert)
    }

    override suspend fun delete(param: String) {
        dbManager.deleteNewsByTitle(param)
    }

    override suspend fun deleteAll() {
        dbManager.deleteAllNewsReportsData()
    }
}
