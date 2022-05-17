package dev.konnov.feature.realm.data.converter

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.realm.data.model.WeatherLogDto
import dev.konnov.feature.realm.data.model.WeatherLogDtoWrapper
import javax.inject.Inject

class WeatherDtoConverter @Inject constructor() : DtoConverter<WeatherLog, WeatherLogDtoWrapper> {

    override fun convertToDto(entity: WeatherLog): WeatherLogDtoWrapper =
        WeatherLogDtoWrapper(
            WeatherLogDto().apply {
                temperature = entity.temperature
                humidity = entity.humidity
                pressure = entity.pressure
            }
        )

    override fun convertToEntity(dto: WeatherLogDtoWrapper): WeatherLog =
        WeatherLog(
            temperature = dto.dto.temperature,
            humidity = dto.dto.humidity,
            pressure = dto.dto.pressure
        )
}