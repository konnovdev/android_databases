package dev.konnov.feature.room.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.feature.room.testik.AppDatabase
import dev.konnov.feature.room.testik.User
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor() : ViewModel() {

    fun testDbSpeed() {
        testRoomWorks()
    }

    @Inject
    lateinit var db: AppDatabase
    // TODO delete this, it's just for test
    private fun testRoomWorks() {
        viewModelScope.launch {
            val userDao = db.userDao()
            userDao.insertAll(User(UUID.randomUUID().toString().hashCode(), "alex", "snowden"), User(UUID.randomUUID().toString().hashCode(), "john", "albert"))
            val users: List<User> = userDao.getAll()
            println("room data: $users")
        }

    }
}