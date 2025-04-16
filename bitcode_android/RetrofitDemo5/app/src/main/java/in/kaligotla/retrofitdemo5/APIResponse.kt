package `in`.kaligotla.retrofitdemo5

data class APIResponse(
    var products: ArrayList<Product>,
    var total: Int,
    var skip: Int,
    var limit: Int
)
