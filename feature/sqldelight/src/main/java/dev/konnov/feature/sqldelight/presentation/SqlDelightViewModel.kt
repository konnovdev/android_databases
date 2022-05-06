package dev.konnov.feature.sqldelight.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.konnov.feature.sqldelight.Database
import dev.konnov.feature.sqldelight.HockeyPlayer
import dev.konnov.feature.sqldelight.PlayerQueries
import javax.inject.Inject

@HiltViewModel
class SqlDelightViewModel @Inject constructor() : ViewModel() {

    fun testDbSpeed() {
        // TODO implement
        testSqlDelightWorks()
    }


    // TODO delete this, it's just test
    @Inject
    lateinit var database: Database

    private fun testSqlDelightWorks() {
        val playerQueries: PlayerQueries = database.playerQueries

        println(playerQueries.selectAll().executeAsList())
// Prints [HockeyPlayer(15, "Ryan Getzlaf")]

        playerQueries.insert(player_number = 10, full_name = "Corey Perry")
        println(playerQueries.selectAll().executeAsList())
// Prints [HockeyPlayer(15, "Ryan Getzlaf"), HockeyPlayer(10, "Corey Perry")]

        val player = HockeyPlayer(10, "Ronald McDonald")
        playerQueries.insertFullPlayerObject(player)

    }
}