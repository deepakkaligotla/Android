package `in`.kaligotla.mvvmdemo1.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userOwnerId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Profile(
    @PrimaryKey val profileId: Int,
    val bio: String,
    val occupation: String,
    val location: String,
    val userOwnerId: Int
)