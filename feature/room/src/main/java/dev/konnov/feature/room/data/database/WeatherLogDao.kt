package dev.konnov.feature.room.data.database

import androidx.room.*
import dev.konnov.feature.room.data.model.WeatherLogDto

@Dao
interface WeatherLogDao {

    @Query("SELECT * FROM weatherLog")
    suspend fun getAll(): WeatherLogDto

    @Query("SELECT * FROM weatherLog WHERE temperature = :temperature")
    suspend fun getWeatherByTemperature(temperature: Double): List<WeatherLogDto>

    @Query("UPDATE weatherLog " +
            "SET temperature = :newTemperature," +
            "humidity = :newHumidity," +
            "pressure = :newPressure " +
            "WHERE temperature = :temperature")
    suspend fun updateByTemperature(temperature: Double, newTemperature: Double, newHumidity: Double,  newPressure: Double)

    @Insert
    suspend fun addWeather(weatherLogs: List<WeatherLogDto>)

    @Query("DELETE FROM weatherLog")
    suspend fun deleteAllWeatherData()
}