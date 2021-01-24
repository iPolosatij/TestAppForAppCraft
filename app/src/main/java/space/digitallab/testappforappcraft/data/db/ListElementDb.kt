package space.digitallab.testappforappcraft.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import space.digitallab.testappforappcraft.data.dto.ListElement


@Database(entities = [ListElement::class], version = 1)
abstract class ListElementDb : RoomDatabase() {
    abstract fun listElementDao(): ListElementDao?
}