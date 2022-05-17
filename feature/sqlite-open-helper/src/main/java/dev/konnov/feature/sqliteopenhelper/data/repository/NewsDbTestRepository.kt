package dev.konnov.feature.sqliteopenhelper.data.repository

import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.converter.MockDtoConverter
import dev.konnov.common.dbtestingtools.data.converter.TestResultConverter
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource
import dev.konnov.common.dbtestingtools.data.datasource.DbDataSource
import dev.konnov.common.dbtestingtools.domain.repository.DbTestRepositoryImpl
import javax.inject.Inject

class NewsDbTestRepository @Inject constructor(
    dataSetDataSource: DataSetDataSource<String, NewsReport>,
    dbDataSource: DbDataSource<String, NewsReport>,
    testResultConverter: TestResultConverter,
    dtoConverter: MockDtoConverter<NewsReport>
) : DbTestRepositoryImpl<String, NewsReport, NewsReport>(
    dataSetDataSource,
    dbDataSource,
    testResultConverter,
    dtoConverter
)