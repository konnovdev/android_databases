package dev.konnov.feature.room.testik

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

// TODO delete, it's a test class from object box readme
@Entity
data class Playlist(
    @Id var id: Long = 0,
    val songName: String
)