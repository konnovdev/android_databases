package dev.konnov.feature.realm.data.repository

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepositoryImpl
import dev.konnov.feature.realm.data.converter.NewsReportDtoConverter
import dev.konnov.feature.realm.data.datasource.NewsDbDataSource
import dev.konnov.feature.realm.data.model.NewsReportDto
import javax.inject.Inject

class NewsDbTestRepository @Inject constructor(
    dataSetDataSource: DataSetDataSource<String, NewsReport>,
//    private val dbDataSource: DbDataSource<String, NewsReportDto>,
    dbDataSource: NewsDbDataSource,  // todo rewrite it like in the line above
    testResultConverter: TestResultConverter,
    dtoConverter: NewsReportDtoConverter
) : DbTestRepositoryImpl<String, NewsReport, NewsReportDto>(
    dataSetDataSource,
    dbDataSource,
    testResultConverter,
    dtoConverter
)