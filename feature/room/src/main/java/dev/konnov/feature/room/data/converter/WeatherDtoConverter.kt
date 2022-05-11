package dev.konnov.feature.room.data.converter

import dev.konnov.common.dataset.weatherlogs.Temperature
import dev.konnov.common.dataset.weatherlogs.WeatherLog
import dev.konnov.feature.room.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherDtoConverter @Inject constructor() {

    fun convert(entity: WeatherLog): WeatherLogDto =
        WeatherLogDto(
            temperature = entity.temperature.temperature,
            humidity = entity.humidity,
            pressure = entity.pressure,
        )

    fun convert(dto: WeatherLogDto): WeatherLog =
        WeatherLog(
            temperature = Temperature(dto.temperature),
            humidity = dto.humidity,
            pressure = dto.pressure
        )
}