package `in`.kaligotla.webservicesdemo2

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Model(
    var page: Int,
    @SerializedName("per_page")
    var perPage: Int,
    var total: Int,
    @SerializedName("total_pages")
    var totalPages: Int,
    var data: ArrayList<User>,
    var support: Support)