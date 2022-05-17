package dev.konnov.feature.realm.data.converter

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.realm.data.model.NewsReportDto
import dev.konnov.feature.realm.data.model.NewsReportDtoWrapper
import javax.inject.Inject

class NewsReportDtoConverter @Inject constructor() :
    DtoConverter<NewsReport, NewsReportDtoWrapper> {

    override fun convert(entity: NewsReport): NewsReportDtoWrapper =
        NewsReportDtoWrapper(
            NewsReportDto().apply {
                title = entity.title
                description = entity.description
            }
        )
}