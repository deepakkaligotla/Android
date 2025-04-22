package `in`.kaligotla.lentra

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {
    @GET("continents")
    suspend fun getAllContinents(): ArrayList<Continent>

    companion object {
        fun getInstance(): APIService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://dummy-json.mock.beeceptor.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(APIService::class.java)
        }
    }
}