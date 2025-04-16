package `in`.kaligotla.allpermissions.data.repository.permission

import android.os.Build
import androidx.annotation.RequiresApi
import `in`.kaligotla.allpermissions.core.LoadDataService
import `in`.kaligotla.allpermissions.core.performGetOperation
import `in`.kaligotla.allpermissions.data.domain.model.entities.Permission
import `in`.kaligotla.allpermissions.data.local.PermissionDao
import `in`.kaligotla.allpermissions.data.remote.PermissionRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@RequiresApi(Build.VERSION_CODES.Q)
@Singleton
class PermissionRepositoryImpl @Inject constructor(
    loadDataService: LoadDataService,
    private val remoteDataSource: PermissionRemoteDataSource,
    private val localDataSource: PermissionDao
) : PermissionRepository {
    override val loadDataFromService: LoadDataService = loadDataService

    override suspend fun setAllPermissionsToSQLite(permissionsList: List<Permission>) {
        localDataSource.insertAll(permissionsList)
    }

    override fun bindGetAllPermissionsFromSQLite(): permissionsDataFromSQLite {
        return localDataSource.getPermissions()
    }

    override fun getPermissionCountFromSQLite(): permissionCountFromSQLite {
        return localDataSource.getPermissionCount()
    }

    override fun bindGetPermissionByIDFromSQLite(pincode: Int): permissionDataFromSQLite {
        return localDataSource.getPermissionById(pincode)
    }

    override suspend fun updatePermissionByIDToSQLite(permission: Permission) {
        localDataSource.update(permission)
    }
}