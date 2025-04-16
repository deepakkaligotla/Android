package `in`.kaligotla.bitcode_android_assignment5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.bitcode_android_assignment5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var toDoList: ArrayList<String>
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var adapter: ToDoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        initData()
        initViews()
        initListeners()
    }

    private fun initData() {
        toDoList = ArrayList()
    }

    private fun initViews() {
        adapter = ToDoListAdapter(toDoList)
        adapter.setOnDeleteBtnClickListener(DeleteBtnClicked(toDoList, adapter))
        activityMainBinding.recyclerView.adapter = adapter
        activityMainBinding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initListeners() {
        activityMainBinding.btnAdd.setOnClickListener {
            val inputText = activityMainBinding.edtTxt.text.toString().trim()
            if (inputText.isNotEmpty()) {
                toDoList.add(inputText)
                adapter.notifyDataSetChanged()
            }
        }
    }

    class DeleteBtnClicked(private val toDoList: ArrayList<String>, private val adapter: ToDoListAdapter)
        : ToDoListAdapter.OnDeleteBtnClickListener {
        override fun onDelete(index: Int) {
            toDoList.removeAt(index)
            adapter.notifyDataSetChanged()
        }
    }
}