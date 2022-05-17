package dev.konnov.feature.realm.data.converter

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.realm.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherDtoConverter @Inject constructor() : DtoConverter<WeatherLog, WeatherLogDto> {

    override fun convertToDto(entity: WeatherLog): WeatherLogDto =
        WeatherLogDto().apply {
            temperature = entity.temperature
            humidity = entity.humidity
            pressure = entity.pressure
        }

    override fun convertToEntity(dto: WeatherLogDto): WeatherLog =
        WeatherLog(
            temperature = dto.temperature,
            humidity = dto.humidity,
            pressure = dto.pressure
        )
}