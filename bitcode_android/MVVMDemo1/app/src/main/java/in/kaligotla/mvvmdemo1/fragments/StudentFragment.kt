package `in`.kaligotla.mvvmdemo1.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.kaligotla.mvvmdemo1.R
import `in`.kaligotla.mvvmdemo1.adapters.CourseAdapter
import `in`.kaligotla.mvvmdemo1.database.AppDatabase
import `in`.kaligotla.mvvmdemo1.databinding.FragmentStudentBinding
import `in`.kaligotla.mvvmdemo1.factory.StudentViewModelFactory
import `in`.kaligotla.mvvmdemo1.network.NetworkService
import `in`.kaligotla.mvvmdemo1.repository.StudentRepository
import `in`.kaligotla.mvvmdemo1.viewmodels.StudentViewModel


class StudentFragment : Fragment() {

    private lateinit var binding: FragmentStudentBinding
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // DataBinding Initialization
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_student, container, false)

        // ViewModel Initialization
        val repository = StudentRepository(AppDatabase.getInstance(requireContext()), NetworkService.getInstance())
        studentViewModel = ViewModelProvider(this, StudentViewModelFactory(repository))[StudentViewModel::class.java]

        // Set ViewModel and LifecycleOwner
        binding.viewModel = studentViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Initialize RecyclerView
        courseAdapter = CourseAdapter()
        binding.coursesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.coursesRecyclerView.adapter = courseAdapter

        // Load Data
        studentViewModel.loadStudentWithCourses(1)

        return binding.root
    }
}