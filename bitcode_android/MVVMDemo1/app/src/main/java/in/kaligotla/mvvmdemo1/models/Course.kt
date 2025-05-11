package `in`.kaligotla.mvvmdemo1.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey val courseId: Int,
    val courseName: String,
    val description: String,
    val credits: Int
)
