package `in`.kaligotla.test2

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.test2.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var todos: ArrayList<ToDo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        fetchData()
        initRecyclerView()
    }

    private fun fetchData() {
        todos = ArrayList()
        CoroutineScope(Dispatchers.IO).launch {
            val apiResponse = APIServices.getInstance().getAllTodos()
            withContext(Dispatchers.Main) {
                todos = apiResponse.todos
                activityMainBinding.todoRecyclerView.adapter = ToDoAdapter(todos)
            }
        }
    }

    private fun initRecyclerView() {
        activityMainBinding.todoRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}