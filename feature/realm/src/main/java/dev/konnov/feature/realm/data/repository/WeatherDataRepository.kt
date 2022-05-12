package dev.konnov.feature.realm.data.repository

import dev.konnov.common.dataset.weatherlogs.Temperature
import dev.konnov.common.dataset.weatherlogs.WeatherLog
import dev.konnov.common.dbtestingtools.*
import dev.konnov.feature.realm.data.model.WeatherLogDto
import io.realm.Realm
import io.realm.query
import javax.inject.Inject

class WeatherDataRepository @Inject constructor(
    private val realm: Realm,
    private val testResultCalculator: TestResultCalculator
) : DbTestRepository<WeatherLog, Temperature> {

    // TODO temporary solution to save the amount of data the repo is being tested on
    // instead of using affected rows
    private var dataSize = DataSetSize(0)

    override suspend fun insert(items: List<WeatherLog>): TestResult {
        realm.write {
            val query = this.query<WeatherLogDto>()
            delete(query)
        }

        return testResultCalculator.getResult(DataSetType.REAL, OperationType.INSERT) {
            items.map {
                WeatherLogDto().apply {
                    this.temperature = it.temperature.temperature
                    this.humidity = it.humidity
                    this.pressure = it.pressure
                }
            }.also { dtos ->
                realm.write {
                    dtos.forEach(::copyToRealm)
                }
            }
            dataSize = DataSetSize(items.size)
            dataSize
        }
    }

    override suspend fun loadEverything(): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.LOAD_ALL) {
            realm.query<WeatherLogDto>().find()
            dataSize
        }

    override suspend fun loadByParameter(param: Temperature): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.LOAD_BY_PARAM) {
            realm.query<WeatherLogDto>("temperature == $0", param.temperature)
            dataSize
        }

    override suspend fun update(param: Temperature, item: WeatherLog): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.UPDATE) {
            realm.query<WeatherLogDto>("temperature == $0", param.temperature)
                .find()
                .forEach { oldWeatherReportDto ->
                    realm.write {
                        findLatest(oldWeatherReportDto)?.apply {
                            temperature = item.temperature.temperature
                            humidity = item.humidity
                            pressure = item.pressure
                        }
                    }
                }
            dataSize
        }


    override suspend fun delete(param: Temperature): TestResult =
        testResultCalculator.getResult(DataSetType.REAL, OperationType.DELETE) {
            realm.write {
                val query = this.query<WeatherLogDto>("temperature == $0", param.temperature)
                delete(query)
            }
            dataSize
        }
}