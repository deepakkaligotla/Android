package `in`.kaligotla.allpermissions.data.repository.proto

import androidx.lifecycle.LiveData

interface OnboardStateRepository {
    suspend fun getOnboardState(): LiveData<Boolean>
    suspend fun updateOnboardState(onboardState: Boolean)
    suspend fun resetData()
    suspend fun clearOnboardState()
}