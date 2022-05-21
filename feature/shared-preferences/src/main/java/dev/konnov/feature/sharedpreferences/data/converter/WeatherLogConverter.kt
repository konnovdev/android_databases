package dev.konnov.feature.sharedpreferences.data.converter

import com.google.gson.Gson
import dev.konnov.common.dataset.weatherlogs.data.model.WeatherLog
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.sharedpreferences.data.model.WeatherLogDto
import javax.inject.Inject

class WeatherLogConverter @Inject constructor() : DtoConverter<WeatherLog, WeatherLogDto> {

    override fun convert(entity: WeatherLog): WeatherLogDto =
        WeatherLogDto(Gson().toJson(entity))
}