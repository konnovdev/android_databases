package dev.konnov.feature.room.data

import dev.konnov.common.dataset.newsreports.NewsReport
import dev.konnov.common.dataset.newsreports.Title
import dev.konnov.feature.room.data.model.NewsReportDto
import javax.inject.Inject

class NewsReportDtoConverter @Inject constructor(){

    fun convert(entity: NewsReport): NewsReportDto =
        NewsReportDto(
            title = entity.title.title,
            description = entity.description
        )

    fun convert(dto: NewsReportDto): NewsReport =
        NewsReport(
            title = Title(dto.title),
            description = dto.description
        )
}