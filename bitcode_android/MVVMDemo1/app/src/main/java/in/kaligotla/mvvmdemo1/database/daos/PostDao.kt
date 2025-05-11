package `in`.kaligotla.mvvmdemo1.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import `in`.kaligotla.mvvmdemo1.database.relationships.PostWithUser
import `in`.kaligotla.mvvmdemo1.database.relationships.UserWithPosts
import `in`.kaligotla.mvvmdemo1.models.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: Post)

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    suspend fun getUserWithPosts(userId: Int): UserWithPosts?

    @Transaction
    @Query("SELECT * FROM Post WHERE postId = :postId")
    suspend fun getPostWithUser(postId: Int): PostWithUser?
}