package `in`.kaligotla.test2

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.kaligotla.test2.databinding.ActivityToDoDetailsAcitivtyBinding

class ToDoDetailsAcitivty : AppCompatActivity() {
    private lateinit var toDoDetailsAcitivtyBinding: ActivityToDoDetailsAcitivtyBinding
    private lateinit var selectedToDo: ToDo

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toDoDetailsAcitivtyBinding = ActivityToDoDetailsAcitivtyBinding.inflate(layoutInflater)
        setContentView(toDoDetailsAcitivtyBinding.root)
        getDataFromIntent()
        bindData()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun getDataFromIntent() {
        val intent = intent
        selectedToDo = intent.extras?.getSerializable("selected_todo", ToDo::class.java)!!
    }

    private fun bindData() {
        toDoDetailsAcitivtyBinding.userId.text = "User ID: ${selectedToDo.userId}"
        toDoDetailsAcitivtyBinding.todoDetails.text = "${selectedToDo.id} - ${selectedToDo.todo}"
        when(selectedToDo.status) {
            true -> {
                toDoDetailsAcitivtyBinding.todoStatus.text = "Status: Completed"
                toDoDetailsAcitivtyBinding.todoStatus.setTextColor(Color.GREEN)
            }
            false -> {
                toDoDetailsAcitivtyBinding.todoStatus.text = "Status: Pending"
                toDoDetailsAcitivtyBinding.todoStatus.setTextColor(Color.RED)
            }
        }
    }
}