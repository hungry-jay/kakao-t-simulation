package api.dto

data class StartApiResponse(
    val auth_key: String,
    val problem: Int,
    val time: Int,
)
