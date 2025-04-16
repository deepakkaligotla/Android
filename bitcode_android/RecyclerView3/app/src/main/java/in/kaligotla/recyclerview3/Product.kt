package `in`.kaligotla.recyclerview3

import java.io.Serializable

data class Product(
    var id: Int,
    var title: String,
    var price: Double
) : Serializable
