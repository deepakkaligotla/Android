package `in`.kaligotla.imagesliderdialog1

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import `in`.kaligotla.imagesliderdialog1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        activityMainBinding.showImageSliderDialog.setOnClickListener {
            val imageList = listOf(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3,
                R.drawable.image4,
                R.drawable.image5
            )
            ImageSliderDialog(
                this,
                imageList,
                delayMillis = 1000L,
                shouldLoop = true
            ).show()
        }
    }
}