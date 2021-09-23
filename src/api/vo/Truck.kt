package api.vo

import domain.Truck

data class Truck(
    val id: Int,
    val location_id: Int,
    val loaded_bikes_count: Int,
) {
    fun toEntity() = Truck(
        id = this.id,
        location_id = this.location_id,
        loaded_bikes_count = this.loaded_bikes_count,
    )
}
