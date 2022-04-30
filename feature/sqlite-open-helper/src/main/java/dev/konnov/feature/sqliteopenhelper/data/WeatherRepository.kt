package dev.konnov.feature.sqliteopenhelper.data

import dev.konnov.common.dbtestingtools.DataSetType
import dev.konnov.common.dataset.weatherlogs.WeatherLog
import dev.konnov.common.dbtestingtools.DbTestRepository
import dev.konnov.common.dbtestingtools.TestResult
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val sqliteOpenManager: SqliteOpenHelperDbManager
): DbTestRepository<WeatherLog, Double> {

    override fun insert(items: List<WeatherLog>): TestResult {
        sqliteOpenManager.deleteAllData()

        val startTimestamp = System.currentTimeMillis()
        sqliteOpenManager.add(items)
        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.REAL, items.size)
    }

    override fun loadEverything(): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val retrievedData = sqliteOpenManager.getAll()

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.REAL, retrievedData.size)
    }

    override fun update(temperature: Double, item: WeatherLog): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val affectedRows = sqliteOpenManager.updateByTemperature(temperature, item)

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.REAL, affectedRows)
    }

    override fun loadByParameter(temperature: Double): TestResult {
        val startTimestamp = System.currentTimeMillis()

        val result = sqliteOpenManager.getByTemperature(temperature)

        val endTimestamp = System.currentTimeMillis()
        val timeTaken = endTimestamp - startTimestamp

        return TestResult(timeTaken, DataSetType.REAL, result.size)
    }
}