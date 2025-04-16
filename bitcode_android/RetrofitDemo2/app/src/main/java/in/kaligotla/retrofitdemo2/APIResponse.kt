package `in`.kaligotla.retrofitdemo2

data class APIResponse(
    var id: Int,
    var title: String,
    var price: Double,
    var description: String,
    var category: String,
    var image: String,
    var rating: Rating
)

data class Rating(
    var rate: Float,
    var count: Int
)
