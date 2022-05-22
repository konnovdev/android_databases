package dev.konnov.feature.datastore.data.datasource

import androidx.datastore.core.DataStore
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.datastore.WeatherLogDto
import dev.konnov.feature.datastore.WeatherLogList
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class WeatherDbDataSource @Inject constructor(
    private val weatherLogDataStore: DataStore<WeatherLogList>
) : DbDataSource<Double, WeatherLogDto> {

    override suspend fun insert(items: List<WeatherLogDto>) {
        weatherLogDataStore.updateData { weatherList ->
            weatherList.toBuilder()
                .addAllWeather(items)
                .build()
        }
    }

    override suspend fun loadAll() {
        weatherLogDataStore.data.first()
    }

    override suspend fun loadByParameter(param: Double) {
        weatherLogDataStore.data.first().weatherList.filter { it.temperature == param }
    }

    override suspend fun update(param: Double, objectToInsert: WeatherLogDto) {
        val itemIndexesToUpdate = mutableListOf<Int>()
        weatherLogDataStore
            .data
            .first()
            .weatherList
            .forEachIndexed { index, item ->
                if (item.temperature == param) {
                    itemIndexesToUpdate.add(index)
                }
            }

        itemIndexesToUpdate.forEach {
            weatherLogDataStore.updateData { weatherList ->
                weatherList.toBuilder()
                    .addWeather(it, objectToInsert)
                    .build()
            }
        }
    }

    override suspend fun delete(param: Double) {
        val dataWithoutDeletedItem = weatherLogDataStore
            .data
            .first()
            .weatherList
            .filter { it.temperature != param }


       weatherLogDataStore.updateData {  weatherList ->
           weatherList.toBuilder()
               .clearWeather()
               .build()
       }

        weatherLogDataStore.updateData {  weatherList ->
            weatherList.toBuilder()
                .addAllWeather(dataWithoutDeletedItem)
                .build()
        }
    }

    override suspend fun deleteAll() {
        weatherLogDataStore.updateData {  weatherList ->
            weatherList.toBuilder()
                .clearWeather()
                .build()
        }
    }
}