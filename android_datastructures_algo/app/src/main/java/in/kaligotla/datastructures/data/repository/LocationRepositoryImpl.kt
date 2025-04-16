package `in`.kaligotla.datastructures.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import `in`.kaligotla.datastructures.core.LoadDataService
import `in`.kaligotla.datastructures.core.performGetOperation
import `in`.kaligotla.datastructures.data.local.LocationDao
import `in`.kaligotla.datastructures.data.remote.LocationRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@RequiresApi(Build.VERSION_CODES.Q)
@Singleton
class LocationRepositoryImpl @Inject constructor(
    loadDataService: LoadDataService,
    private val remoteDataSource: LocationRemoteDataSource,
    private val localDataSource: LocationDao
) : LocationRepository {
    override val loadDataFromService: LoadDataService = loadDataService

    override fun bindGetAllLocationsFromMongo(): locationsDataFromMongo {
        return performGetOperation(
            databaseQuery = { localDataSource.getAllLocations() },
            networkCall = { remoteDataSource.getLocations() },
            saveCallResult = { localDataSource.insertAll(it.data) })
    }

    override fun bindGetAllLocationsFromSQLite(): locationsDataFromSQLite {
        return localDataSource.getAllLocations()
    }

    override fun bindGetLocationByPincodeFromSQLite(pincode: Int): locationsDataFromSQLite {
        return localDataSource.getLocationByPincode(pincode)
    }

    override fun bindGetLocationByIDFromSQLite(pincode: Int): locationDataFromSQLite {
        return localDataSource.getLocationById(pincode)
    }
}