package `in`.kaligotla.mvvmdemo1.repository

import android.util.Log
import `in`.kaligotla.mvvmdemo1.database.AppDatabase
import `in`.kaligotla.mvvmdemo1.network.NetworkService

class UserRepository(
    private val db: AppDatabase,
    private val api: NetworkService
) {

    suspend fun fetchUserAndProfile() {
        val userAndProfile = api.getUserAndProfile()
        db.getUserDao().insertUser(userAndProfile.user)
        db.getUserDao().insertProfile(userAndProfile.profile)
    }

    suspend fun fetchUserWithPosts() {
        val userWithPosts = api.getUserWithPosts()
        db.getUserDao().insertUser(userWithPosts.user)
        userWithPosts.posts.forEach {
            db.getPostDao().insertPost(it)
        }
    }

    suspend fun fetchUserWithProfileAndPosts() {
        val userDetails = api.getUserWithProfileAndPosts()
        db.getUserDao().insertUser(userDetails.user)
        db.getUserDao().insertProfile(userDetails.profile)
        userDetails.posts.forEach {
            db.getPostDao().insertPost(it)
        }
    }

    suspend fun getUserAndProfile(userId: Int) = db.getUserDao().getUserAndProfile(userId)
    suspend fun getUserWithPosts(userId: Int) = db.getPostDao().getUserWithPosts(userId)
    suspend fun getUserWithProfileAndPosts(userId: Int) = db.getUserDao().getUserWithProfileAndPosts(userId)
}