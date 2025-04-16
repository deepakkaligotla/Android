package `in`.kaligotla.datastructures.data.domain.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "location")
data class Location(
    @PrimaryKey
    @field:SerializedName("location_id")
    var location_id: Int,

    @field:SerializedName("_id")
    val _id: String,

    @field:SerializedName("pincode")
    val pincode: Int,

    @field:SerializedName("area")
    val area: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("district")
    val district: String,

    @field:SerializedName("state")
    val state: String
)
