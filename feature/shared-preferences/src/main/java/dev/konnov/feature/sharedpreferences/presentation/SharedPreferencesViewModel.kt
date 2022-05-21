package dev.konnov.feature.sharedpreferences.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dbtestingtools.domain.entity.SIZE_10k
import dev.konnov.common.dbtestingtools.domain.usecase.TestSpeedUseCase
import dev.konnov.common.mvvm.TestDbViewModel
import dev.konnov.common.mvvm.TestDbViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SharedPreferencesViewModel @Inject constructor(
    @Named("Sharedprefs_usecase")
    private val testSpeedUseCase: TestSpeedUseCase
) : TestDbViewModel(testSpeedUseCase) {

    override val testIterations = 1

    override val testSizes: List<Int> = listOf(SIZE_10k)

    override fun testDbSpeed() {
        viewModelScope.launch(IO) {
            val testResults = testSpeedUseCase(testIterations, testSizes)
            withContext(Main) {
                _state.value = TestDbViewState.Content(testResults)
            }
        }
    }
}