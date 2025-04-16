package `in`.kaligotla.datastructures.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import `in`.kaligotla.datastructures.data.domain.model.entities.Location

@Database(entities = [Location::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun DBLocationDao(): LocationDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "locations")
                .fallbackToDestructiveMigration()
                .build()
    }

}