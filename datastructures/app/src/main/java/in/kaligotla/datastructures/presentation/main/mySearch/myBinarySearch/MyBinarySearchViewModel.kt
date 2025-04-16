package `in`.kaligotla.datastructures.presentation.main.mySearch.myBinarySearch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.data.domain.model.entities.Location
import `in`.kaligotla.datastructures.data.repository.LocationRepository
import javax.inject.Inject

@HiltViewModel
class MyBinarySearchViewModel @Inject constructor(
    private val repo: LocationRepository
) : ViewModel() {

    val locationsFromSQLite = repo.bindGetAllLocationsFromSQLite()
    var locationsList by mutableStateOf(emptyList<Location>())
    var items by mutableStateOf(emptyList<Location>().toMutableList())

    fun setupObservers(lifecycleOwner: LifecycleOwner) {
        locationsFromSQLite.observe(lifecycleOwner, Observer {
            locationsList = it
        })
        items = locationsList.toMutableList()
    }

    fun binarySearch(arr: IntArray, first: Int, last: Int, key: Int) {
        var first = first
        var last = last
        var mid = (first + last) / 2
        while (first <= last) {
            if (arr[mid] < key) {
                first = mid + 1
            } else if (arr[mid] == key) {
                items.clear()
                break
            } else {
                last = mid - 1
            }
            mid = (first + last) / 2
        }
        if (first > last) {
            items.clear()
        }
    }

}