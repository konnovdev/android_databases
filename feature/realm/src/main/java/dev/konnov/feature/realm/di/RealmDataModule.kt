package dev.konnov.feature.realm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.konnov.feature.realm.data.model.NewsReportDto
import dev.konnov.feature.realm.data.model.WeatherLogDto
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RealmDataModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm {
        val configuration = RealmConfiguration.with(
            schema = setOf(
                NewsReportDto::class,
                WeatherLogDto::class
            )
        )
        return Realm.open(configuration) // can also use RealmConfiguration.Builder for more options
    }
}