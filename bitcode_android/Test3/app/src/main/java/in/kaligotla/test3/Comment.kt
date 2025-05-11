package `in`.kaligotla.test3

import java.io.Serializable

data class Comment(
    var id: Int,
    var body: String,
    var postId: Int,
    var likes: Int,
    var user: User
): Serializable
