package `in`.kaligotla.myanimations

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(): ViewModel() {
    fun runMyChallenge() {
        viewModelScope.launch {
            coroutineScope {
                launch {
                    Log.e("Task 2", "dad - Task 2")
                }
                Log.e("Task 1", "parentsOf(dad) - Task 1")
            }
            supervisorScope {
                launch {
                    Log.e("Task 4", "mom - 4")
                }
                Log.e("Task 3", "parentsOf(mom) - Task 3")
            }
            GlobalScope.launch {
                launch {
                    Log.e("Task 5", "grandChild - Task 5")
                }
                Log.e("Task 6", "child - Task 6")
            }
            launch { Log.e("Task 7", "wifeOf(child) - Task 7") }
        }
    }
}