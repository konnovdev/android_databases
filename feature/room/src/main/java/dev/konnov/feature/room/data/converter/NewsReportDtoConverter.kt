package dev.konnov.feature.room.data.converter

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.room.data.model.NewsReportDto
import javax.inject.Inject

class NewsReportDtoConverter @Inject constructor() : DtoConverter<NewsReport, NewsReportDto> {

    override fun convertToDto(entity: NewsReport): NewsReportDto =
        NewsReportDto(
            title = entity.title,
            description = entity.description
        )

    override fun convertToEntity(dto: NewsReportDto): NewsReport =
        NewsReport(
            title = dto.title,
            description = dto.description
        )
}