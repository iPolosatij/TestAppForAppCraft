package space.digitallab.testappforappcraft.data.db

import androidx.room.*
import space.digitallab.testappforappcraft.data.dto.ListElement


@Dao
interface ListElementDao {
    @get:Query("SELECT * FROM listelement")
    val all: List<ListElement?>?

    @Query("SELECT * FROM listelement WHERE id = :id")
    fun getById(id: Long): ListElement?

    @Insert
    fun insert(listElement: ListElement?)

    @Update
    fun update(listElement: ListElement?)

    @Delete
    fun delete(listElement: ListElement?)
}