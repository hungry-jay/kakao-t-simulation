package domain

import api.RestApi
import api.dto.SimulateApiRequest
import api.dto.StartApiRequest
import api.dto.StartApiResponse
import api.vo.Command
import api.vo.Truck

class Problem {
    var authKey: String = ""

    fun problem(args: Array<String>) {
//        var map: List<LongArray>? = Array(N) {
//            LongArray(
//                N
//            )
//        }
//        var trucks: Array<Truck?> = arrayOfNulls<Truck>(TRUCK_COUNT)
        var result = "ready"
        val restApi = RestApi()
        val startApiResponse = restApi.startApi(StartApiRequest(problem = 1))
        authKey = startApiResponse.auth_key

        while (result == "ready") {
            val trucks = restApi.trucksApi(authKey)
            val locations = restApi.locationsApi(authKey)
            for (time in 0..9) {
//                for (t in trucks.indices) {
//                    b = trucks[t].findNotEnough(CYCLE_REQUEST_MEAN, N, map)
//                    if (!b) trucks[t].findExceeding(CYCLE_REQUEST_MEAN, N, map)
//                }
            }
//            trucks.trucks.map {
//                println(it)
//            }
            val simulateApiRequest = SimulateApiRequest(listOf(
                Command(truck_id = 0, command = listOf()),
                Command(1, listOf(0, 0, 0, 0, 0)),
                Command(2, listOf(0, 0, 0, 0, 0)),
                Command(3, listOf(0, 0, 0, 0, 0)),
                Command(4, listOf(0, 0, 0, 0, 0)),
            ))
            val eachResult = restApi.simulateApi(simulateApiRequest, authKey)
            result = eachResult.status
            println(eachResult)
        }
        println(restApi.scoreApi(authKey).score)
    }

    companion object {
        const val N = 60
        const val TRUCK_COUNT = 10
        const val CYCLE_REQUEST_MEAN = 2
    }
}