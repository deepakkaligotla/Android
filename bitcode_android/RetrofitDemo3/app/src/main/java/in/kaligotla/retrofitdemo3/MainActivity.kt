package `in`.kaligotla.retrofitdemo3

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.retrofitdemo3.databinding.ActivityMainBinding
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
        activityMainBinding.btnFetch.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val commentsList: List<APIResponse> = APIServices.getInstance().getComments().toList()
                withContext(Dispatchers.Main) {
                    val adapter = CommentsAdapter(emptyMap())
                    activityMainBinding.recyclerView.adapter = adapter
                    adapter.setComments(commentsList)
                    activityMainBinding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }
        }
    }
}