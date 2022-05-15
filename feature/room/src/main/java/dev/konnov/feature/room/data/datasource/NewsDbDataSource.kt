package dev.konnov.feature.room.data.datasource

import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.room.data.database.NewsReportDao
import dev.konnov.feature.room.data.model.NewsReportDto
import javax.inject.Inject

class NewsDbDataSource @Inject constructor(
    private val newsReportDao: NewsReportDao
) : DbDataSource<String, NewsReportDto> {

    override suspend fun insert(items: List<NewsReportDto>) {
        newsReportDao.addNewsReports(items)
    }

    override suspend fun loadAll() {
        newsReportDao.getAll()
    }

    override suspend fun loadByParameter(param: String) {
        newsReportDao.getNewsReportByTitle(param)
    }

    override suspend fun update(param: String, objectToInsert: NewsReportDto) {
        newsReportDao.updateByTitle(
            param,
            objectToInsert.title,
            objectToInsert.description
        )
    }

    override suspend fun delete(param: String) {
        newsReportDao.deleteByTitle(param)
    }

    override suspend fun deleteAll() {
         newsReportDao.deleteAllNewsReportData()
    }
}
