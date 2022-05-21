package dev.konnov.feature.objectbox.data.converter

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.objectbox.data.model.NewsReportDto
import javax.inject.Inject

class NewsReportDtoConverter @Inject constructor() :
    DtoConverter<NewsReport, NewsReportDto> {

    override fun convert(entity: NewsReport): NewsReportDto =
            NewsReportDto(
                title = entity.title,
                description = entity.description
            )
}