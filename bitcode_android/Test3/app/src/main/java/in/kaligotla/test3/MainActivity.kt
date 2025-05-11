package `in`.kaligotla.test3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.test3.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var comments: ArrayList<Comment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        comments = ArrayList()
        fetchAPIData()
        initRecyclerView()
    }

    private fun fetchAPIData() {
        CoroutineScope(Dispatchers.IO).launch {
            comments = APIServices.getInstance().getComments().comments
            Log.e("Data", "APIServices.getInstance().getComments()")
            withContext(Dispatchers.Main) {
                activityMainBinding.commentsRecyclerView.adapter = CommentAdapater(comments)
                activityMainBinding.commentsRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun initRecyclerView() {
        activityMainBinding.commentsRecyclerView.layoutManager = LinearLayoutManager(this)
        activityMainBinding.commentsRecyclerView.adapter = CommentAdapater(comments)
    }
}