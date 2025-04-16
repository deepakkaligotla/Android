package `in`.kaligotla.datastructures.data.proto

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import `in`.kaligotla.datastructures.proto.OnboardState
import java.io.IOException
import javax.inject.Inject

class OnboardStateRepositoryImpl @Inject constructor(
    context: Context, private var onboardStateDataStore: DataStore<OnboardState>
) : OnboardStateRepository {

//    private var appContext = context.applicationContext
//    private val sharedPreferences =
//        appContext.getSharedPreferences("user_preferences", Context.MODE_PRIVATE)

    override suspend fun getOnboardState(): LiveData<Boolean> {
        return onboardStateDataStore.data.asLiveData()
            .switchMap { MutableLiveData(it.isOnboardedState) }
    }

    override suspend fun updateOnboardState(newOnboardState: Boolean) {
        try {
            onboardStateDataStore.updateData {
                it.toBuilder().setIsOnboardedState(newOnboardState).build()
            }
        } catch (e: IOException) {
            Log.e("updateOnboardState catch", "" + e)
        }
    }

    override suspend fun clearOnboardState() {
        onboardStateDataStore.updateData {
            it.toBuilder().clearIsOnboardedState().build()
        }
    }

    override suspend fun resetData() {
        onboardStateDataStore.updateData {
            OnboardState.getDefaultInstance()
        }
    }
}