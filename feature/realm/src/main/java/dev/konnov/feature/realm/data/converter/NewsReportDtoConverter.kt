package dev.konnov.feature.realm.data.converter

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.realm.data.model.NewsReportDto
import javax.inject.Inject

class NewsReportDtoConverter @Inject constructor() :
    DtoConverter<NewsReport, NewsReportDto> {

    override fun convert(entity: NewsReport): NewsReportDto =
        NewsReportDto().apply {
            title = entity.title
            description = entity.description
        }
}