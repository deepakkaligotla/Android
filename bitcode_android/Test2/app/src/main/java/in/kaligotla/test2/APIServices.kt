package `in`.kaligotla.test2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIServices {
    @GET("todos")
    suspend fun getAllTodos(): APIResponse

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