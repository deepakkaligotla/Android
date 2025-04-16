package `in`.kaligotla.retrofitdemo1

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    var page: Int,
    @SerializedName("per_page")
    var perPage: Int,
    var total: Int,
    @SerializedName("total_pages")
    var totalPages: Int,
    @SerializedName("data")
    var users: ArrayList<User>,
    var support: Support
)

data class User(
    var id: Int,
    var email: String,
    @SerializedName("first_name")
    var firstName: String,
    @SerializedName("last_name")
    var lastName: String,
    var avatar: String
)

data class NewUser(
    var email: String,
    var password: String
)

data class PostResponseForRegisterUser(
    var id: Int,
    var token: String
)

data class UpdateUser(
    var name: String,
    var job: String
)

data class PutResponseForUpdateUser(
    var name: String,
    var job: String,
    var updatedAt: String
)

data class Support(
    var url: String,
    var text: String
)
