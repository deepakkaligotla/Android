package `in`.kaligotla.bitcode_android_assignment7

import android.app.Dialog
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Toast
import `in`.kaligotla.bitcode_android_assignment7.databinding.ActivityNewTaskDialogBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewTaskDialog(
    context: Context,
    private val onTaskAdded: (Task) -> Unit
) : Dialog(context) {
    private var activityNewTaskDialogBinding: ActivityNewTaskDialogBinding =
        ActivityNewTaskDialogBinding.inflate(layoutInflater)

    init {
        setContentView(activityNewTaskDialogBinding.root)
        initListeners()
    }

    private fun initListeners() {
        val statuses = TaskStatus.values().map { it.name }
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, statuses)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        activityNewTaskDialogBinding.statusSpinner.adapter = adapter

        activityNewTaskDialogBinding.btnDone.setOnClickListener {
            val taskName = activityNewTaskDialogBinding.edtTxtNewTaskName.text.toString().trim()
            val selectedStatus = activityNewTaskDialogBinding.statusSpinner.selectedItem.toString()
            if (taskName.isEmpty()) {
                Toast.makeText(context, "Task name cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newTask = Task(
                id = (0..999999).random(),
                taskName = taskName,
                taskStatus = TaskStatus.valueOf(selectedStatus)
            )
            onTaskAdded.invoke(newTask)
            dismiss()
        }
    }
}