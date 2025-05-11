package `in`.kaligotla.mvvmdemo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import `in`.kaligotla.mvvmdemo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // Log to verify if the view is inflated
        if (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) != null) {
            println("✅ nav_host_fragment found in layout")
        } else {
            println("❌ nav_host_fragment NOT found in layout")
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        if (navHostFragment != null) {
            println("✅ NavHostFragment successfully retrieved")
            navController = navHostFragment.navController
        } else {
            throw IllegalStateException("❌ NavHostFragment not found!")
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }
}