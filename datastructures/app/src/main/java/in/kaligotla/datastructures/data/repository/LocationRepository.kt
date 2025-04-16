package `in`.kaligotla.datastructures.data.repository

import androidx.lifecycle.LiveData
import `in`.kaligotla.datastructures.core.LoadDataService
import `in`.kaligotla.datastructures.core.Resource
import `in`.kaligotla.datastructures.data.domain.model.entities.Location

typealias locationsDataFromMongo = LiveData<Resource<List<Location>>>
typealias locationsDataFromSQLite = LiveData<List<Location>>
typealias locationDataFromSQLite = LiveData<Location>

interface LocationRepository {
    val loadDataFromService: LoadDataService

    fun bindGetAllLocationsFromMongo(): locationsDataFromMongo
    fun bindGetAllLocationsFromSQLite(): locationsDataFromSQLite
    fun bindGetLocationByPincodeFromSQLite(pincode: Int): locationsDataFromSQLite
    fun bindGetLocationByIDFromSQLite(ID: Int): locationDataFromSQLite
}