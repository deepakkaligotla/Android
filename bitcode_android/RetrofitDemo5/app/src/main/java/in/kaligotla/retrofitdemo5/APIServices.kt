package `in`.kaligotla.retrofitdemo5

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

    @GET("products/search")
    fun searchProduct(@Query("q") q: String): APIResponse

    companion object {
        fun getInstance(): APIServices {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(APIServices::class.java)
        }
    }
}