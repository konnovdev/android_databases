package dev.konnov.feature.room.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.feature.room.testik.Playlist
import io.objectbox.Box
import io.objectbox.BoxStore
import javax.inject.Inject


@HiltViewModel
class ObjectBoxViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var boxStore: BoxStore

    fun testDbSpeed() {
        // TODO implement
        testDbWorks()
    }

    // TODO delete this, it's just to test the db works
    private fun testDbWorks() {
        val box: Box<Playlist> = boxStore.boxFor(Playlist::class.java)
        box.put(Playlist(songName = "it's my life"))
        box.put(Playlist(songName = "g6"))

        println("ObjectBox test: ${box.all}")
    }
}