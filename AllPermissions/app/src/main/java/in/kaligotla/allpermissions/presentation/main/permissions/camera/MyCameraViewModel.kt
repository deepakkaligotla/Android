package `in`.kaligotla.allpermissions.presentation.main.permissions.camera

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MyCameraViewModel @Inject constructor(

) : ViewModel()  {
    fun createImageFile(context: Context): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_$timeStamp.jpg"
        val directory = context.getExternalFilesDir("Pictures")

        if (directory != null) {
            if (!directory.exists()) {
                directory.mkdirs()
            }
        }

        val image = File(directory, imageFileName)

        try {
            val fileCreated = image.createNewFile()
            if (fileCreated) {
                println("File created successfully: ${image.absolutePath}")
            } else {
                println("File creation failed")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return image
    }
}