package `in`.kaligotla.allpermissions.data.repository.permission

import androidx.lifecycle.LiveData
import `in`.kaligotla.allpermissions.core.LoadDataService
import `in`.kaligotla.allpermissions.core.Resource
import `in`.kaligotla.allpermissions.data.domain.model.entities.Permission

typealias permissionsDataFromSQLite = LiveData<List<Permission>>
typealias permissionDataFromSQLite = LiveData<Permission>
typealias permissionCountFromSQLite = LiveData<Int>

interface PermissionRepository {
    val loadDataFromService: LoadDataService
    suspend fun setAllPermissionsToSQLite(permissionsList: List<Permission>)
    fun bindGetAllPermissionsFromSQLite(): permissionsDataFromSQLite
    fun getPermissionCountFromSQLite(): permissionCountFromSQLite
    fun bindGetPermissionByIDFromSQLite(id: Int): permissionDataFromSQLite
    suspend fun updatePermissionByIDToSQLite(permission: Permission)
}