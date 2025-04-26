package `in`.kaligotla.test

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import `in`.kaligotla.test.databinding.ActivityMainBinding
import `in`.kaligotla.test.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        val materialToolbar: MaterialToolbar = activityMainBinding.materialToolbar
        materialToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.favorite -> {
                    Toast.makeText(this, "Favorites Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.search -> {
                    Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.more -> {
                    Toast.makeText(this, "More Options Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}