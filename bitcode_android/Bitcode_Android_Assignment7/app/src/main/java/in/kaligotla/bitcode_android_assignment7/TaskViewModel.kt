package `in`.kaligotla.bitcode_android_assignment7

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository = TaskRepository(application)
    val allTasks: LiveData<List<Task>> = repository.allTasks

    fun insert(task: Task) {
        repository.insert(task)
    }
    fun getTaskById(id: Int): LiveData<Task> {
        return repository.getTaskById(id)
    }
    fun deleteTaskById(id: Int) {
        TaskDatabase.databaseWriteExecutor.execute {
            repository.deleteTaskById(id)
        }
    }
}