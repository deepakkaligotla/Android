package `in`.kaligotla.bitcode_android_assignment7

import android.app.Application;
import androidx.lifecycle.LiveData;

class TaskRepository(application: Application) {
    private val taskDao: TaskDao
    val allTasks: LiveData<List<Task>>

    init {
        val db = TaskDatabase.getDatabase(application)
        taskDao = db.taskDao()
        allTasks = taskDao.getAllTasks()
    }

    fun insert(task: Task) {
        TaskDatabase.databaseWriteExecutor.execute {
            taskDao.addNewTask(task)
        }
    }

    fun getTaskById(id: Int): LiveData<Task> {
        return taskDao.getTaskById(id)
    }

    fun deleteTaskById(id: Int) {
        taskDao.deleteTaskById(id)
    }
}