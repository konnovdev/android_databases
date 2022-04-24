package dev.konnov.feature.sqlitehelper

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SqliteHelperViewModel @Inject constructor() : ViewModel() {

    fun testDbSpeed() {
        // TODO test db speed here
    }
}