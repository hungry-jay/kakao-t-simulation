package api.dto

import api.vo.Location

data class LocationsApiResponse(
    val locations: List<Location>
) {
    fun toMap(n: Int): Array<Array<Location>> {
        val locationMap = Array(n) { Array(n) { this.locations[0] } }

        var i = n - 1
        var j = 0
        this.locations.forEach {
            locationMap[i][j] = it
            i--
            if(i == -1) {
                j++
                i = n - 1
            }
        }
        return locationMap
    }
}
