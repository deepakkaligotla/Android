package `in`.kaligotla.allpermissions.presentation.main.permissions.photosVideos

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MyPhotosVideosViewModel @Inject constructor(

) : ViewModel() {

    private var photoVideosFilesExtensionArray = ArrayList<String>()
    var photoVideosFilesExtensionList by mutableStateOf(emptyList<String>())

    private var filesArray = ArrayList<File>()
    var photoVideosFilesList by mutableStateOf(emptyList<File>())

    val isPhoto = listOf(
        "bpm", "cr2", "cur", "dds", "dng", "erf", "exr", "fts", "gif",
        "hdr", "heic", "heif", "ico", "jfif", "jp2", "jpe", "jpeg", "jpg",
        "jps", "mng", "nef", "nrw", "orf", "pam", "pbm", "pcd", "pcx", "pef",
        "pes", "pfm", "pgm", "picon", "pict", "png", "pnm", "ppm", "psd",
        "raf", "ras", "rw2", "sfw", "sgi", "svg", "tga", "tiff", "wbmp",
        "webp", "wpg", "x3f", "xbm", "xcf", "xpm", "xwd")
    val isVideo = listOf(
        "3gp", "asf", "avi", "f4v", "flv", "hevc", "m2ts", "m2v", "m4v", "mjpeg",
        "mkv", "mov", "mp4", "mpeg", "mpg", "mts", "mxf", "ogv", "rm", "swf",
        "ts", "vob", "webm", "wmv", "wtv")

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("Range")
    fun createCursor(context: Context) {
        val filesInternal: Cursor? = context.contentResolver.query(
            MediaStore.Files.getContentUri("internal"),
            null,
            null,
            null,
            null
        )

        val filesExternal: Cursor? = context.contentResolver.query(
            MediaStore.Files.getContentUri("external"),
            null,
            null,
            null,
            null
        )

        combinedResults(filesInternal)
        combinedResults(filesExternal)
    }

    @SuppressLint("Range")
    private fun combinedResults(cursor: Cursor?) {
        while(cursor?.moveToNext()==true) {
            val filePath1 = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA))
            val fileExtension = cursor.getString(cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME)).substringAfterLast('.', "")
            check(File(filePath1), fileExtension)
        }
        cursor?.close()
        photoVideosFilesList = filesArray.toList()
        photoVideosFilesExtensionList = photoVideosFilesExtensionArray.toList()
    }

    private fun check(temp: File, fileExtension: String) {
        for(extension in isPhoto+isVideo) {
            if(extension==fileExtension && !photoVideosFilesExtensionArray.contains(fileExtension)) {
                photoVideosFilesExtensionArray.add(fileExtension)
            }
        }
        if(photoVideosFilesExtensionArray.contains(fileExtension) && !filesArray.contains(temp)) {
            filesArray.add(temp)
        }
    }
}