package dev.konnov.feature.sqldelight.data.datasource

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.sqldelight.WeatherLogQueries
import javax.inject.Inject

class WeatherDbDataSource @Inject constructor(
    private val weatherLogQueries: WeatherLogQueries
) : DbDataSource<Double, WeatherLog> {

    override suspend fun insert(items: List<WeatherLog>) {
        items.forEach {
            weatherLogQueries.insert(
                it.temperature,
                it.humidity,
                it.pressure
            )
        }
    }

    override suspend fun loadAll() {
        weatherLogQueries.selectAll()
    }

    override suspend fun loadByParameter(param: Double) {
        weatherLogQueries.selectByTemperature(param)
    }

    override suspend fun update(param: Double, objectToInsert: WeatherLog) {
        weatherLogQueries.updateByTemperature(
            objectToInsert.temperature,
            objectToInsert.humidity,
            objectToInsert.pressure,
            param
        )
    }

    override suspend fun delete(param: Double) {
        weatherLogQueries.deleteByTemperature(param)
    }

    override suspend fun deleteAll() {
        weatherLogQueries.deleteAll()
    }
}