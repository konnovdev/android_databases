import dev.konnov.common.dataset.weatherlogs.data.datasource.WeatherLogDataSetDataSource
import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class WeatherLogDataSetDataSourceTest {

    private val weatherLogDataSetDataSource = WeatherLogDataSetDataSource()

    @Test
    fun itemSizesAreCorrect() {
        assertEquals(10_000, weatherLogDataSetDataSource.get(10_000).size)
        assertEquals(100_000, weatherLogDataSetDataSource.get(100_000).size)
        assertEquals(1_000_000, weatherLogDataSetDataSource.get(1_000_000).size)
    }

    @Test
    fun temperatureHasNegativeNumbers() {
        val negativeTemperaturesCount =
            weatherLogDataSetDataSource.get(10_000).count { it.temperature < 0 }

        println("Negative temperatures count: $negativeTemperaturesCount")

        assert(negativeTemperaturesCount > 1)
    }

    @Test
    fun highUniqueItemsFrequency() {
        val entities = weatherLogDataSetDataSource.get(1_000_000)
        val temperatures = entities.map { it.temperature }
        val humidities = entities.map { it.humidity }
        val pressures = entities.map { it.pressure }

        val uniqueTemperatures = mutableSetOf<Double>()
        uniqueTemperatures.addAll(temperatures)

        val uniqueHumidities = mutableSetOf<Double>()
        uniqueHumidities.addAll(humidities)

        val uniquePressures = mutableSetOf<Double>()
        uniquePressures.addAll(pressures)

        val temperatureUniqueness =
            uniqueTemperatures.size.toDouble() / temperatures.size.toDouble()
        val humidityUniqueness = uniqueHumidities.size.toDouble() / humidities.size.toDouble()
        val pressureUniqueness = uniquePressures.size.toDouble() / pressures.size.toDouble()

        println("temperatureUniqueness: ${temperatureUniqueness.toBigDecimal().toPlainString()}")
        println("humidityUniqueness: ${humidityUniqueness.toBigDecimal().toPlainString()}")
        println("pressureUniqueness: ${pressureUniqueness.toBigDecimal().toPlainString()}")

        assert(temperatureUniqueness > 0.99)
        assert(humidityUniqueness > 0.99)
        assert(pressureUniqueness > 0.99)
    }

    @Test
    fun twoGenerationsGiveTheSameItem() {
        val firstGeneration = weatherLogDataSetDataSource.get(1_000_000)
        val secondGeneration = weatherLogDataSetDataSource.get(1_000_000)

        assert(firstGeneration == secondGeneration)
    }

    @Test
    fun oldParameterToUpdateExistsInTheCollection() {
        val collection = weatherLogDataSetDataSource.get(10_000)
        val oldParamToUpdate = weatherLogDataSetDataSource.oldParameterToUpdate

        assert(collection.map { it.temperature }.contains(oldParamToUpdate))
    }

    @Test
    fun parameterToDeleteExistsInTheCollection() {
        val collection = weatherLogDataSetDataSource.get(10_000)
        val paramToDelete = weatherLogDataSetDataSource.parameterToDelete

        assert(collection.map { it.temperature }.contains(paramToDelete))
    }

    @Test
    fun parameterToLoadByExistsInTheCollection() {
        val collection = weatherLogDataSetDataSource.get(10_000)
        val paramToLoadBy = weatherLogDataSetDataSource.parameterToLoadBy

        assert(collection.map { it.temperature }.contains(paramToLoadBy))
    }
}