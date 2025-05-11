package `in`.kaligotla.mvvmdemo1.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import `in`.kaligotla.mvvmdemo1.models.Post
import `in`.kaligotla.mvvmdemo1.models.User

data class PostWithUser(
    @Embedded val post: Post,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val user: User
)
