package dev.konnov.feature.room.data.converter

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.feature.room.data.model.NewsReportDto
import javax.inject.Inject

class NewsReportDtoConverter @Inject constructor(){

    fun convert(entity: NewsReport): NewsReportDto =
        NewsReportDto(
            title = entity.title,
            description = entity.description
        )

    fun convert(dto: NewsReportDto): NewsReport =
        NewsReport(
            title = dto.title,
            description = dto.description
        )
}