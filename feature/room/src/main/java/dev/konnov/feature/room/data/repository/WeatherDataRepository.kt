package dev.konnov.feature.room.data.repository

import dev.konnov.common.dataset.weatherlogs.Temperature
import dev.konnov.common.dataset.weatherlogs.WeatherLog
import dev.konnov.common.dbtestingtools.*
import dev.konnov.feature.room.data.converter.WeatherDtoConverter
import dev.konnov.feature.room.data.database.WeatherLogDao
import javax.inject.Inject

class WeatherDataRepository @Inject constructor(
    private val weatherLogDao: WeatherLogDao,
    private val testResultCalculator: TestResultCalculator,
    private val weatherDtoConverter: WeatherDtoConverter
) : DbTestRepository<WeatherLog, Temperature> {

    // TODO temporary solution to save the amount of data the repo is being tested on
    // instead of using affected rows
    private var dataSize = DataSetSize(0)

    override suspend fun insert(items: List<WeatherLog>): TestResult {
        weatherLogDao.deleteAllWeatherData()
        val itemDtos = items.map(weatherDtoConverter::convert)

        return testResultCalculator.getResult(DataSetType.REAL, OperationType.INSERT) {
            weatherLogDao.addWeather(itemDtos)
            dataSize = DataSetSize(items.size)
            dataSize
        }
    }

    override suspend fun loadEverything(): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.LOAD_ALL) {
            weatherLogDao.getAll()
            dataSize
        }

    override suspend fun loadByParameter(param: Temperature): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.LOAD_ALL) {
            weatherLogDao.getAll()
            dataSize
        }

    override suspend fun update(param: Temperature, item: WeatherLog): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.UPDATE) {
            weatherLogDao.updateByTemperature(
                param.temperature,
                item.temperature.temperature,
                item.humidity,
                item.pressure
            )
            dataSize
        }


    override suspend fun delete(param: Temperature): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.UPDATE) {
            weatherLogDao.deleteByTemperature(param.temperature)
            dataSize
        }
}