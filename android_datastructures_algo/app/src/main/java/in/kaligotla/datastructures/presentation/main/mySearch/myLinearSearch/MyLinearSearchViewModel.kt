package `in`.kaligotla.datastructures.presentation.main.mySearch.myLinearSearch

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.data.domain.model.entities.Location
import `in`.kaligotla.datastructures.data.repository.LocationRepository
import javax.inject.Inject


@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class MyLinearSearchViewModel @Inject constructor(
    private val repo: LocationRepository
) : ViewModel() {
    private val locationsFromSQLite = repo.bindGetAllLocationsFromSQLite()
    var items by mutableStateOf(emptyList<Location>().toMutableList())
    var locationsList by mutableStateOf(emptyList<Location>())

    fun setupObservers(lifecycleOwner: LifecycleOwner) {
        locationsFromSQLite.observe(lifecycleOwner, Observer {
            locationsList = it
        })
        items = locationsList.toMutableList()
    }

    fun linearSearchRec(key: Int, index: Int, lifecycleOwner: LifecycleOwner, context: Context) {
        val workManager = WorkManager.getInstance(context)

        val request: WorkRequest =
            OneTimeWorkRequest.Builder(LinearSearchWorker::class.java)
                .setInputData(
                    Data.Builder().putInt("KEY_PINCODE", key).putInt("INDEX", index).build()
                )
                .build()

        workManager.enqueue(request)

        workManager.getWorkInfoByIdLiveData(request.id)
            .observe(lifecycleOwner, Observer<WorkInfo> {
                it
                when (it.state) {
                    WorkInfo.State.ENQUEUED -> {
                        Log.e("Work started", "" + it.state)
                    }

                    WorkInfo.State.RUNNING -> {
                        Log.e("Work is running", "" + it.state)
                    }

                    WorkInfo.State.SUCCEEDED -> {
                        Log.e("Work done", "" + it.outputData.getString("items"))
                    }

                    WorkInfo.State.FAILED -> {
                        Log.e("Work failed", "" + it.state)
                    }

                    WorkInfo.State.BLOCKED -> {
                        Log.e("Work blocked by another", "" + it.state)
                    }

                    WorkInfo.State.CANCELLED -> {
                        Log.e("Work cancelled", "" + it.state)
                    }

                    else -> {}
                }
            })
    }
}

