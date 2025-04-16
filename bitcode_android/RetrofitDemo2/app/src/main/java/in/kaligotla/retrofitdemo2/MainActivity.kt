package `in`.kaligotla.retrofitdemo2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.retrofitdemo2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        activityMainBinding.btnFetch.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val response = APIServices.getInstance().getProductsWithLimit(activityMainBinding.limit.text.toString().toInt())
                withContext(Dispatchers.Main) {
                    activityMainBinding.recyclerView.adapter = ProductsAdapter(response)
                }
            }
        }
    }
}