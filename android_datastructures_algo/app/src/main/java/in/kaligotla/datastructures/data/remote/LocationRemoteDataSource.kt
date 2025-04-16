package `in`.kaligotla.datastructures.data.remote

import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(
    private val locationService: LocationService
) : BaseDataSource() {
    suspend fun getLocations() = getResult { locationService.getAllLocations() }
    suspend fun getLocationById(id: Int) = getResult { locationService.getLocationById(id) }
    suspend fun getLocationByPincode(pincode: Int) =
        getResult { locationService.getLocationByPincode(pincode) }
}