package dev.konnov.feature.objectbox.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class WeatherLogDto(
    @Id
    var id: Long = 0,
    val temperature: Double,
    val humidity: Double,
    val pressure: Double
)