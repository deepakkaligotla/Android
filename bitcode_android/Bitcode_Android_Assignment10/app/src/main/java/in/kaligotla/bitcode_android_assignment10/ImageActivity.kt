package `in`.kaligotla.bitcode_android_assignment10

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import `in`.kaligotla.bitcode_android_assignment10.databinding.ActivityImageBinding

class ImageActivity : AppCompatActivity() {
    private lateinit var activityImageBinding: ActivityImageBinding
    private lateinit var images: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityImageBinding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(activityImageBinding.root)
        extractData()
        activityImageBinding.imagesRecyclerView.adapter = ImageAdapater(images)
        activityImageBinding.imagesRecyclerView.layoutManager = GridLayoutManager(this, 2)
    }

    private fun extractData() {
        val bundle = intent.extras
        if(bundle != null) {
            images = bundle.getIntArray("images")!!
        }
    }
}