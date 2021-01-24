package space.digitallab.testappforappcraft.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ListElement(

    @PrimaryKey
    var userId: String? = null,
    var id: String? = null,
    var title: String? = null
)