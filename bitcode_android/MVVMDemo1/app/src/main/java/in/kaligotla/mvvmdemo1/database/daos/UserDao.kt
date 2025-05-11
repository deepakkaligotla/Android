package `in`.kaligotla.mvvmdemo1.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import `in`.kaligotla.mvvmdemo1.database.relationships.UserAndProfile
import `in`.kaligotla.mvvmdemo1.database.relationships.UserWithProfileAndPosts
import `in`.kaligotla.mvvmdemo1.models.Profile
import `in`.kaligotla.mvvmdemo1.models.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: Profile)

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    suspend fun getUserAndProfile(userId: Int): UserAndProfile?

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    suspend fun getUserWithProfileAndPosts(userId: Int): UserWithProfileAndPosts?
}