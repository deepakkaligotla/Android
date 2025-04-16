package `in`.kaligotla.retrofitdemo4

import retrofit2.http.GET

interface APIServices {

    @GET("")
    suspend fun getAll(): APIResponse

    companion object {
        fun getInstance(): APIServices {
            
        }
    }
}