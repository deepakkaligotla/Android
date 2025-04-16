package `in`.kaligotla.retrofitdemo5

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.kaligotla.retrofitdemo5.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var apiResponse: APIResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        CoroutineScope(Dispatchers.IO).launch {
            apiResponse = APIServices.getInstance().searchProduct("Laptop")
            for(product in apiResponse.products) {
                withContext(Dispatchers.Main) {
                    activityMainBinding.txtView.text = "${product.title}\n"
                }
            }
        }
    }
}