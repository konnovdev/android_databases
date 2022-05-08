package dev.konnov.common.dataset.weatherlogs

data class WeatherLog(val temperature: Temperature, val humidity: Double, val pressure: Double)

@JvmInline
value class Temperature(val temperature: Double)