package dev.konnov.feature.room.data.datasource

import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.feature.room.data.database.WeatherLogDao
import dev.konnov.feature.room.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherDbDataSource @Inject constructor(
    private val weatherLogDao: WeatherLogDao
) : DbDataSource<Double, WeatherLogDto> {

    override suspend fun insert(items: List<WeatherLogDto>) {
        weatherLogDao.addWeather(items)
    }

    override suspend fun loadAll() {
        weatherLogDao.getAll()
    }

    override suspend fun loadByParameter(param: Double) {
        weatherLogDao.getWeatherByTemperature(param)
    }

    override suspend fun update(param: Double, objectToInsert: WeatherLogDto) {
        weatherLogDao.updateByTemperature(
            param,
            objectToInsert.temperature,
            objectToInsert.humidity,
            objectToInsert.pressure
        )
    }

    override suspend fun delete(param: Double) {
        weatherLogDao.deleteByTemperature(param)
    }

    override suspend fun deleteAll() {
        weatherLogDao.deleteAllWeatherData()
    }
}
