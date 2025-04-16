package `in`.kaligotla.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.lifecycle.databinding.ActivitySingleInstancePerTaskBinding

class SingleInstancePerTaskActivity : AppCompatActivity() {
    private lateinit var activitySingleInstancePerTaskBinding: ActivitySingleInstancePerTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySingleInstancePerTaskBinding = ActivitySingleInstancePerTaskBinding.inflate(layoutInflater)
        setContentView(activitySingleInstancePerTaskBinding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(activitySingleInstancePerTaskBinding.container.id, CommonViewFragment())
                .commit()
        }
    }
}