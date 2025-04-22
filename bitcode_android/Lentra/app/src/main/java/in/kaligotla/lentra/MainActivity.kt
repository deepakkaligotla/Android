package `in`.kaligotla.lentra

import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.lentra.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var continents: ArrayList<Continent>
    private lateinit var continentAdapter: ContinentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        continents = ArrayList()
        continentAdapter = ContinentAdapter(continents)
        activityMainBinding.recyclerViewContinents.layoutManager = LinearLayoutManager(this)
        activityMainBinding.recyclerViewContinents.adapter = continentAdapter
        fetchAPI()
    }

    private fun fetchAPI() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                continents.addAll(APIService.getInstance().getAllContinents())
                continentAdapter.notifyDataSetChanged()
            }
        }
    }
}