package dev.konnov.feature.sqliteopenhelper.data

import dev.konnov.common.dbtestingtools.DataSetType
import dev.konnov.common.dataset.weatherlogs.WeatherLog
import dev.konnov.common.dbtestingtools.DbTestRepository
import dev.konnov.common.dbtestingtools.TestResult
import dev.konnov.common.dbtestingtools.TestType
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val sqliteOpenManager: SqliteOpenHelperDbManager
): DbTestRepository<WeatherLog, Double> {

    override fun insert(items: List<WeatherLog>): TestResult {
        sqliteOpenManager.deleteAllWeatherData()

        val startTimestamp = System.currentTimeMillis()
        sqliteOpenManager.addWeather(items)
        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.REAL, items.size, TestType.INSERT)
    }

    override fun loadEverything(): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val retrievedData = sqliteOpenManager.getAllWeatherData()

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.REAL, retrievedData.size, TestType.LOAD_ALL)
    }

    override fun update(temperature: Double, item: WeatherLog): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val affectedRows = sqliteOpenManager.updateByTemperature(temperature, item)

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.REAL, affectedRows, TestType.UPDATE)
    }

    override fun loadByParameter(temperature: Double): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val result = sqliteOpenManager.getWeatherByTemperature(temperature)

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.REAL, result.size, TestType.LOAD_BY_PARAM)
    }

    override fun delete(temperature: Double): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val result = sqliteOpenManager.getWeatherByTemperature(temperature)

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.REAL, result.size, TestType.DELETE)
    }
}