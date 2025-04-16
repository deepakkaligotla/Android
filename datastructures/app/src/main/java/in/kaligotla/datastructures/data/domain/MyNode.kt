package `in`.kaligotla.datastructures.data.domain

import `in`.kaligotla.datastructures.data.domain.model.entities.Location

class MyNode(
    var data: Location?,
    var next: MyNode?
) {
    constructor(value: Location?) : this(value, null)
}