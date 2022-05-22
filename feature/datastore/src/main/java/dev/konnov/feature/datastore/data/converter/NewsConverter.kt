package dev.konnov.feature.datastore.data.converter

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.datastore.NewsReportDto
import javax.inject.Inject

class NewsConverter @Inject constructor() : DtoConverter<NewsReport, NewsReportDto> {

    override fun convert(entity: NewsReport): NewsReportDto =
        NewsReportDto.newBuilder()
            .setTitle(entity.title)
            .setDescription(entity.description)
            .build()
}