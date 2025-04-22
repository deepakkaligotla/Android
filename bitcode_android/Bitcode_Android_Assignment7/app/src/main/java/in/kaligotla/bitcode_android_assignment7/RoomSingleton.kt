package `in`.kaligotla.bitcode_android_assignment7

import android.content.Context
import androidx.room.Room

object RoomSingleton {
    @Volatile
    private var INSTANCE: TaskDatabase? = null
    fun getDatabase(context: Context): TaskDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "db_tasks"
            )
                .allowMainThreadQueries()
                .build()
            INSTANCE = instance
            instance
        }
    }
}