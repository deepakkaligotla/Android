package `in`.kaligotla.mvvmdemo1.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.kaligotla.mvvmdemo1.database.relationships.UserAndProfile
import `in`.kaligotla.mvvmdemo1.database.relationships.UserWithPosts
import `in`.kaligotla.mvvmdemo1.database.relationships.UserWithProfileAndPosts
import `in`.kaligotla.mvvmdemo1.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel(){
    private val _userAndProfile = MutableLiveData<UserAndProfile>()
    val userAndProfile: LiveData<UserAndProfile> = _userAndProfile

    private val _userWithPosts = MutableLiveData<UserWithPosts>()
    val userWithPosts: LiveData<UserWithPosts> = _userWithPosts

    private val _userWithProfileAndPosts = MutableLiveData<UserWithProfileAndPosts>()
    val userWithProfileAndPosts: LiveData<UserWithProfileAndPosts> = _userWithProfileAndPosts

    fun loadUserAndProfile() {
        viewModelScope.launch {
            userRepository.fetchUserAndProfile()
            _userAndProfile.postValue(userRepository.getUserAndProfile(1))
        }
    }

    fun loadUserWithPosts() {
        viewModelScope.launch {
            userRepository.fetchUserWithPosts()
            _userWithPosts.postValue(userRepository.getUserWithPosts(1))
        }
    }

    fun loadUserWithProfileAndPosts() {
        viewModelScope.launch {
            userRepository.fetchUserWithProfileAndPosts()
            _userWithProfileAndPosts.postValue(userRepository.getUserWithProfileAndPosts(1))
        }
    }
}