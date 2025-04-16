package `in`.kaligotla.datastructures.data.domain

import `in`.kaligotla.datastructures.data.domain.model.entities.Location

class MyNode2(
    var data: Location?,
    var next: MyNode2?,
    var prev: MyNode2?
) {
    constructor(value: Location?) : this(value, null, null)
}