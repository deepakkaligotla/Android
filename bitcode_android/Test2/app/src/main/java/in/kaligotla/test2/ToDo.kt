package `in`.kaligotla.test2

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ToDo(
    var id: Int,
    var userId: Int,
    var todo: String,
    @SerializedName("completed")
    var status: Boolean
): Serializable
