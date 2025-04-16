package `in`.kaligotla.navigationdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.kaligotla.navigationdemo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var activitySecondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activitySecondBinding.root)
        activitySecondBinding.previous.setOnClickListener {
            val intent: Intent = Intent()
            intent.putExtra("key","value")
            setResult(1, intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val b: Bundle = data?.extras!!
        Toast.makeText(this, b.getString("key"), Toast.LENGTH_SHORT).show()
    }
}