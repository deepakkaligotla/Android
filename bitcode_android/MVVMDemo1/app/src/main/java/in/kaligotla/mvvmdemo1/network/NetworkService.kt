package `in`.kaligotla.mvvmdemo1.network

import `in`.kaligotla.mvvmdemo1.database.relationships.PostWithUser
import `in`.kaligotla.mvvmdemo1.database.relationships.StudentWithCourses
import `in`.kaligotla.mvvmdemo1.database.relationships.UserAndProfile
import `in`.kaligotla.mvvmdemo1.database.relationships.UserWithPosts
import `in`.kaligotla.mvvmdemo1.database.relationships.UserWithProfileAndPosts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NetworkService {

    @GET("user/1/profile")
    suspend fun getUserAndProfile(): UserAndProfile

    @GET("user/1/posts")
    suspend fun getUserWithPosts(): UserWithPosts

    @GET("post/201/user")
    suspend fun getPostWithUser(): PostWithUser

    @GET("student/1/courses")
    suspend fun getStudentWithCourses(): StudentWithCourses

    @GET("user/1/details")
    suspend fun getUserWithProfileAndPosts(): UserWithProfileAndPosts

    companion object {
        fun getInstance(): NetworkService {
            val client = okhttp3.OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("accept", "application/json")
                        .header(
                            "x-api-key",
                            "PMAK-682070509ad3930001ed029b-6c2f013c78b516bce4a365ce6cc7547739"
                        )
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("https://11c73932-4f26-422b-8c64-938b32050a33.mock.pstmn.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NetworkService::class.java)
        }
    }
}