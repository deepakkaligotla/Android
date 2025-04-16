package `in`.kaligotla.recyclerview3

import java.io.Serializable
import java.time.LocalDate

data class User(
    var id: Int,
    var name: String,
    var dob: LocalDate
): Serializable
