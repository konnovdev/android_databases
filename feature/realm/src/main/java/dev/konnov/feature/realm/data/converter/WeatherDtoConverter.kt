package dev.konnov.feature.realm.data.converter

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.realm.data.model.WeatherLogDto
import dev.konnov.feature.realm.data.model.WeatherLogDtoWrapper
import javax.inject.Inject

class WeatherDtoConverter @Inject constructor() : DtoConverter<WeatherLog, WeatherLogDtoWrapper> {

    override fun convert(entity: WeatherLog): WeatherLogDtoWrapper =
        WeatherLogDtoWrapper(
            WeatherLogDto().apply {
                temperature = entity.temperature
                humidity = entity.humidity
                pressure = entity.pressure
            }
        )
}