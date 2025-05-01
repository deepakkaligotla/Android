package `in`.kaligotla.imagesliderdialog1

import android.app.Dialog
import android.app.Fragment
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.findViewTreeLifecycleOwner
import `in`.kaligotla.imagesliderdialog1.databinding.ImageSliderDialogBinding
import kotlinx.coroutines.*

class ImageSliderDialog(
    context: Context,
    private val imageIds: List<Int>,
    private val delayMillis: Long = 2000L,
    private val shouldLoop: Boolean = true
) : Dialog(context) {
    private val binding = ImageSliderDialogBinding.inflate(LayoutInflater.from(context))
    private var currentIndex = 0
    private var sliderJob: Job? = null

    init {
        setContentView(binding.root)
        startSlideshow()
    }

    private fun startSlideshow() {
        val lifecycleOwner = binding.root.findViewTreeLifecycleOwner() as? LifecycleOwner
        val scope = lifecycleOwner?.lifecycleScope ?: CoroutineScope(Dispatchers.Main)

        sliderJob = scope.launch {
            while (true) {
                binding.imgView.setImageResource(imageIds[currentIndex])
                delay(delayMillis)
                currentIndex++

                if (currentIndex >= imageIds.size) {
                    if (shouldLoop) {
                        currentIndex = 0
                    } else {
                        break
                    }
                }
            }
        }
    }

    override fun dismiss() {
        sliderJob?.cancel()
        super.dismiss()
    }
}