package `in`.kaligotla.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.lifecycle.databinding.ActivitySingleTopBinding

class SingleTopActivity : AppCompatActivity() {
    private lateinit var activitySingleTopBinding: ActivitySingleTopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySingleTopBinding = ActivitySingleTopBinding.inflate(layoutInflater)
        setContentView(activitySingleTopBinding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(activitySingleTopBinding.container.id, CommonViewFragment())
                .commit()
        }
    }
}