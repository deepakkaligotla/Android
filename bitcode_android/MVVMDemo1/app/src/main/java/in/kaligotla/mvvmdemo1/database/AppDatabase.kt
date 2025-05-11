package `in`.kaligotla.mvvmdemo1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import `in`.kaligotla.mvvmdemo1.database.daos.PostDao
import `in`.kaligotla.mvvmdemo1.database.daos.StudentDao
import `in`.kaligotla.mvvmdemo1.database.daos.UserDao
import `in`.kaligotla.mvvmdemo1.models.*

@Database(
    entities = [
        User::class,
        Profile::class,
        Post::class,
        Student::class,
        Course::class,
        StudentCourseCrossRef::class], version = 1
) abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getPostDao(): PostDao
    abstract fun getStudentDao(): StudentDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "app_database")
                .allowMainThreadQueries()
                .build()
        }
    }
}