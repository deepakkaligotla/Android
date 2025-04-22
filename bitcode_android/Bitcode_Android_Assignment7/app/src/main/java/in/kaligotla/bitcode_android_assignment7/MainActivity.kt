package `in`.kaligotla.bitcode_android_assignment7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.bitcode_android_assignment7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var tasks: ArrayList<Task>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        var productDao = RoomSingleton.getDatabase(this).getTaskDao()
        tasks = ArrayList(productDao.getAllTasks())
        activityMainBinding.taskManagerRecyclerView.adapter = TaskManagerAdapter(tasks, productDao)
        activityMainBinding.taskManagerRecyclerView.layoutManager = LinearLayoutManager(this)
        activityMainBinding.btnAdd.setOnClickListener {
            val newTaskDialog = NewTaskDialog(this@MainActivity) { newTask ->
                tasks.add(newTask)
                activityMainBinding.taskManagerRecyclerView.adapter?.notifyItemInserted(tasks.size - 1)
            }
            newTaskDialog.show()
        }
    }
}