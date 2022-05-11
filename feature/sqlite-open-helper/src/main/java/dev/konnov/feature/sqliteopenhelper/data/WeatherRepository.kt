package dev.konnov.feature.sqliteopenhelper.data

import dev.konnov.common.dataset.weatherlogs.Temperature
import dev.konnov.common.dataset.weatherlogs.WeatherLog
import dev.konnov.common.dbtestingtools.*
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val sqliteOpenManager: SqliteOpenHelperDbManager,
    private val testResultCalculator: TestResultCalculator
) : DbTestRepository<WeatherLog, Temperature> {

    // TODO temporary solution to save the amount of data the repo is being tested on
    // instead of using affected rows
    private var dataSize = DataSetSize(0)

    override suspend fun insert(items: List<WeatherLog>): TestResult {
        sqliteOpenManager.deleteAllWeatherData()

        return testResultCalculator.getResult(DataSetType.REAL, OperationType.INSERT) {
            sqliteOpenManager.addWeather(items)
            dataSize = DataSetSize(items.size)
            dataSize
        }
    }

    override suspend fun loadEverything(): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.LOAD_ALL) {
            sqliteOpenManager.getAllWeatherData()
            dataSize
        }

    override suspend fun update(param: Temperature, item: WeatherLog): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.UPDATE) {
            sqliteOpenManager.updateByTemperature(param.temperature, item)
            dataSize
        }

    override suspend fun loadByParameter(param: Temperature): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.LOAD_BY_PARAM) {
            sqliteOpenManager.getWeatherByTemperature(param.temperature)
            dataSize
        }

    override suspend fun delete(param: Temperature): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.DELETE) {
            sqliteOpenManager.deleteWeatherByTemperature(param.temperature)
            dataSize
        }
}