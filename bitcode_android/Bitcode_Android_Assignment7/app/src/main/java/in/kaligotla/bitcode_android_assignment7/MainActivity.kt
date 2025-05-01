package `in`.kaligotla.bitcode_android_assignment7

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.bitcode_android_assignment7.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        activityMainBinding.taskManagerRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TaskManagerAdapter(
            onDeleteClick = { task ->
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        taskViewModel.deleteTaskById(task.id)
                    }
                }
            },
            onItemClick = { task ->
                val intent = Intent(this, TaskDetails::class.java)
                intent.putExtra("taskId", task.id)
                startActivity(intent)
            }
        )

        activityMainBinding.taskManagerRecyclerView.adapter = adapter
        activityMainBinding.taskManagerRecyclerView.adapter = adapter
        taskViewModel.allTasks.observe(this, { tasks ->
            adapter.submitList(tasks)
        })
        activityMainBinding.btnAdd.setOnClickListener {
            val newTaskDialog = NewTaskDialog(this) { newTask ->
                taskViewModel.insert(newTask)
            }
            newTaskDialog.show()
        }
    }
}