package `in`.kaligotla.mvvmdemo1.fragments

import `in`.kaligotla.mvvmdemo1.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import `in`.kaligotla.mvvmdemo1.database.AppDatabase
import `in`.kaligotla.mvvmdemo1.databinding.FragmentPostBinding
import `in`.kaligotla.mvvmdemo1.factory.PostViewModelFactory
import `in`.kaligotla.mvvmdemo1.network.NetworkService
import `in`.kaligotla.mvvmdemo1.repository.PostRepository
import `in`.kaligotla.mvvmdemo1.viewmodels.PostViewModel

class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding
    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // DataBinding Initialization
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false)

        // ViewModel Initialization
        val repository = PostRepository(AppDatabase.getInstance(requireContext()), NetworkService.getInstance())
        postViewModel = ViewModelProvider(this, PostViewModelFactory(repository))[PostViewModel::class.java]

        // Set ViewModel and LifecycleOwner
        binding.viewModel = postViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Load Data
        postViewModel.loadPostWithUser(201)

        return binding.root
    }
}