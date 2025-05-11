package `in`.kaligotla.mvvmdemo1.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import `in`.kaligotla.mvvmdemo1.models.Post
import `in`.kaligotla.mvvmdemo1.models.Profile
import `in`.kaligotla.mvvmdemo1.models.User

data class UserWithProfileAndPosts(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val profile: Profile,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val posts: List<Post>
)
