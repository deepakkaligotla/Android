package `in`.kaligotla.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.lifecycle.databinding.ActivitySingleTaskBinding

class SingleTaskActivity : AppCompatActivity() {
    private lateinit var activitySingleTaskBinding: ActivitySingleTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySingleTaskBinding = ActivitySingleTaskBinding.inflate(layoutInflater)
        setContentView(activitySingleTaskBinding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(activitySingleTaskBinding.container.id, CommonViewFragment())
                .commit()
        }
    }
}