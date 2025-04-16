package `in`.kaligotla.webservicesdemo

data class Model(
    var page: Int,
    var per_page: Int,
    var total: Int,
    var data: ArrayList<User>,
    var support: Support
)
