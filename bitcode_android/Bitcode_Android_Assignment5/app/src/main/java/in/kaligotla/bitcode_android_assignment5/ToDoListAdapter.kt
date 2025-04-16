package `in`.kaligotla.bitcode_android_assignment5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.bitcode_android_assignment5.databinding.TodolistAdapterBinding

class ToDoListAdapter(private var toDoList: ArrayList<String>) :
    RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>() {

    interface OnDeleteBtnClickListener {
        fun onDelete(index: Int)
    }

    private var onDeleteBtnClickListener: OnDeleteBtnClickListener? = null

    fun setOnDeleteBtnClickListener(onDeleteBtnClickListener1: OnDeleteBtnClickListener?) {
        this.onDeleteBtnClickListener = onDeleteBtnClickListener1
    }

    inner class ToDoListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var todolistAdapterBinding = TodolistAdapterBinding.bind(itemView)

        init {
            todolistAdapterBinding.btnDelete.setOnClickListener {
                onDeleteBtnClickListener?.onDelete(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        return ToDoListViewHolder(TodolistAdapterBinding.inflate(LayoutInflater.from(parent.context)).root)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.todolistAdapterBinding.txtView.text = toDoList[position]
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }
}