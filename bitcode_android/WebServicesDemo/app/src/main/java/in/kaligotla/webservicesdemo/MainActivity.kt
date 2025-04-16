package `in`.kaligotla.webservicesdemo

import android.accounts.NetworkErrorException
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import `in`.kaligotla.webservicesdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException
import java.nio.channels.ConnectionPendingException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchUsersFromAPI()
    }

    private fun fetchUsersFromAPI() {
        lifecycleScope.launch {
            try {
                val usersModel = withContext(Dispatchers.IO) {
                    UserRepository().fetchUsers()
                }
                usersModel?.let { model ->
                    val userDataText = StringBuilder()
                    model.data.forEach { user ->
                        userDataText.append("${user.id}: ${user.first_name} ${user.last_name}\n${user.email}\n\n")
                    }
                    runOnUiThread {
                        binding.txtView.text = userDataText.toString()
                        Toast.makeText(this@MainActivity, "Fetched ${model.data.size} users!", Toast.LENGTH_SHORT).show()
                    }
                } ?: runOnUiThread {
                    Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("API Error", "Error fetching data: ${e.message}")
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Network error!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}