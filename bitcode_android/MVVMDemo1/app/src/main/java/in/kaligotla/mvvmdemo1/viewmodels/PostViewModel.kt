package `in`.kaligotla.mvvmdemo1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.kaligotla.mvvmdemo1.database.relationships.PostWithUser
import `in`.kaligotla.mvvmdemo1.repository.PostRepository
import kotlinx.coroutines.launch


class PostViewModel(private val postRepository: PostRepository) : ViewModel() {
    private val _postWithUser = MutableLiveData<PostWithUser>()
    val postWithUser: LiveData<PostWithUser> = _postWithUser

    fun loadPostWithUser(postId: Int) {
        viewModelScope.launch {
            postRepository.fetchPostWithUser()
            _postWithUser.postValue(postRepository.getPostWithUser(postId))
        }
    }
}