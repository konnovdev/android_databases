package dev.konnov.common.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.konnov.common.dbtestingtools.DbInfo
import dev.konnov.common.dbtestingtools.domain.entity.SIZE_100k
import dev.konnov.common.dbtestingtools.domain.entity.SIZE_10k
import dev.konnov.common.dbtestingtools.domain.entity.SIZE_1k
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.common.mvvm.TestDbViewState.Content
import dev.konnov.common.mvvm.TestDbViewState.InProgress
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class TestDbViewModel(private val testSpeedUseCase: TestSpeedUseCase) : ViewModel() {

    protected val _state = MutableStateFlow<TestDbViewState>(InProgress)
    val state: StateFlow<TestDbViewState> = _state

    protected open val testSizes = listOf(SIZE_1k, SIZE_10k, SIZE_100k)

    protected open val testIterations = 5

    protected abstract val dbInfo: DbInfo

    open fun testDbSpeed() {
        viewModelScope.launch {
            val testResults = testSpeedUseCase(testIterations, testSizes)
            _state.value = Content(dbInfo, testResults)
        }
    }
}
