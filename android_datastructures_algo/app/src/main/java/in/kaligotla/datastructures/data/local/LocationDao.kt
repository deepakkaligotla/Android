package `in`.kaligotla.datastructures.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import `in`.kaligotla.datastructures.core.Constants.SEARCH_BY_PINCODE
import `in`.kaligotla.datastructures.data.domain.model.entities.Location

@Dao
interface LocationDao {

    @Query("SELECT * FROM location")
    fun getAllLocations(): LiveData<List<Location>>

    @Query("SELECT * FROM location WHERE location_id = :id")
    fun getLocationById(id: Int): LiveData<Location>

    @Query(SEARCH_BY_PINCODE)
    fun getLocationByPincode(pincode: Int): LiveData<List<Location>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<Location>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: Location)
}