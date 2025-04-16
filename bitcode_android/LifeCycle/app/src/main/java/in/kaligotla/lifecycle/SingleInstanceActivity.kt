package `in`.kaligotla.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.lifecycle.databinding.ActivitySingleInstanceBinding

class SingleInstanceActivity : AppCompatActivity() {
    private lateinit var singleInstanceBinding: ActivitySingleInstanceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        singleInstanceBinding = ActivitySingleInstanceBinding.inflate(layoutInflater)
        setContentView(singleInstanceBinding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(singleInstanceBinding.container.id, CommonViewFragment())
                .commit()
        }
    }
}