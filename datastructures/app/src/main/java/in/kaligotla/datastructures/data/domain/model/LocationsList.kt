package `in`.kaligotla.datastructures.data.domain.model

import `in`.kaligotla.datastructures.data.domain.model.entities.Location

data class LocationsList(
    val status: String,
    val data: List<Location>
)