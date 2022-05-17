package dev.konnov.feature.room.data.repository

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepositoryImpl
import dev.konnov.feature.room.data.converter.NewsReportDtoConverter
import dev.konnov.feature.room.data.model.NewsReportDto
import javax.inject.Inject

class NewsDbTestRepository @Inject constructor(
    dataSetDataSource: DataSetDataSource<String, NewsReport>,
    dbDataSource: DbDataSource<String, NewsReportDto>,
    testResultConverter: TestResultConverter,
    dtoConverter: NewsReportDtoConverter
) : DbTestRepositoryImpl<String, NewsReport, NewsReportDto>(
    dataSetDataSource,
    dbDataSource,
    testResultConverter,
    dtoConverter
)