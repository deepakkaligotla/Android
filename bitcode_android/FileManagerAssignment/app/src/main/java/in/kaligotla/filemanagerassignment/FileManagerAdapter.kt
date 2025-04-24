package `in`.kaligotla.filemanagerassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import `in`.kaligotla.filemanagerassignment.databinding.ViewHolderFileManagerBinding
import java.io.File

class FileManagerAdapter(private var rootDir: File,
                         var showHidden: Boolean = true,
                         private val onDirectoryClick: (File) -> Unit) : RecyclerView.Adapter<FileManagerAdapter.FileManagerViewHolder>() {
    private var fileList: MutableList<File> = mutableListOf()

    init {
        setData(rootDir, showHidden)
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
        holder.binding.fileIcon.setImageResource(
            if (file.isDirectory) R.drawable.ic_folder else R.drawable.ic_launcher_background
        )

        holder.binding.root.setOnClickListener {
            if (file.isDirectory) {
                val children = file.listFiles()
                if (children.isNullOrEmpty()) {
                    Toast.makeText(holder.itemView.context, "${file.name} is empty", Toast.LENGTH_SHORT).show()
                } else {
                    onDirectoryClick(file)
                }
            } else {
                Toast.makeText(holder.itemView.context, "Selected file: ${file.name}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setData(newDir: File, showHidden: Boolean) {
        this.rootDir = newDir
        this.showHidden = showHidden
        fileList.clear()
        val allFiles = newDir.listFiles()?.toList() ?: emptyList()
        val visibleFiles = if (showHidden) allFiles else allFiles.filterNot { it.name.startsWith(".") }
        fileList.addAll(visibleFiles)
        notifyDataSetChanged()
    }
}