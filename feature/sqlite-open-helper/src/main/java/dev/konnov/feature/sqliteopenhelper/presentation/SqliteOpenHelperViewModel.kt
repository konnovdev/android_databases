package dev.konnov.feature.sqliteopenhelper.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dbtestingtools.DbInfo
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
class SqliteOpenHelperViewModel @Inject constructor(
    @Named("Sqliteopenhelper_usecase")
    private val testSpeedUseCase: TestSpeedUseCase
) : TestDbViewModel(testSpeedUseCase) {

    override val dbInfo = DbInfo("SqliteOpenHelper")

    override fun testDbSpeed() {
        viewModelScope.launch(IO) {
            val testResults = testSpeedUseCase(testIterations, testSizes)
            withContext(Main) {
                _state.value = TestDbViewState.Content(dbInfo, testResults)
            }
        }
    }

}