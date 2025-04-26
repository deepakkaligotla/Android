package `in`.kaligotla.test2

data class APIResponse(
    val todos: ArrayList<ToDo>,
    var total: Int,
    var skip: Int,
    var limit: Int
)
