package domain

import api.RestAPI
import response.StartApiResponse

class Problem {
    fun problem(args: Array<String>) {
        var map: Array<LongArray>? = Array(N) {
            LongArray(
                N
            )
        }
//        var trucks: Array<Truck?> = arrayOfNulls<Truck>(TRUCK_COUNT)
        var result = "ready"
        val restApi = RestAPI()
        val startApiResponse: StartApiResponse = restApi.startApi()
        println(startApiResponse.auth_key)
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