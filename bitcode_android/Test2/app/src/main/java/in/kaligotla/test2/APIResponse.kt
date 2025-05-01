package `in`.kaligotla.test2

import java.io.Serializable

data class APIResponse(
    val todos: ArrayList<ToDo>,
    var total: Int,
    var skip: Int,
    var limit: Int
): Serializable
