package dev.konnov.feature.datastore.data.converter

import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.datastore.WeatherLogDto
import javax.inject.Inject

class WeatherConverter @Inject constructor() : DtoConverter<WeatherLog, WeatherLogDto> {

    override fun convert(entity: WeatherLog): WeatherLogDto =
        WeatherLogDto.newBuilder()
            .setTemperature(entity.temperature)
            .setHumidity(entity.humidity)
            .setPressure(entity.pressure)
            .build()
}