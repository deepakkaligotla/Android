package `in`.kaligotla.webservicesdemo

import com.google.gson.Gson
import java.net.HttpURLConnection
import java.net.URL

class UserRepositoryUsingGson {
    fun fetchUsers(): Model? {
        val url = URL("https://reqres.in/api/users?page=1")
        val connection = url.openConnection() as HttpURLConnection

        return try {
            connection.requestMethod = "GET"
            connection.connect()
            val response = connection.inputStream.bufferedReader().use { it.readText() }
            Gson().fromJson(response, Model::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            connection.disconnect()
        }
    }
}

class UserRepository {
    fun fetchUsers(): Model? {
        val url = URL("https://reqres.in/api/users?page=1")
        val connection = url.openConnection() as HttpURLConnection

        return try {
            connection.requestMethod = "GET"
            connection.connect()
            val response = connection.inputStream.bufferedReader().use { it.readText() }
            Gson().fromJson(response, Model::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            connection.disconnect()
        }
    }
}