package `in`.kaligotla.datastructures.presentation.main.mySort.myInsertionSort

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
class MyInsertionSortViewModel @Inject constructor(
    private val repo: LocationRepository
) : ViewModel() {
    private val locationsFromSQLite = repo.bindGetAllLocationsFromSQLite()
    var items by mutableStateOf(emptyList<Location>().toMutableList())
    var locationsList by mutableStateOf(emptyList<Location>())

    fun setupObservers(lifecycleOwner: LifecycleOwner) {
        locationsFromSQLite.observe(lifecycleOwner, Observer {
            locationsList = it
        })
        for (location in locationsList) {
            items.add(location)
            if (location.location_id == 10000) break
        }
    }

    fun sortByPincode() {
        for (i in 1 until items.size) {
            val key = items[i]
            var j = i - 1
            while (j >= 0 && items[j].pincode > key.pincode) {
                items[j + 1] = items[j]
                j -= 1
            }
            items[j + 1] = key
        }
    }
}