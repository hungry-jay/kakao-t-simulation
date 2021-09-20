package domain

import api.RestApi
import api.dto.StartApiRequest
import api.dto.StartApiResponse

class Problem {
    var authKey: String = ""

    fun problem(args: Array<String>) {
        var map: Array<LongArray>? = Array(N) {
            LongArray(
                N
            )
        }
//        var trucks: Array<Truck?> = arrayOfNulls<Truck>(TRUCK_COUNT)
        var result = "ready"
        val restApi = RestApi()
        val startApiResponse: StartApiResponse = restApi.startApi(StartApiRequest(1))
        authKey = startApiResponse.auth_key
        println(authKey)

        restApi.locationsApi(authKey)
        val truckResponse = restApi.trucksApi(authKey)
//        println(truckResponse)
//        (truckResponse.trucks.map {
//            println(it)
//        })
//        val simulateResponse = restApi.simulateApi(authKey)
//        println(simulateResponse)
//        val scoreResponse = restApi.scoreApi(authKey)
//        println(scoreResponse)


        while (result == "ready") {
//            map = api.RestAPI.locationsAPI(N, auth_key)
//            trucks = api.RestAPI.trucksAPI(TRUCK_COUNT, auth_key)
            var b: Boolean
            for (time in 0..9) {
//                for (t in trucks.indices) {
//                    b = trucks[t].findNotEnough(CYCLE_REQUEST_MEAN, N, map)
//                    if (!b) trucks[t].findExceeding(CYCLE_REQUEST_MEAN, N, map)
//                }
            }
//            result = api.RestAPI.simulateAPI(trucks, auth_key)
        }
//        api.RestAPI.scoreAPI(auth_key)
    }

    companion object {
        const val N = 60
        const val TRUCK_COUNT = 10
        const val CYCLE_REQUEST_MEAN = 2
    }
}