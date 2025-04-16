package `in`.kaligotla.retrofitdemo1

import android.net.http.HttpResponseCache
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("users")
    suspend fun getUsersPerPage(@Query("page") pageNum: Int): ApiResponse

    @POST("register")
    suspend fun registerUser(@Body user: NewUser): PostResponseForRegisterUser

    @PUT("users/{id}")
    suspend fun updateUser(@Body modifiedUser: UpdateUser, @Path("id") userId: Int): PutResponseForUpdateUser

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") userId: Int): Response<Unit>

    companion object {
        fun getInstance(): ApiServices {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiServices::class.java)
        }
    }
}