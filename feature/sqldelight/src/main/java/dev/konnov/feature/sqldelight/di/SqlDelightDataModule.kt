package dev.konnov.feature.sqldelight.di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.konnov.feature.sqldelight.Database
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SqlDelightDataModule {

    @Singleton
    @Provides
    fun provideSqlDelightDriver(
        @ApplicationContext appContext: Context
    ): AndroidSqliteDriver =
        AndroidSqliteDriver(Database.Schema, appContext, "test.db")

    @Singleton
    @Provides
    fun provideDatabase(
        driver: AndroidSqliteDriver
    ): Database = Database(driver)
}