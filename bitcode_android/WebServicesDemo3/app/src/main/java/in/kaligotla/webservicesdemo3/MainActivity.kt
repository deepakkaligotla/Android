package `in`.kaligotla.webservicesdemo3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.webservicesdemo3.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var books: ArrayList<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("https://fakestoreapi.com/products")
            val connection = url.openConnection() as HttpsURLConnection

            connection.connect()

            var inStream = connection.inputStream
            val byteArray = ByteArray(1024 * 2)
            var responseBuffer = StringBuffer()
            var count: Int
            count = inStream.read(byteArray)

            while (count != -1) {
                responseBuffer.append(String(byteArray, 0, count))
                count = inStream.read(byteArray)
            }
            inStream.close()
            var jsonResponse = JSONArray(responseBuffer.toString())
            for (i in 0..jsonResponse.length() - 1) {
                Log.e("", jsonResponse[i].toString())
            }
        }
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val url = URL("https://fakestoreapi.com/users")
//            val connection = url.openConnection() as HttpsURLConnection
//
//            connection.connect()
//            var apiResponseStringBuffer = StringBuffer()
//            val inStream = connection.inputStream
//            val byteArray = ByteArray(1024 * 2)
//            var count: Int
//            count = inStream.read(byteArray)
//
//            while (count != -1) {
//                apiResponseStringBuffer.append(String(byteArray, 0, count))
//                count = inStream.read(byteArray)
//            }
//
//            inStream.close()
//            var apiResponse = JSONArray(apiResponseStringBuffer.toString())
//
//            for (i in 0..apiResponse.length() - 1) {
//                Log.e("", apiResponse[i].toString())
//            }
//        }
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val url = URL("https://fakestoreapi.com/products/1")
//            val connection = url.openConnection() as HttpsURLConnection
//
//            connection.connect()
//            var apiResStringBuffer = StringBuffer()
//            val inStream = connection.inputStream
//            val byteArray = ByteArray(1024 * 2)
//            var count: Int
//            count = inStream.read(byteArray)
//
//            while (count != -1) {
//                apiResStringBuffer.append(String(byteArray, 0, count))
//                count = inStream.read(byteArray)
//            }
//            inStream.close()
//
//            val apiResJsonObj = JSONObject(apiResStringBuffer.toString())
//
//            Log.e("", apiResJsonObj.toString())
//        }
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val url = URL("https://gorest.co.in/public/v2/todos")
//            val connection = url.openConnection() as HttpsURLConnection
//
//            connection.connect()
//
//            val apiResponseStringBuffer = StringBuffer()
//            val inStream = connection.inputStream
//            val byteArray = ByteArray(1024 * 2)
//            var count: Int
//            count = inStream.read(byteArray)
//
//            while (count != -1) {
//                apiResponseStringBuffer.append(String(byteArray, 0, count))
//                count = inStream.read(byteArray)
//            }
//
//            inStream.close()
//            val apiResJsonArray = JSONArray(apiResponseStringBuffer.toString())
//            for (i in 0..<apiResJsonArray.length()) {
//                Log.e("", apiResJsonArray[i].toString())
//            }
//        }
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val url = URL("https://fakestoreapi.com/products/category/jewelery")
//            val connection = url.openConnection() as HttpsURLConnection
//            connection.connect()
//
//            val inputStream = connection.inputStream
//            val apiResponseStringBuffer = StringBuffer()
//            val byteArray = ByteArray(1024 * 2)
//            var count: Int
//            count = inputStream.read(byteArray)
//
//            while (count != -1) {
//                apiResponseStringBuffer.append(String(byteArray, 0, count))
//                count = inputStream.read(byteArray)
//            }
//            inputStream.close()
//
//            val apiResponseJsonArray = JSONArray(apiResponseStringBuffer.toString())
//            for (i in 0..apiResponseJsonArray.length() - 1) {
//                Log.e("", apiResponseJsonArray[i].toString())
//            }
//        }
//
//        CoroutineScope(Dispatchers.IO).launch {
//            val url = URL("https://fakestoreapi.com/carts?userId=1")
//            val connection = url.openConnection() as HttpsURLConnection
//            connection.connect()
//
//            val apiResponseStringBuffer = StringBuffer()
//            val inputStream = connection.inputStream
//            val byteArray = ByteArray(1024)
//            var count: Int
//            count = inputStream.read(byteArray)
//
//            while (count != -1) {
//                apiResponseStringBuffer.append(String(byteArray, 0, count))
//                count = inputStream.read(byteArray)
//            }
//
//            inputStream.close()
//
//            val apiResponseJsonArray = JSONArray(apiResponseStringBuffer.toString())
//
//            for (i in 0..apiResponseJsonArray.length() - 1) {
//                Log.e("", apiResponseJsonArray[i].toString())
//            }
//        }

//        CoroutineScope(Dispatchers.IO).launch {
//            val url = URL("https://api.itbook.store/1.0/new")
//            val connection = url.openConnection() as HttpsURLConnection
//            connection.connect()
//
//            val responseStringBuffer = StringBuffer()
//            val inputStream = connection.inputStream
//            val byteArray = ByteArray(1024)
//
//            var count: Int
//            count = inputStream.read(byteArray)
//
//            while(count != -1) {
//                responseStringBuffer.append(String(byteArray, 0, count))
//                count = inputStream.read(byteArray)
//            }
//            inputStream.close()
//
//            val responseJson = JSONObject(responseStringBuffer.toString())
//            val data: JSONArray = responseJson.getJSONArray("books")
//            books = ArrayList()
//
//            for(i in 0..data.length()-1) {
//                val jsonBook = data[i] as JSONObject
//                val book = Book(
//                    jsonBook.getString("title"),
//                    jsonBook.getString("subtitle"),
//                    jsonBook.getString("isbn13"),
//                    jsonBook.getString("price"),
//                    jsonBook.getString("image"),
//                    jsonBook.getString("url"),
//                )
//                books.add(book)
//            }
//
//            for(i in 0..books.size-1) {
//                Log.e("", books[i].toString())
//            }
//        }

//        CoroutineScope(Dispatchers.IO).launch {
//            val url = URL("https://reqres.in/api/users?page=2")
//            val connection = url.openConnection() as HttpsURLConnection
//            connection.connect()
//
//            val resStringBuffer = StringBuffer()
//            val inputStream = connection.inputStream
//            val byteArray = ByteArray(1024 *3)
//            var count: Int
//            count = inputStream.read(byteArray)
//
//            while(count != -1) {
//                resStringBuffer.append(String(byteArray, 0, count))
//                count = inputStream.read(byteArray)
//            }
//            inputStream.close()
//
//            val resJsonObject = JSONObject(resStringBuffer.toString())
//            val data = resJsonObject.getJSONArray("data")
//            Log.e("", data.toString())
//        }
    }
}