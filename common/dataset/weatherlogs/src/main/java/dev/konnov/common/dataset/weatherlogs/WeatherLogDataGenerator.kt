package dev.konnov.common.dataset.weatherlogs

/**
 * This is a fake data generator for floating point numbers
 *
 * The items that are generated here are 99% unique, but they are linked to their position in the list
 * So every time you generate a set of numbers of any size here you would get the exact same set of numbers
 *
 * This is done so that the dataset doesn't change on every test, giving a more consistent test result
 */
object WeatherLogDataGenerator {

    fun getEntities(size: Int): List<WeatherLog> {
        val entitiesList = mutableListOf<WeatherLog>()
        for (i in 1..size) {
            entitiesList.add(generateWeatherLog(i))
        }
        return entitiesList
    }

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