package dev.konnov.feature.objectbox.data.converter

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.objectbox.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherDtoConverter @Inject constructor() : DtoConverter<WeatherLog, WeatherLogDto> {

    override fun convert(entity: WeatherLog): WeatherLogDto =
        WeatherLogDto(
            temperature = entity.temperature,
            humidity = entity.humidity,
            pressure = entity.pressure,
        )
}