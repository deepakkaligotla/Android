package `in`.kaligotla.mvvmdemo1.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userId: Int,
    val name: String,
    val email: String
)