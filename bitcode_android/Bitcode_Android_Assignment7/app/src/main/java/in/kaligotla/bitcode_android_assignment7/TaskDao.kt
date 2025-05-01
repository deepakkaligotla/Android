package `in`.kaligotla.bitcode_android_assignment7

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNewTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("Select * from Task order by task_name")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("select * from Task where id = :id")
    fun getTaskById(id : Int) : LiveData<Task>

    @Query("delete from Task where id = :id")
    fun deleteTaskById(id : Int) : Int

    @Query("delete from Task")
    fun deleteAllTask() : Int
}