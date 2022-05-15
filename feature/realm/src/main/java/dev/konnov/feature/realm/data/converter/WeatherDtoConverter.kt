package dev.konnov.feature.realm.data.converter

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.feature.realm.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherDtoConverter @Inject constructor() {

    fun convert(entity: WeatherLog): WeatherLogDto =
        WeatherLogDto().apply {
            temperature = entity.temperature
            humidity = entity.humidity
            pressure = entity.pressure
        }


    fun convert(dto: WeatherLogDto): WeatherLog =
        WeatherLog(
            temperature = dto.temperature,
            humidity = dto.humidity,
            pressure = dto.pressure
        )
}