package `in`.kaligotla.retrofitdemo2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {
    @GET("products")
    suspend fun getProductsWithLimit(@Query("limit") limit: Int): ArrayList<APIResponse>

    companion object {
        fun getInstance() : APIServices {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(APIServices::class.java)
        }
    }
}