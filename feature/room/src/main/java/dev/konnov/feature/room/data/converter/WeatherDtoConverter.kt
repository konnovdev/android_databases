package dev.konnov.feature.room.data.converter

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.feature.room.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherDtoConverter @Inject constructor() {

    fun convert(entity: WeatherLog): WeatherLogDto =
        WeatherLogDto(
            temperature = entity.temperature,
            humidity = entity.humidity,
            pressure = entity.pressure,
        )

    fun convert(dto: WeatherLogDto): WeatherLog =
        WeatherLog(
            temperature = dto.temperature,
            humidity = dto.humidity,
            pressure = dto.pressure
        )
}