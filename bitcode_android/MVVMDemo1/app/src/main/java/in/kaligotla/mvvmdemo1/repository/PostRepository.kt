package `in`.kaligotla.mvvmdemo1.repository

import android.util.Log
import `in`.kaligotla.mvvmdemo1.database.AppDatabase
import `in`.kaligotla.mvvmdemo1.network.NetworkService

class PostRepository(
    private val db: AppDatabase,
    private val api: NetworkService
) {

    suspend fun fetchPostWithUser() {
        val postWithUser = api.getPostWithUser()
        Log.e("", postWithUser.toString())
        db.getUserDao().insertUser(postWithUser.user)
        db.getPostDao().insertPost(postWithUser.post)
    }

    suspend fun getPostWithUser(postId: Int) = db.getPostDao().getPostWithUser(postId)
}