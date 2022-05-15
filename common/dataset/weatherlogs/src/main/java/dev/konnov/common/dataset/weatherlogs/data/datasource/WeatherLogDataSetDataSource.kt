package dev.konnov.common.dataset.weatherlogs.data.datasource

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import javax.inject.Inject

/**
 * This is a fake data generator for floating point numbers
 *
 * The items that are generated here are 99% unique, but they are linked to their position in the list
 * So every time you generate a set of numbers of any size here you would get the exact same set of numbers
 *
 * This is done so that the dataset doesn't change on every test, giving a more consistent test result
 */
class WeatherLogDataSetDataSource @Inject constructor() : DataSetDataSource<Double, WeatherLog> {

    override fun get(size: Int): List<WeatherLog> {
        val dataList = mutableListOf<WeatherLog>()
        for (i in 1..size) {
            dataList.add(generateWeatherLog(i))
        }
        return dataList
    }

    /**
     * Temperature values to test CRUD operations on
     */
    override val oldParameterToUpdate: Double
        get() = 13.0

    override val objectToInsertAsUpdate: WeatherLog
        get() =  WeatherLog(14.0, 3123.1, 33.0)

    override val parameterToDelete: Double
        get() = 24.078125

    override val parameterToLoadBy: Double
        get() = 20.32

    private fun generateWeatherLog(index: Int): WeatherLog {
        val temperature = generateTemperature(index)
        val humidity = generateHumidity(index)
        val pressure = generatePressure(index)
        return WeatherLog(temperature, humidity, pressure)
    }

    private fun generateTemperature(index: Int): Double {
        var negativeTemperatureMultiplier = 1.0
        if (index % 43 == 0) {
            negativeTemperatureMultiplier = -1.0
        }
        return (20.0 + index % 22.0) / index % 13.0 * negativeTemperatureMultiplier + index % 40
    }

    private fun generateHumidity(index: Int) =
        100.0 - (index % 80.0) + index % 13.0 / (4241.0 % 99.0 + 1231 % 77.0) - (36.0 * 0.0001 * 11.0 % index * (index % 31.01))

    private fun generatePressure(index: Int) =
        1000.0 - index % 100.0 + (index % 100.0) / 100.0 + (index % 42.0 + 32.0 % index) / (index % 930.103)
}
