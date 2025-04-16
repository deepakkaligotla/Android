package `in`.kaligotla.roomdbdemo

import android.util.Log
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase : RoomDatabase(){
    abstract fun getProductDao() : ProductDao

    fun testFunction(){
        Log.e("tag","hi from test function")
    }
}