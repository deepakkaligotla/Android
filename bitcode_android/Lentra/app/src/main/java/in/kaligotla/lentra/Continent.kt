package `in`.kaligotla.lentra

data class Continent(
    var code: String,
    var name: String,
    var areaSqKm: Long,
    var population: Long,
    var lines: Array<String>,
    var countries: Int,
    var oceans: Array<String>,
    var developedCountries:  Array<String>
)
