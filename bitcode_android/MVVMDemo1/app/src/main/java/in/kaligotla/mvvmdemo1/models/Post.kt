package `in`.kaligotla.mvvmdemo1.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey val postId: Int,
    val userId: Int,
    val title: String,
    val body: String,
    val timestamp: String
)




