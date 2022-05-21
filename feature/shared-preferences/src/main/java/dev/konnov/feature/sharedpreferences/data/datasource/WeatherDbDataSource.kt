package dev.konnov.feature.sharedpreferences.data.datasource

import android.content.SharedPreferences
import com.google.gson.Gson
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.sharedpreferences.data.model.NewsReportDto
import dev.konnov.feature.sharedpreferences.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherDbDataSource @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : DbDataSource<Double, WeatherLogDto> {

    override suspend fun insert(items: List<WeatherLogDto>) {
        items.forEachIndexed { objectIndex, item ->
            sharedPreferences.edit().putString(objectIndex.toString(), item.dto).commit()
        }
    }

    override suspend fun loadAll() {
        sharedPreferences.all
    }

    override suspend fun loadByParameter(param: Double) {
        sharedPreferences.all.values.find {
            val weatherLog = Gson().fromJson(it.toString(), WeatherLog::class.java)
            weatherLog.temperature == param
        }
    }

    override suspend fun update(param: Double, objectToInsert: WeatherLogDto) {
        val matchingElements = mutableMapOf<String, NewsReportDto>()

        sharedPreferences.all.forEach {
            val weatherLog = Gson().fromJson(it.value.toString(), WeatherLog::class.java)

            if (weatherLog.temperature == param) {
                matchingElements.put(it.key, NewsReportDto(objectToInsert.dto))
            }
        }

        matchingElements.forEach {
            sharedPreferences.edit().putString(it.key, it.value.dto).commit()
        }
    }

    override suspend fun delete(param: Double) {
        val matchingElements = mutableListOf<String>()

        sharedPreferences.all.forEach {
            val weatherLog = Gson().fromJson(it.value.toString(), WeatherLog::class.java)

            if (weatherLog.temperature == param) {
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