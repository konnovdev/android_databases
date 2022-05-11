package dev.konnov.feature.realm.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.common.dataset.newsreports.NewsReportDataGenerator
import dev.konnov.common.dbtestingtools.SIZE_10k
import dev.konnov.feature.realm.data.repository.NewsReportRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealmViewModel @Inject constructor(
    private val newsReportRepository: NewsReportRepository
) : ViewModel() {

    fun testDbSpeed() {
        // TODO implement
        testRealmWorks()
    }

    // TODO delete this method, it's just for testing that realm works
    private fun testRealmWorks() {
        viewModelScope.launch {
            newsReportRepository.insert(NewsReportDataGenerator.getEntities(SIZE_10k))
        }
    }
}