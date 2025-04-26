package `in`.kaligotla.test.ui.main

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import `in`.kaligotla.test.R
import `in`.kaligotla.test.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var fragmentMainBinding: FragmentMainBinding
    private lateinit var fileManagerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        val fileList = listOf("File1", "File2", "File3", "File4", "File5")
        fileManagerAdapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            private var items: List<String> = fileList
            inner class FileViewHolder(view: View) : RecyclerView.ViewHolder(view) {}
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
                return FileViewHolder(view)
            }
            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
            override fun getItemCount(): Int {
                return items.size
            }
        }
        fragmentMainBinding.fileManagerRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = fileManagerAdapter
        }
        return fragmentMainBinding.root
    }
}