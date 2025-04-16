package `in`.kaligotla.datastructures.presentation.main.mySort.myQuickSort

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.data.repository.LocationRepository
import javax.inject.Inject

@HiltViewModel
class MyQuickSortViewModel @Inject constructor(
    private val repo: LocationRepository
) : ViewModel()