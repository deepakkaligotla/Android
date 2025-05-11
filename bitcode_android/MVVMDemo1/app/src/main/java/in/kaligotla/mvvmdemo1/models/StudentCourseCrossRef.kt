package `in`.kaligotla.mvvmdemo1.models

import androidx.room.Entity

@Entity(primaryKeys = ["studentId", "courseId"])
data class StudentCourseCrossRef(
    val studentId: Int,
    val courseId: Int
)
