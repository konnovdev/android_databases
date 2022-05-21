package dev.konnov.feature.objectbox.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class NewsReportDto(
    @Id
    var id: Long = 0,
    val title: String,
    val description: String,
)