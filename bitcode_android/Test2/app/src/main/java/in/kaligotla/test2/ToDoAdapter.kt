package `in`.kaligotla.test2

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.test2.databinding.ViewHolderTodoBinding

class ToDoAdapter(var todos: ArrayList<ToDo>): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    inner class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var viewHolderTodoBinding = ViewHolderTodoBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(ViewHolderTodoBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.viewHolderTodoBinding.todoId.text = "User ID: ${todos[position].userId}"
        holder.viewHolderTodoBinding.todoTxtView.text = "ID: ${todos[position].id} - ${todos[position].todo}"
        when(todos[position].status) {
            true -> {
                holder.viewHolderTodoBinding.status.apply {
                    text = "Status: Completed"
                    setTextColor(Color.GREEN)
                }
            }

            false -> {
                holder.viewHolderTodoBinding.status.apply {
                    text = "Status: Pending"
                    setTextColor(Color.RED)
                }
            }
        }
        holder.viewHolderTodoBinding.root.setOnClickListener {
            val intent = Intent(holder.itemView.context, ToDoDetailsAcitivty::class.java)
            intent.putExtra("selected_todo", todos[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}