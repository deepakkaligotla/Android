package `in`.kaligotla.bitcode_android_assignment7

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.kaligotla.bitcode_android_assignment7.databinding.ActivityTaskDetailsBinding

class TaskDetails : AppCompatActivity() {
    private lateinit var activityTaskDetailsBinding: ActivityTaskDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTaskDetailsBinding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(activityTaskDetailsBinding.root)
        getSelectedTask()
    }

    fun getSelectedTask() {
        val selectedTask = RoomSingleton.getDatabase(this).getTaskDao().getTaskById(intent.extras!!.getInt("taskId"))
        activityTaskDetailsBinding.taskNameDetails.text = selectedTask.taskName
        activityTaskDetailsBinding.taskStatusDetails.text = selectedTask.taskStatus.toString()
    }
}