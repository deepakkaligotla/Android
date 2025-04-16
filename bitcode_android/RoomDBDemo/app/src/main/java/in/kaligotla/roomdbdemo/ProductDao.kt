package `in`.kaligotla.roomdbdemo

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDao {

    @Insert
    fun insertProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)

    @Query("Select * from Products")
    fun getAllProducts() : Cursor

    @Query("delete from Products where id = :id")
    fun delete(id : Int) : Int
}