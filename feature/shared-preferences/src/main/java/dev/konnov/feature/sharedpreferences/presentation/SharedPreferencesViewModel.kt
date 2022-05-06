package dev.konnov.feature.sharedpreferences.presentation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedPreferencesViewModel @Inject constructor() : ViewModel() {

    fun testDbSpeed() {
        //TODO implement
        testSharedPreferencesWork()
    }

    // TODO delete, this is just for a test
    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private fun testSharedPreferencesWork() {
        sharedPreferences
            .edit()  // create an Editor
            .putString("key", "testkey")
            .apply() // write to disk asynchronously

        val key = sharedPreferences.getString("key", "")
        println("sharedprefs_test: $key")
    }
}