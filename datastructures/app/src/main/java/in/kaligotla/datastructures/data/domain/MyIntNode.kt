package `in`.kaligotla.datastructures.data.domain


class MyIntNode(
    var data: Entry,
    var next: MyIntNode?,
    var prev: MyIntNode?
) {
    constructor(value: Entry) : this(value, null, null)
}