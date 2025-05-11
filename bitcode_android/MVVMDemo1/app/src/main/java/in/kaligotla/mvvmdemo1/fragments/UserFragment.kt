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
import `in`.kaligotla.mvvmdemo1.adapters.PostAdapter
import `in`.kaligotla.mvvmdemo1.database.AppDatabase
import `in`.kaligotla.mvvmdemo1.databinding.FragmentUserBinding
import `in`.kaligotla.mvvmdemo1.factory.UserViewModelFactory
import `in`.kaligotla.mvvmdemo1.network.NetworkService
import `in`.kaligotla.mvvmdemo1.repository.UserRepository
import `in`.kaligotla.mvvmdemo1.viewmodels.UserViewModel


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)

        // ViewModel Initialization
        val repository = UserRepository(AppDatabase.getInstance(requireContext()), NetworkService.getInstance())
        userViewModel = ViewModelProvider(this, UserViewModelFactory(repository))[UserViewModel::class.java]

        // Set the ViewModel to the Binding
        binding.viewModel = userViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        // Initialize RecyclerView
        postAdapter = PostAdapter()
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.postsRecyclerView.adapter = postAdapter

        // Load Data
        userViewModel.loadUserAndProfile()
        userViewModel.loadUserWithPosts()

        // Observe Data
        userViewModel.userWithPosts.observe(viewLifecycleOwner) {
            postAdapter.submitList(it.posts)
        }

        return binding.root
    }
}