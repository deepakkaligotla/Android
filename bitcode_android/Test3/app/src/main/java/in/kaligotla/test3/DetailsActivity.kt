package `in`.kaligotla.test3

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.kaligotla.test3.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var activityDetailsBinding: ActivityDetailsBinding
    private lateinit var comment: Comment

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(activityDetailsBinding.root)
        extractData()
        bindData()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun extractData(){
        comment = intent.extras?.getSerializable ("selectedComment", Comment::class.java)!!
    }

    private fun bindData() {
        activityDetailsBinding.postId.text = "Post ID: ${comment.postId}"
        activityDetailsBinding.userId.text = "User ID: ${comment.user.id}"
        activityDetailsBinding.userName.text = "Username: ${comment.user.username}"
        activityDetailsBinding.fullName.text = "Full Name: ${comment.user.fullName}"
        activityDetailsBinding.body.text = comment.body
        activityDetailsBinding.likes.text = "👍${comment.likes}"
    }
}