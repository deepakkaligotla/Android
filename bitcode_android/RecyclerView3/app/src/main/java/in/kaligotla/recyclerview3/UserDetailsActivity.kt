package `in`.kaligotla.recyclerview3

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.kaligotla.recyclerview3.databinding.ActivityUserDetailsBinding

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class UserDetailsActivity : AppCompatActivity() {
    private lateinit var userDetailsBinding: ActivityUserDetailsBinding
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        userDetailsBinding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(userDetailsBinding.root)
        extractData()
        bindData()
    }

    private fun extractData() {
        user = intent.extras?.getSerializable("user", User::class.java)!!
    }

    private fun bindData() {
        userDetailsBinding.userImage.setImageResource(R.drawable.ic_launcher_foreground)
        userDetailsBinding.userName.text = user.name
        userDetailsBinding.userDob.text = user.dob.toString()
    }
}