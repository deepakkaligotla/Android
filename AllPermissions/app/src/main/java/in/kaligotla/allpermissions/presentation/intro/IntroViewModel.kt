package `in`.kaligotla.allpermissions.presentation.intro

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.allpermissions.data.repository.proto.AppPreferencesRepository
import `in`.kaligotla.allpermissions.data.repository.proto.OnboardStateRepository
import `in`.kaligotla.allpermissions.proto.AppTheme
import `in`.kaligotla.allpermissions.proto.MyAppTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val onBoardStateRepository: OnboardStateRepository,
    private val appPreferencesRepository: AppPreferencesRepository
) : ViewModel(), DefaultLifecycleObserver {

    var onboardState = false

    init {
        viewModelScope.launch {
            onBoardStateRepository.getOnboardState().observeForever{
                onboardState = it
            }
        }
        getUserTheme()
    }

    private lateinit var _usertTheme: LiveData<MyAppTheme>
    var userTheme: LiveData<MyAppTheme> = _usertTheme

    fun saveUserOnboarding(onboardState: Boolean) = viewModelScope.launch {
        onBoardStateRepository.updateOnboardState(onboardState)
    }

    fun clearOnboardState() = viewModelScope.launch {
        onBoardStateRepository.clearOnboardState()
    }

    private fun getUserTheme() = viewModelScope.launch {
        _usertTheme = appPreferencesRepository.getTheme()
    }

    fun saveUserTheme(theme: AppTheme) = viewModelScope.launch {
        appPreferencesRepository.setTheme(theme)
    }

    fun resetUserTheme() = viewModelScope.launch {
        appPreferencesRepository.resetTheme()
    }
}