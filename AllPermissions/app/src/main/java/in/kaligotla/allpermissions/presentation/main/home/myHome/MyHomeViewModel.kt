package `in`.kaligotla.allpermissions.presentation.main.home.myHome

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.allpermissions.core.Constants.PERMISSIONS_ARRAY
import `in`.kaligotla.allpermissions.data.domain.model.entities.Permission
import `in`.kaligotla.allpermissions.data.repository.permission.PermissionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyHomeViewModel @Inject constructor(
    private val repo: PermissionRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var permissionsList by mutableStateOf(emptyList<Permission>())
    private var permissionsSearchList by mutableStateOf(emptyList<Permission>())

    init {
         repo.bindGetAllPermissionsFromSQLite().observeForever{
             if(it.isNotEmpty()) permissionsList = it
             else if(it.isNullOrEmpty()) onEvent(PermissionsEvent.SetPermissions)
         }
    }

    private fun onEvent(event: PermissionsEvent) {
        viewModelScope.launch {
            when (event) {
                is PermissionsEvent.SetPermissions -> {
                    Log.e("Permissions","to SQLite")
                    repo.setAllPermissionsToSQLite(PERMISSIONS_ARRAY.toList())
                }
            }
        }
    }
}

sealed class PermissionsEvent {
    object SetPermissions : PermissionsEvent()
}

data class PermissionsState(
    val permissions: List<Permission> = listOf()
)
