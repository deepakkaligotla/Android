package `in`.kaligotla.mvvmdemo1.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey val studentId: Int,
    val studentName: String,
    val email: String,
    val phoneNumber: String
)
