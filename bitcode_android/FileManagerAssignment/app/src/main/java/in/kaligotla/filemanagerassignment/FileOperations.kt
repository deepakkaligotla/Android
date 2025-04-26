package `in`.kaligotla.filemanagerassignment

import java.io.*

object FileOperations {
    fun copyFile(source: File, destination: File): Boolean {
        return try {
            if (source.isDirectory) {
                if (!destination.exists()) destination.mkdirs()
                source.listFiles()?.forEach {
                    copyFile(it, File(destination, it.name))
                }
            } else {
                destination.outputStream().use { output ->
                    source.inputStream().use { input ->
                        input.copyTo(output)
                    }
                }
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun moveFile(source: File, destination: File): Boolean {
        return if (copyFile(source, destination)) {
            source.deleteRecursively()
        } else false
    }
}