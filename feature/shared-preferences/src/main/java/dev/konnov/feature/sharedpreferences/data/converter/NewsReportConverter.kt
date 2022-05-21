package dev.konnov.feature.sharedpreferences.data.converter

import com.google.gson.Gson
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.DtoConverter
import dev.konnov.feature.sharedpreferences.data.model.NewsReportDto
import javax.inject.Inject

class NewsReportConverter @Inject constructor() : DtoConverter<NewsReport, NewsReportDto> {

    override fun convert(entity: NewsReport): NewsReportDto =
        NewsReportDto(
            Gson().toJson(entity)
        )
}