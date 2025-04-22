package `in`.kaligotla.bitcode_android_assignment7

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Insert
    fun newTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("Select * from Task order by task_name")
    fun getAllTasks(): List<Task>

    @Query("select * from Task where id = :id")
    fun getTaskById(id : Int) : Task

    @Query("delete from Task where id = :id")
    fun deleteTaskById(id : Int) : Int
}