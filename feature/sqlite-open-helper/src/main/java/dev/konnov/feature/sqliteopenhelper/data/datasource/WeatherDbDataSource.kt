package dev.konnov.feature.sqliteopenhelper.data.datasource

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.sqliteopenhelper.data.database.SqliteOpenHelperDbManager
import javax.inject.Inject

class WeatherDbDataSource @Inject constructor(
    private val dbManager: SqliteOpenHelperDbManager
) : DbDataSource<Double, WeatherLog> {

    override suspend fun insert(items: List<WeatherLog>) {
       dbManager.addWeather(items)
    }

    override suspend fun loadAll() {
        dbManager.getAllWeatherData()
    }

    override suspend fun loadByParameter(param: Double) {
        dbManager.getWeatherByTemperature(param)
    }

    override suspend fun update(param: Double, objectToInsert: WeatherLog) {
        dbManager.updateByTemperature(param, objectToInsert)
    }

    override suspend fun delete(param: Double) {
        dbManager.deleteWeatherByTemperature(param)
    }

    override suspend fun deleteAll() {
        dbManager.deleteAllNewsReportsData()
    }
}
