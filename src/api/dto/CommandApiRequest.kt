package api.dto

import api.vo.Command

data class CommandApiRequest(
    val commands: List<Command>
)
