package dev.konnov.feature.datastore.data.datasource

import androidx.datastore.core.DataStore
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.datastore.NewsReportDto
import dev.konnov.feature.datastore.NewsReportList
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class NewsDbDataSource @Inject constructor(
    private val weatherLogDataStore: DataStore<NewsReportList>
) : DbDataSource<String, NewsReportDto> {

    override suspend fun insert(items: List<NewsReportDto>) {
        weatherLogDataStore.updateData { weatherList ->
            weatherList.toBuilder()
                .addAllNews(items)
                .build()
        }
    }

    override suspend fun loadAll() {
        weatherLogDataStore.data.first()
    }

    override suspend fun loadByParameter(param: String) {
        weatherLogDataStore.data.first().newsList.filter { it.title == param }
    }

    override suspend fun update(param: String, objectToInsert: NewsReportDto) {
        val updatedItems = mutableListOf<NewsReportDto>()
        weatherLogDataStore
            .data
            .first()
            .newsList
            .forEachIndexed { index, item ->
                if (item.title == param) {
                    updatedItems.add(index, objectToInsert)
                } else {
                    updatedItems.add(index, item)
                }
            }

            weatherLogDataStore.updateData { weatherList ->
                weatherList.toBuilder()
                    .addAllNews(updatedItems)
                    .build()
            }
        }

    override suspend fun delete(param: String) {
        val dataWithoutDeletedItem = weatherLogDataStore
            .data
            .first()
            .newsList
            .filter { it.title != param }


        weatherLogDataStore.updateData {  weatherList ->
            weatherList.toBuilder()
                .clearNews()
                .build()
        }

        weatherLogDataStore.updateData {  weatherList ->
            weatherList.toBuilder()
                .addAllNews(dataWithoutDeletedItem)
                .build()
        }
    }

    override suspend fun deleteAll() {
        weatherLogDataStore.updateData {  weatherList ->
            weatherList.toBuilder()
                .clearNews()
                .build()
        }
    }
}