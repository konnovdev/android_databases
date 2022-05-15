package dev.konnov.common.dataset.newsreports.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.konnov.common.dataset.newsreports.data.datasource.NewsReportDataSetDataSource
import dev.konnov.common.dataset.newsreports.data.model.NewsReport
import dev.konnov.common.dbtestingtools.data.datasource.DataSetDataSource

@Module
@InstallIn(SingletonComponent::class)
class NewsReportDataModule {

    @Provides
    fun provideNewsDataSetDataSource(): DataSetDataSource<String, NewsReport> =
        NewsReportDataSetDataSource()
}
