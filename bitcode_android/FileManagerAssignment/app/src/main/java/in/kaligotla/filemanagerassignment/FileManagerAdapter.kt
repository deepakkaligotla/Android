package `in`.kaligotla.filemanagerassignment

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.filemanagerassignment.databinding.ViewHolderFileManagerBinding
import java.io.File

class FileManagerAdapter(
    rootDir: File,
    showHiddenFiles: Boolean = true,
    private val onDirectoryClick: (File) -> Unit
) : RecyclerView.Adapter<FileManagerAdapter.FileManagerViewHolder>() {
    private var fileList: MutableList<File> = mutableListOf()
    private var selectionMode = false
    val selectedItems = mutableSetOf<File>()
    var onSelectionChanged: ((List<File>) -> Unit)? = null

    init {
        setData(rootDir, showHiddenFiles)
    }

    inner class FileManagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ViewHolderFileManagerBinding.bind(itemView)
    }

    override fun getItemCount(): Int = fileList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileManagerViewHolder {
        val binding = ViewHolderFileManagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FileManagerViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: FileManagerViewHolder, position: Int) {
        val file = fileList[position]
        holder.binding.fileName.text = file.name
        holder.binding.fileIcon.setImageResource(getFileIcon(file))
        holder.binding.checkBox.visibility = if (selectionMode) View.VISIBLE else View.GONE
        holder.binding.checkBox.isChecked = selectedItems.contains(file)
        holder.binding.checkBox.setOnClickListener {
            toggleSelection(file)
        }

        holder.binding.root.setOnClickListener {
            if (selectionMode) {
                toggleSelection(file)
            } else {
                if (file.isDirectory) {
                    val children = file.listFiles()
                    if (children.isNullOrEmpty()) {
                        Toast.makeText(holder.itemView.context, "${file.name} is empty", Toast.LENGTH_SHORT).show()
                    } else {
                        onDirectoryClick(file)
                    }
                } else {
                    val uri = FileProvider.getUriForFile(
                        holder.itemView.context,
                        "${holder.itemView.context.packageName}.provider",
                        file
                    )
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(uri, getMimeType(file))
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    holder.itemView.context.startActivity(Intent.createChooser(intent, "Open with"))
                }
            }
        }

        holder.binding.root.setOnLongClickListener {
            if (!selectionMode) {
                selectionMode = true
                selectedItems.add(file)
                onSelectionChanged?.invoke(selectedItems.toList())
                notifyDataSetChanged()
            }
            true
        }
    }

    private fun toggleSelection(file: File) {
        if (selectedItems.contains(file)) {
            selectedItems.remove(file)
        } else {
            selectedItems.add(file)
        }
        if (selectedItems.isEmpty()) {
            selectionMode = false
        }
        onSelectionChanged?.invoke(selectedItems.toList())
        notifyDataSetChanged()
    }

    fun clearSelection() {
        selectedItems.clear()
        notifyDataSetChanged()
    }

    fun getSelectedItems(): List<File> = selectedItems.toList()

    fun getFileIcon(file: File): Int {
        return when {
            file.isDirectory -> R.drawable.ic_folder
            file.extension in listOf("jpg", "png", "gif", "jpeg", "gif", "svg", "ai", "tiff", "webp") -> R.drawable.ic_image
            file.extension in listOf("avi", "flv", "mkv", "mov", "mp4", "mpg", "swf", "webm", "wmv") -> R.drawable.ic_video
            file.extension in listOf("mp3", "aac", "ac3", "aiff", "amr", "au", "flac", "mid", "mka", "ogg", "ra", "voc", "wav", "wma") -> R.drawable.ic_music
            file.extension in listOf("pdf") -> R.drawable.ic_pdf
            file.extension in listOf("csv", "dbf", "ods", "xls", "xlsb", "xlsm", "xlsx", "xlt", "xltm", "xltx", "xlw") -> R.drawable.ic_excel
            file.extension in listOf("doc", "docx", "odp", "odt", "wps") -> R.drawable.ic_word
            file.extension in listOf("pot", "potx", "ppa", "ppam", "pps", "ppsm", "ppsx", "ppt", "pptm",  "pptx") -> R.drawable.ic_power_point
            else -> R.drawable.ic_launcher_foreground
        }
    }

    fun getMimeType(file: File): String {
        return MimeTypeMap.getSingleton()
            .getMimeTypeFromExtension(file.extension.lowercase()) ?: "*/*"
    }

    fun setData(directory: File, showHiddenFiles: Boolean) {
        val files = directory.listFiles { file ->
            showHiddenFiles || !file.name.startsWith(".")
        }
        fileList.clear()
        files?.let { fileList.addAll(it) }
        notifyDataSetChanged()
    }

    fun setSearchResults(results: List<File>) {
        fileList.clear()
        fileList.addAll(results)
        selectionMode = false
        selectedItems.clear()
        notifyDataSetChanged()
        onSelectionChanged?.invoke(getSelectedItems())
    }
}