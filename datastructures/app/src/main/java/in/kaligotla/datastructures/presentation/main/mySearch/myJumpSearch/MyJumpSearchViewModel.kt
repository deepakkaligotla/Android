package `in`.kaligotla.datastructures.presentation.main.mySearch.myJumpSearch

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.data.repository.LocationRepository
import javax.inject.Inject

@HiltViewModel
class MyJumpSearchViewModel @Inject constructor(
    private val repo: LocationRepository
) : ViewModel()