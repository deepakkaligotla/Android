package `in`.kaligotla.navigationdemo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import `in`.kaligotla.navigationdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.next.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra("key", "Value")
            startActivityForResult(intent, 1)
        }
    }
}