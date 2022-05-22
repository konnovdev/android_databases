package dev.konnov.feature.datastore.presentation

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.common.mvvm.TestDbViewModel
import dev.konnov.feature.datastore.data.serializer.settingsDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named


class DataStoreViewModel @Inject constructor(
    @Named("Datastore_usecase")
    private val testSpeedUseCase: TestSpeedUseCase
) : TestDbViewModel(testSpeedUseCase) {

    @Inject
    @ApplicationContext
    lateinit var context: Context

    fun datastoreSample() {
        val exampleCounterFlow: Flow<Int> =
            context
                .settingsDataStore
                .data
                .map { settings ->
                // The exampleCounter property is generated from the proto schema.
                settings.exampleCounter
            }
    }

    suspend fun incrementCounter() {
        context.settingsDataStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setExampleCounter(currentSettings.exampleCounter + 1)
                .build()
        }
    }

}