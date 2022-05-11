package dev.konnov.feature.room.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newsReport")
class NewsReportDto(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
)
