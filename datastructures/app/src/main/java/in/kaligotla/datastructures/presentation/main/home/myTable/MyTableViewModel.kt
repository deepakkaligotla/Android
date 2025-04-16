package `in`.kaligotla.datastructures.presentation.main.home.myTable

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.core.Resource
import `in`.kaligotla.datastructures.data.domain.model.entities.Location
import `in`.kaligotla.datastructures.data.repository.LocationRepository
import javax.inject.Inject

@HiltViewModel
class MyTableViewModel @Inject constructor(
    private val repo: LocationRepository
) : ViewModel() {
    val locationsFromMongo = repo.bindGetAllLocationsFromMongo()
    val locationsFromSQLite = repo.bindGetAllLocationsFromSQLite()
    fun getLocationByPincode(pincode: Int) = repo.bindGetLocationByPincodeFromSQLite(pincode)
    fun getLocationByID(ID: Int) = repo.bindGetLocationByIDFromSQLite(ID)
    var locationsList by mutableStateOf(emptyList<Location>())
    var locationsSearchList by mutableStateOf(emptyList<Location>())

    fun setupObservers(lifecycleOwner: LifecycleOwner) {
        locationsFromSQLite.observe(lifecycleOwner, Observer {
            locationsList = it
        })

        if (locationsList.size != 155599) {
            locationsFromMongo.observe(lifecycleOwner, Observer {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        if (!it.data.isNullOrEmpty()) {
                            locationsList = it.data
                        }
                    }

                    Resource.Status.ERROR ->
                        Log.e("error", "error")

                    Resource.Status.LOADING ->
                        if (!it.data.isNullOrEmpty()) {
                            locationsList = it.data
                        }
                }
            })
        }
    }

    fun searchPincode(searchPincode: String, lifecycleOwner: LifecycleOwner) {
        if (searchPincode.isDigitsOnly() && searchPincode != "" && searchPincode != null) {
            locationsList.toMutableList().clear()
            getLocationByPincode(searchPincode.toInt()).observe(lifecycleOwner, Observer {
                locationsSearchList = it
            })
        }
    }

    fun searchID(searchID: String, lifecycleOwner: LifecycleOwner) {
        locationsList.toMutableList().clear()
        if (searchID.isDigitsOnly() && searchID != "" && searchID != null && searchID.toInt() < locationsList.size) {
            getLocationByID(searchID.toInt()).observe(lifecycleOwner, Observer {
                locationsSearchList = listOf(it)
            })
        }
    }
}
