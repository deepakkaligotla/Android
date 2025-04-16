package `in`.kaligotla.datastructures.presentation.main.mySort.mySelectionSort

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
class MySelectionSortViewModel @Inject constructor(
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
            if (location.location_id == 5000) break
        }
    }

    fun sortByPincode() {
        for (i in 0 until items.size - 1) {
            var index = i
            for (j in i + 1 until items.size)
                if (items[j].pincode < items[index].pincode)
                    index = j
            val temp = items[index]
            items[index] = items[i]
            items[i] = temp
        }
    }
}