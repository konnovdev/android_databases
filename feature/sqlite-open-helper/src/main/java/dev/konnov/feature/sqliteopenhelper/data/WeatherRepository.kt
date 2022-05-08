package dev.konnov.feature.sqliteopenhelper.data

import dev.konnov.common.dataset.weatherlogs.Temperature
import dev.konnov.common.dataset.weatherlogs.WeatherLog
import dev.konnov.common.dbtestingtools.*
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val sqliteOpenManager: SqliteOpenHelperDbManager,
    private val testResultCalculator: TestResultCalculator
) : DbTestRepository<WeatherLog, Temperature> {

    override fun insert(items: List<WeatherLog>): TestResult {
        sqliteOpenManager.deleteAllWeatherData()

        return testResultCalculator.getResult(DataSetType.REAL, OperationType.INSERT) {
            sqliteOpenManager.addWeather(items)
            DataSetSize(items.size)
        }
    }

    override fun loadEverything(): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.LOAD_ALL) {
            val retrievedData = sqliteOpenManager.getAllWeatherData()
            DataSetSize(retrievedData.size)
        }

    override fun update(param: Temperature, item: WeatherLog): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.UPDATE) {
            val affectedRows = sqliteOpenManager.updateByTemperature(param.temperature, item)
            DataSetSize(affectedRows)
        }

    override fun loadByParameter(param: Temperature): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.LOAD_BY_PARAM) {
            val result = sqliteOpenManager.getWeatherByTemperature(param.temperature)
            DataSetSize(result.size)
        }

    override fun delete(param: Temperature): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.DELETE) {
            val result = sqliteOpenManager.getWeatherByTemperature(param.temperature)
            DataSetSize(result.size)
        }
}