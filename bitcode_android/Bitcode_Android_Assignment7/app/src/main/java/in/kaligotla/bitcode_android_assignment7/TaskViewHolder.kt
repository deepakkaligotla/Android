package `in`.kaligotla.bitcode_android_assignment7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.bitcode_android_assignment7.databinding.TaskManagerViewHolderBinding

class TaskViewHolder(
    private val binding: TaskManagerViewHolderBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task, onDeleteClick: (Task) -> Unit, onItemClick: (Task) -> Unit) {
        binding.txtViewTaskName.text = task.taskName
        binding.txtViewTaskStatus.text = task.taskStatus.toString()

        binding.btnDelete.setOnClickListener {
            onDeleteClick(task)
        }

        binding.root.setOnClickListener {
            onItemClick(task)
        }
    }

    companion object {
        fun create(parent: ViewGroup): TaskViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = TaskManagerViewHolderBinding.inflate(inflater, parent, false)
            return TaskViewHolder(binding)
        }
    }
}