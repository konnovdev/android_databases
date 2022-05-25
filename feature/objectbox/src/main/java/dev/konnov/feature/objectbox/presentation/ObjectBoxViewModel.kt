package dev.konnov.feature.objectbox.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dbtestingtools.DbInfo
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.common.mvvm.TestDbViewModel
import dev.konnov.common.mvvm.TestDbViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class ObjectBoxViewModel @Inject constructor(
    @Named("Objectbox_usecase")
    private val testSpeedUseCase: TestSpeedUseCase
): TestDbViewModel(testSpeedUseCase) {

    override val dbInfo = DbInfo("Object Box")

    override fun testDbSpeed() {
        viewModelScope.launch(Dispatchers.IO) {
            val testResults = testSpeedUseCase(testIterations, testSizes)
            withContext(Dispatchers.Main) {
                _state.value = TestDbViewState.Content(dbInfo, testResults)
            }
        }
    }
}