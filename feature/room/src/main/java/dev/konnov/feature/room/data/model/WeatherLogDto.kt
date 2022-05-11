package dev.konnov.feature.room.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherLog")
data class WeatherLogDto(
    @ColumnInfo(name = "temperature") val temperature: Double,
    @ColumnInfo(name = "humidity") val humidity: Double,
    @ColumnInfo(name = "pressure") val pressure: Double,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
)