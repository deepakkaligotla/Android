package `in`.kaligotla.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.lifecycle.databinding.ActivityStandardBinding

class StandardActivity : AppCompatActivity() {
    private lateinit var activityStandardBinding: ActivityStandardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityStandardBinding = ActivityStandardBinding.inflate(layoutInflater)
        setContentView(activityStandardBinding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(activityStandardBinding.container.id, CommonViewFragment())
                .commit()
        }
    }
}