package domain

import api.vo.Command
import api.vo.Location

class Truck(
    id: Int,
    location_id: Int,
    loaded_bikes_count: Int,
) {
    val id: Int = id
    private val locationId: Int = location_id
    private val loadedBikesCount: Int = loaded_bikes_count

    fun findCurrentTask(locations: Array<Array<Location>>, commands: List<Command>) {
        TODO()
    }
}
