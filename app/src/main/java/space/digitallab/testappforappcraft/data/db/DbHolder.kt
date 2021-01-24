package space.digitallab.testappforappcraft.data.db

import android.app.Application
import androidx.room.Room


class DbHolder : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getDatabase(): ListElementDb? {

        return Room.databaseBuilder(
            applicationContext,
            ListElementDb::class.java, "database"
        )
            .allowMainThreadQueries()
            .build()
    }

    companion object {
        var instance: DbHolder? = null
    }
}