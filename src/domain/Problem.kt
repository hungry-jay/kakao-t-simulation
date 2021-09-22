package domain

import api.RestApi
import api.dto.SimulateApiRequest
import api.dto.StartApiRequest
import api.vo.Command

object Problem {
    private const val STATUS_READY = "ready"

    fun doProblem(scenario: Int) {
        var result = ""
        val startApiResponse = RestApi.startApi(StartApiRequest(problem = scenario))
        val authKey = startApiResponse.auth_key

        do {
            val simulateApiRequest = SimulateApiRequest(listOf(
                Command(truck_id = 0, command = listOf()),
            ))
            val eachResult = RestApi.simulateApi(simulateApiRequest, authKey)
            result = eachResult.status
        } while (result == STATUS_READY)
        println(RestApi.scoreApi(authKey).score)
    }
}