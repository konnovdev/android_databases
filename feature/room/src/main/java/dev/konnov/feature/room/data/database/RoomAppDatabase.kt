package dev.konnov.feature.room.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.konnov.feature.room.data.model.WeatherLogDto

@Database(entities = [WeatherLogDto::class], version = 1)
abstract class RoomAppDatabase : RoomDatabase() {

    abstract fun weatherLogDao(): WeatherLogDao
}