package `in`.kaligotla.mvvmdemo1.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import `in`.kaligotla.mvvmdemo1.repository.UserRepository
import `in`.kaligotla.mvvmdemo1.viewmodels.UserViewModel

class UserViewModelFactory(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}