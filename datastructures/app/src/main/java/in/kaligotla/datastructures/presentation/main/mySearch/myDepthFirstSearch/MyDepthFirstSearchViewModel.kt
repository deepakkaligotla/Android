package `in`.kaligotla.datastructures.presentation.main.mySearch.myDepthFirstSearch

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.data.repository.LocationRepository
import javax.inject.Inject

@HiltViewModel
class MyDepthFirstSearchViewModel @Inject constructor(
    private val repo: LocationRepository
) : ViewModel()