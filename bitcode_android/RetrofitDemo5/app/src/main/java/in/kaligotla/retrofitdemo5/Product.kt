package `in`.kaligotla.retrofitdemo5

data class Product(
    var id: Int,
    var title: String,
    var description: String,
    var category: String,
    var price: Double,
    var discountPercentage: Float,
    var rating: Float,
    var stock: Int,
    var tags: ArrayList<String>,
    var brand: String,
    var sku: String,
    var weight: Int,
    var dimensions: Dimension,
    var warrantyInformation: String,
    var shippingInformation: String,
    var availabilityStatus: String,
    var reviews: ArrayList<Review>,
    var returnPolicy: String,
    var minimumOrderQuantity: Int,
    var meta: Meta,
    var images: ArrayList<String>,
    var thumbnail: String
)
