package dev.konnov.feature.datastore.presentation

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dbtestingtools.DbInfo
import dev.konnov.common.dbtestingtools.domain.entity.SIZE_10k
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.common.mvvm.TestDbViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    @Named("Datastore_usecase")
    private val testSpeedUseCase: TestSpeedUseCase
) : TestDbViewModel(testSpeedUseCase) {

    override val dbInfo = DbInfo("DataStore Proto")

    override val testIterations = 1

    override val testSizes: List<Int>
        get() = listOf(SIZE_10k)
}