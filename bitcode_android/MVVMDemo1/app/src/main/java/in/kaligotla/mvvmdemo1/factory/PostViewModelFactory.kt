package `in`.kaligotla.mvvmdemo1.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import `in`.kaligotla.mvvmdemo1.repository.PostRepository
import `in`.kaligotla.mvvmdemo1.viewmodels.PostViewModel

class PostViewModelFactory(
    private val postRepository: PostRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(postRepository) as T
    }
}