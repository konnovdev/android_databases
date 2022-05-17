package dev.konnov.feature.realm.data.repository

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepositoryImpl
import dev.konnov.feature.realm.data.converter.NewsReportDtoConverter
import dev.konnov.feature.realm.data.datasource.NewsDbDataSource
import dev.konnov.feature.realm.data.model.NewsReportDtoWrapper
import javax.inject.Inject

class NewsDbTestRepository @Inject constructor(
    dataSetDataSource: DataSetDataSource<String, NewsReport>,
    dbDataSource: NewsDbDataSource,
    testResultConverter: TestResultConverter,
    dtoConverter: NewsReportDtoConverter
) : DbTestRepositoryImpl<String, NewsReport, NewsReportDtoWrapper>(
    dataSetDataSource,
    dbDataSource,
    testResultConverter,
    dtoConverter
)