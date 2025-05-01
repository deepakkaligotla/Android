package `in`.kaligotla.filemanagerassignment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import `in`.kaligotla.filemanagerassignment.databinding.ActivityFileManagerBinding
import java.io.File

class FileManagerActivity : AppCompatActivity(){
    private lateinit var binding: ActivityFileManagerBinding
    private val navStack = mutableListOf<File>()
    private lateinit var fileManagerAdapter: FileManagerAdapter
    var showHidden = true
    private var selectedFiles = mutableListOf<File>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPermissions()
        initListeners()
        supportActionBar?.title = ""
        actionBar?.title = ""
        val rootDir = File("/storage/emulated/0")
        navStack.add(rootDir)
        fileManagerAdapter = FileManagerAdapter(rootDir, showHidden) { clickedDir ->
            navStack.add(clickedDir)
            fileManagerAdapter.setData(clickedDir, showHidden)
        }
        fileManagerAdapter.onSelectionChanged = {
            toggleOptionsMenuVisibility(it)
            Log.e("onSelectionChanged", it.toString())
        }
        binding.fileManagerRecyclerView.layoutManager = GridLayoutManager(this, 4)
        binding.fileManagerRecyclerView.adapter = fileManagerAdapter
    }

    private fun initPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
            val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            startActivity(intent)
        }
    }

    private fun initListeners() {
        updateButtonText()
        binding.btnShowHidden.setOnClickListener {
            showHidden = !showHidden
            updateButtonText()
            fileManagerAdapter.setData(navStack.last(), showHidden)
        }
    }

    private fun updateButtonText() {
        if (showHidden) {
            binding.btnShowHidden.text = "🙈"
        } else {
            binding.btnShowHidden.text = "🐵"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        val searchItem = menu?.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { fileManagerAdapter.setSearchResults(searchFilesRecursively(navStack.last(),it)) }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { fileManagerAdapter.setSearchResults(searchFilesRecursively(navStack.last(),it)) }
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_delete -> {
                    if (selectedFiles.isEmpty()) {
                        Toast.makeText(this, "No items selected", Toast.LENGTH_SHORT).show()
                    } else {
                        AlertDialog.Builder(this)
                            .setTitle("Delete Confirmation")
                            .setMessage("Are you sure you want to delete ${selectedFiles.size} item(s)?")
                            .setPositiveButton("Delete") { _, _ ->
                                for (file in selectedFiles) {
                                    deleteRecursively(file)
                                }
                                fileManagerAdapter.clearSelection()
                                fileManagerAdapter.setData(navStack.last(), showHidden)
                                Toast.makeText(
                                    this,
                                    "Deleted ${selectedFiles.size} item(s)",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .setNegativeButton("Cancel", null)
                            .show()
                    }
                    true
                }

                R.id.action_copy -> {
                    val selectedFiles = fileManagerAdapter.getSelectedItems()
                    if (selectedFiles.isEmpty()) {
                        Toast.makeText(this, "No items selected", Toast.LENGTH_SHORT).show()
                    } else {
                        showCopyMoveDialog(selectedFiles)
                    }
                    true
                }

                R.id.action_rename -> {
                    if (selectedFiles.size == 1) {
                        showRenameDialog(selectedFiles[0])
                    }
                    true
                }
                else -> false
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        Log.e("onPrepareOptionsMenu", selectedFiles.toString())
        menu?.findItem(R.id.action_copy)?.isVisible = selectedFiles.isNotEmpty()
        menu?.findItem(R.id.action_delete)?.isVisible = selectedFiles.isNotEmpty()
        menu?.findItem(R.id.action_rename)?.isVisible = selectedFiles.size == 1
        return super.onPrepareOptionsMenu(menu)
    }

    private fun toggleOptionsMenuVisibility(selectedItems: List<File>) {
        invalidateOptionsMenu()
    }

    private fun searchFilesRecursively(dir: File, keyword: String): List<File> {
        val result = mutableListOf<File>()
        val files = dir.listFiles() ?: return result
        for (file in files) {
            if (showHidden || !file.name.startsWith(".")) {
                if (file.name.contains(keyword, ignoreCase = true)) {
                    result.add(file)
                    println("Found match: ${file.absolutePath}")
                }
                if (file.isDirectory) {
                    println("Recursing into: ${file.absolutePath}")
                    result.addAll(searchFilesRecursively(file, keyword))
                }
            }
        }
        return result
    }

    private fun deleteRecursively(file: File): Boolean {
        if (file.isDirectory) {
            file.listFiles()?.forEach { deleteRecursively(it) }
        }
        return file.delete()
    }

    private fun showCopyMoveDialog(selectedFiles: List<File>) {
        val options = arrayOf("Copy", "Move")
        AlertDialog.Builder(this)
            .setTitle("What would you like to do?")
            .setItems(options) { _, which ->
                val isCopy = (which == 0)
                chooseTargetFolder(selectedFiles, isCopy)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun chooseTargetFolder(selectedFiles: List<File>, isCopy: Boolean) {
        val folders = File("/storage/emulated/0")
            .listFiles { file -> file.isDirectory && (showHidden || !file.name.startsWith(".")) }
            ?.map { it.absolutePath }?.toTypedArray() ?: arrayOf()

        AlertDialog.Builder(this)
            .setTitle("Choose destination folder")
            .setItems(folders) { _, which ->
                val targetDir = File(folders[which])
                for (file in selectedFiles) {
                    val targetFile = File(targetDir, file.name)
                    val success = if (isCopy)
                        FileOperations.copyFile(file, targetFile)
                    else
                        FileOperations.moveFile(file, targetFile)

                    if (!success) {
                        Toast.makeText(
                            this,
                            "Failed to ${if (isCopy) "copy" else "move"} ${file.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                fileManagerAdapter.clearSelection()
                fileManagerAdapter.setData(navStack.last(), showHidden)
                Toast.makeText(
                    this,
                    "${if (isCopy) "Copied" else "Moved"} ${selectedFiles.size} item(s)",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showRenameDialog(file: File) {
        val input = android.widget.EditText(this)
        input.setText(file.name)

        AlertDialog.Builder(this)
            .setTitle("Rename")
            .setView(input)
            .setPositiveButton("Rename") { _, _ ->
                val newName = input.text.toString().trim()
                if (newName.isNotEmpty()) {
                    val newFile = File(file.parentFile, newName)
                    if (file.renameTo(newFile)) {
                        Toast.makeText(this, "Renamed to $newName", Toast.LENGTH_SHORT).show()
                        fileManagerAdapter.setData(navStack.last(), showHidden)
                    } else {
                        Toast.makeText(this, "Rename failed", Toast.LENGTH_SHORT).show()
                    }
                    fileManagerAdapter.clearSelection()
                    selectedFiles = mutableListOf<File>()
                    invalidateOptionsMenu()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    override fun onBackPressed() {
        if (navStack.size > 1) {
            navStack.removeLast()
            fileManagerAdapter.setData(navStack.last(), showHidden)
        } else {
            super.onBackPressed()
        }
    }
}