package `in`.kaligotla.allpermissions.presentation.main.permissions.location

import android.content.Context
import `in`.kaligotla.allpermissions.data.domain.model.entities.LocationDetails
import kotlinx.coroutines.flow.StateFlow

interface MyLocationViewModel {
    var locations: StateFlow<List<LocationDetails>>
    fun getLocationLive(context: Context)
}