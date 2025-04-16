package `in`.kaligotla.retrofitdemo3

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {
    @GET("comments")
    suspend fun getComments(): Array<APIResponse>

    companion object {
        fun getInstance(): APIServices {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://gorest.co.in/public/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(APIServices::class.java)
        }
    }
}