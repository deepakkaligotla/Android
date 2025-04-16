package `in`.kaligotla.allpermissions.data.repository.proto

import androidx.lifecycle.LiveData
import `in`.kaligotla.allpermissions.proto.AppTheme
import `in`.kaligotla.allpermissions.proto.MyAppTheme

interface AppPreferencesRepository {
    suspend fun getTheme(): LiveData<MyAppTheme>
    suspend fun setTheme(theme: AppTheme)
    suspend fun resetTheme()
    suspend fun clearThemeState()
}