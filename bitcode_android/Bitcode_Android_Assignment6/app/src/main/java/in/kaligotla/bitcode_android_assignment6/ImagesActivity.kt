package `in`.kaligotla.bitcode_android_assignment6

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.bitcode_android_assignment6.databinding.ActivityImagesBinding

class ImagesActivity : AppCompatActivity() {
    private lateinit var activityImagesBinding: ActivityImagesBinding
    private lateinit var imageIds: ArrayList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityImagesBinding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(activityImagesBinding.root)
        imageIds = ArrayList<Int>()
        imageIds.add(R.drawable.image1)
        imageIds.add(R.drawable.image2)
        imageIds.add(R.drawable.image3)
        imageIds.add(R.drawable.image4)
        imageIds.add(R.drawable.image5)
        activityImagesBinding.imageRecyclerView.adapter = ImageAdapter(imageIds)
        activityImagesBinding.imageRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}