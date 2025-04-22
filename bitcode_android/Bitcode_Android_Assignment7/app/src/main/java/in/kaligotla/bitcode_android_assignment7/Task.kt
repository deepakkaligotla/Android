package `in`.kaligotla.bitcode_android_assignment7

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "task_name")
    var taskName: String,
    @ColumnInfo(name = "task_status")
    var taskStatus: TaskStatus
)

enum class TaskStatus {
    RUNNING, COMPLETED, PENDING
}
