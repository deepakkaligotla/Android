package `in`.kaligotla.bitcode_android_assignment7

import android.app.Dialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.bitcode_android_assignment7.databinding.TaskManagerViewHolderBinding

class TaskManagerAdapter(var tasks: ArrayList<Task>,
                         private val taskDao: TaskDao): RecyclerView.Adapter<TaskManagerAdapter.TaskManagerViewHolder>() {

    inner class TaskManagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var taskManagerViewHolderBinding = TaskManagerViewHolderBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskManagerViewHolder {
        val binding = TaskManagerViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskManagerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TaskManagerViewHolder, position: Int) {
        holder.taskManagerViewHolderBinding.txtViewTaskName.text = tasks[position].taskName
        holder.taskManagerViewHolderBinding.txtViewTaskStatus.text = tasks[position].taskStatus.toString()
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, TaskDetails::class.java)
            intent.putExtra("taskId", tasks[position].id)
            context.startActivity(intent)
        }
        holder.taskManagerViewHolderBinding.btnDelete.setOnClickListener {
            taskDao.deleteTaskById(tasks[position].id)
            tasks.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, tasks.size)
        }
        holder.itemView.setOnLongClickListener {
            val context = holder.itemView.context
            val statuses = TaskStatus.values().map { it.name }
            val dialog = Dialog(context)
            dialog.setContentView(R.layout.dialog_update_status)
            val spinner = dialog.findViewById<Spinner>(R.id.spinnerUpdateStatus)
            val btnUpdate = dialog.findViewById<Button>(R.id.btnUpdateStatus)
            val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, statuses)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.setSelection(statuses.indexOf(tasks[position].taskStatus.name))
            btnUpdate.setOnClickListener {
                val newStatus = TaskStatus.valueOf(spinner.selectedItem.toString())
                val task = tasks[position]
                task.taskStatus = newStatus
                taskDao.updateTask(task)
                tasks[position] = task
                notifyItemChanged(position)
                dialog.dismiss()
            }
            dialog.show()
            true
        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }
}