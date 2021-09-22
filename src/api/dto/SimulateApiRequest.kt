package api.dto

import api.vo.Command

data class SimulateApiRequest(
    val commands: List<Command>
)
