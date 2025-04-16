package `in`.kaligotla.retrofitdemo1

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.retrofitdemo1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.btnFetchData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val apiResponse = ApiServices.getInstance().getUsersPerPage(1)
                withContext(Dispatchers.Main) {
                    usersAdapter = UsersAdapter(users = apiResponse.users)
                    activityMainBinding.recyclerView.adapter = usersAdapter
                    activityMainBinding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.e("Register User",ApiServices.getInstance().registerUser(NewUser("eve.holt@reqres.in","pistol")).toString())
            Log.e("Update User",ApiServices.getInstance().updateUser(UpdateUser("morpheus","zion resident"), 2).toString())
            Log.e("Delete User", "${ApiServices.getInstance().deleteUser(2)}")
        }
    }
}