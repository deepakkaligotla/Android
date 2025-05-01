package `in`.kaligotla.bitcode_android_assignment10

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.kaligotla.bitcode_android_assignment10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.btn1.setOnClickListener(this)
        activityMainBinding.btn2.setOnClickListener(this)
        activityMainBinding.btn3.setOnClickListener(this)
        activityMainBinding.btn4.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            activityMainBinding.btn1.id -> {
                var intent = Intent(this, ImageActivity::class.java)
                intent.putExtra("images", intArrayOf(R.drawable.lion_1, R.drawable.lion_2, R.drawable.lion_3, R.drawable.lion_4))
                startActivityForResult(intent,1)
            }
            activityMainBinding.btn2.id -> {
                var intent = Intent(this, ImageActivity::class.java)
                intent.putExtra("images", intArrayOf(R.drawable.dog_1, R.drawable.dog_3, R.drawable.dog_4))
                startActivityForResult(intent,1)
            }
            activityMainBinding.btn3.id -> {
                var intent = Intent(this, ImageActivity::class.java)
                intent.putExtra("images", intArrayOf(R.drawable.cat_1, R.drawable.cat_2, R.drawable.cat_3, R.drawable.cat_4))
                startActivityForResult(intent,1)
            }
            activityMainBinding.btn4.id -> {
                var intent = Intent(this, ImageActivity::class.java)
                intent.putExtra("images", intArrayOf(R.drawable.peacock_1, R.drawable.peacock_2, R.drawable.peacock_3, R.drawable.peacock_4))
                startActivityForResult(intent,1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val selectedImageID = data?.getIntExtra("selectedImage", 0)
            if(selectedImageID != null)
            activityMainBinding.selectedImageView.setImageResource(selectedImageID)
        }
    }
}