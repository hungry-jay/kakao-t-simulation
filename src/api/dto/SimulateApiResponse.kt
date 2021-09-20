package api.dto

data class SimulateApiResponse(
    val status: String,
    val time: Int,
    val failed_requests_count: Int,
    val distance: Float,
)
