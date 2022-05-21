package dev.konnov.feature.sharedpreferences.data.datasource

import android.content.SharedPreferences
import com.google.gson.Gson
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.sharedpreferences.data.model.NewsReportDto
import javax.inject.Inject

class NewsDbDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : DbDataSource<String, NewsReportDto> {

    override suspend fun insert(items: List<NewsReportDto>) {
        items.forEachIndexed { objectIndex, item ->
            sharedPreferences.edit().putString(objectIndex.toString(), item.dto).commit()
        }
    }

    override suspend fun loadAll() {
        sharedPreferences.all
    }

    override suspend fun loadByParameter(param: String) {
        sharedPreferences.all.values.find {
           val newsReport = Gson().fromJson(it.toString(), NewsReport::class.java)
            newsReport.title == param
        }
    }

    override suspend fun update(param: String, objectToInsert: NewsReportDto) {
        val matchingElements = mutableMapOf<String, NewsReportDto>()

        sharedPreferences.all.forEach {
            val newsReport = Gson().fromJson(it.value.toString(), NewsReport::class.java)

            if (newsReport.title == param) {
                matchingElements.put(it.key, NewsReportDto(objectToInsert.dto))
            }
        }

        matchingElements.forEach {
            sharedPreferences.edit().putString(it.key, it.value.dto).commit()
        }
    }

    override suspend fun delete(param: String) {
        val matchingElements = mutableListOf<String>()

        sharedPreferences.all.forEach {
            val newsReport = Gson().fromJson(it.value.toString(), NewsReport::class.java)

            if (newsReport.title == param) {
                matchingElements.add(it.key)
            }
        }

        matchingElements.forEach {
            sharedPreferences.edit().remove(it).commit()
        }
    }

    override suspend fun deleteAll() {
        sharedPreferences.edit().clear().commit()
    }
}