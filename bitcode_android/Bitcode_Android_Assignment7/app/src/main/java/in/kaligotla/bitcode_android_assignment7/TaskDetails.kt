package `in`.kaligotla.bitcode_android_assignment7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import `in`.kaligotla.bitcode_android_assignment7.databinding.ActivityTaskDetailsBinding
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskDetails : AppCompatActivity() {
    private lateinit var activityTaskDetailsBinding: ActivityTaskDetailsBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTaskDetailsBinding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(activityTaskDetailsBinding.root)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        val taskId = intent.getIntExtra("taskId", -1)
        if (taskId != -1) {
            taskViewModel.getTaskById(taskId).observe(this) { selectedTask ->
                selectedTask?.let {
                    activityTaskDetailsBinding.taskNameDetails.text = it.taskName
                    activityTaskDetailsBinding.taskStatusDetails.text = it.taskStatus.name
                }
            }
        }
    }
}