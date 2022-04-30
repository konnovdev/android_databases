import dev.konnov.common.dataset.weatherlogs.WeatherLogDataGenerator
import junit.framework.TestCase.assertEquals
import org.junit.Test

internal class WeatherLogDataGeneratorTest {

    @Test
    fun itemSizesAreCorrect() {
        assertEquals(10_000, WeatherLogDataGenerator.get10kEntities().size)
        assertEquals(100_000, WeatherLogDataGenerator.get100kEntities().size)
        assertEquals(1_000_000, WeatherLogDataGenerator.get1MEntities().size)
    }

    @Test
    fun temperatureHasNegativeNumbers() {
        val negativeTemperaturesCount =
            WeatherLogDataGenerator.get10kEntities().count { it.temperature < 0 }

        println("Negative temperatures count: $negativeTemperaturesCount")

        assert(negativeTemperaturesCount > 1)
    }

    @Test
    fun highUniqueItemsFrequency() {
        val entities = WeatherLogDataGenerator.get1MEntities()
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
}