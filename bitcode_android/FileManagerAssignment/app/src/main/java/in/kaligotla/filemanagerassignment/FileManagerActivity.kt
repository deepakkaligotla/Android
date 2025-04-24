package `in`.kaligotla.filemanagerassignment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import `in`.kaligotla.filemanagerassignment.databinding.ActivityFileManagerBinding
import java.io.File

class FileManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFileManagerBinding
    private val navStack = mutableListOf<File>()
    private lateinit var fileManagerAdapter: FileManagerAdapter
    var showHidden = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFileManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initPermissions()
        initListeners()
        val rootDir = File("/storage/emulated/0")
        navStack.add(rootDir)
        fileManagerAdapter = FileManagerAdapter(rootDir, showHidden) { clickedDir ->
            navStack.add(clickedDir)
            fileManagerAdapter.setData(clickedDir, showHidden)
        }
        binding.fileManagerRecyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.fileManagerRecyclerView.adapter = fileManagerAdapter
    }

    private fun initPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
            val intent = Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)
            startActivity(intent)
        }
    }

    private fun initListeners() {
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

    override fun onBackPressed() {
        if (navStack.size > 1) {
            navStack.removeLast()
            fileManagerAdapter.setData(navStack.last(), showHidden)
        } else {
            super.onBackPressed()
        }
    }
}