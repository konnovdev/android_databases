package dev.konnov.feature.sqliteopenhelper.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.konnov.feature.sqliteopenhelper.data.SqliteOpenHelperDbManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SqliteOpenHelperDataModule {

    @Singleton
    @Provides
    fun provideSqliteOpenHelperDbManager(
        @ApplicationContext appContext: Context
    ): SqliteOpenHelperDbManager = SqliteOpenHelperDbManager(
        appContext
    )
}
