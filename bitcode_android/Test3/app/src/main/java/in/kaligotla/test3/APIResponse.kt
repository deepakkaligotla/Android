package `in`.kaligotla.test3

import java.io.Serializable

data class APIResponse(
    var comments: ArrayList<Comment>,
    var total: Int,
    var skip: Int,
    var limit: Int
): Serializable
