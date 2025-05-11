package `in`.kaligotla.mvvmdemo1.database.relationships

import androidx.room.Embedded
import androidx.room.Relation
import `in`.kaligotla.mvvmdemo1.models.Profile
import `in`.kaligotla.mvvmdemo1.models.User

data class UserAndProfile(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    val profile: Profile
)
