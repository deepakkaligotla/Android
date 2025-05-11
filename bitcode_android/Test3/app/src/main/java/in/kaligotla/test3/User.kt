package `in`.kaligotla.test3

import java.io.Serializable

data class User(
    var id: Int,
    var username: String,
    var fullName: String
): Serializable
