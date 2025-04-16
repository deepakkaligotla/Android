package `in`.kaligotla.datastructures.data.remote

import `in`.kaligotla.datastructures.data.domain.model.LocationsList
import `in`.kaligotla.datastructures.data.domain.model.entities.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LocationService {
    @GET("locations")
    suspend fun getAllLocations(): Response<LocationsList>

    @POST("location/{id}")
    suspend fun getLocationById(@Path("id") id: Int): Response<Location>

    @POST("/findByIdLocation/{pincode}")
    suspend fun getLocationByPincode(@Path("pincode") pincode: Int): Response<LocationsList>
}