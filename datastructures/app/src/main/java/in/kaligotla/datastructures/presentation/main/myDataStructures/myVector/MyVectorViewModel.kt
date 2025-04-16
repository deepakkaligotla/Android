package `in`.kaligotla.datastructures.presentation.main.myDataStructures.myVector

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import `in`.kaligotla.datastructures.data.domain.Entry
import `in`.kaligotla.datastructures.data.repository.LocationRepository
import java.util.Vector
import javax.inject.Inject

@HiltViewModel
class MyVectorViewModel @Inject constructor(
    private val repo: LocationRepository,
) : ViewModel() {
    var vectorItems by mutableStateOf(Vector<Entry>())
    var arrayItems by mutableStateOf(emptyArray<Entry?>())

    fun createArray(size: Int) {
        arrayItems = Array<Entry?>(size) { null }
    }

    fun addToArray(value: Entry) {
        arrayItems[value.key!!] = value
        for (item in arrayItems) Log.e("array items", "" + item)
    }

    fun addToVector(value: Entry) {
        vectorItems.add(value)
        Log.e("vector items", "" + vectorItems)
    }
}