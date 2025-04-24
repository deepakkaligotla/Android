package `in`.kaligotla.filemanagerassignment

import java.io.File

object FileUtils {
    fun listFilesIn(dir: File): List<File> {
        return dir.listFiles()?.toList() ?: emptyList()
    }
}