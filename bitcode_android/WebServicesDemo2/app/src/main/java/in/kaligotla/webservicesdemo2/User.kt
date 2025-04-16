package `in`.kaligotla.webservicesdemo2

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class User(
    var id: Int,
    var email: String,
    @SerializedName("first_name")
    var first_name: String,
    @SerializedName("last_name")
    var last_name: String,
    var avatar: String): Serializable
