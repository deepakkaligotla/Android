package `in`.kaligotla.bitcode_android_assignment8

import java.io.Serializable

data class Product(
    var id: Int,
    var title: String,
    var description: String,
    var price: Double,
    var image: String,
    var rating: Float,
    var ratingCount: Int
) : Serializable
