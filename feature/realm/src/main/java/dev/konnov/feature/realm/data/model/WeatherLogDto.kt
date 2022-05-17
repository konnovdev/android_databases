package dev.konnov.feature.realm.data.model

import io.realm.RealmObject

data class WeatherLogDtoWrapper(val dto: WeatherLogDto)

class WeatherLogDto : RealmObject {
    var temperature: Double = 0.0
    var humidity: Double = 0.0
    var pressure: Double = 0.0
}