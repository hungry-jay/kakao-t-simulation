package api

import api.dto.LocationsApiResponse
import api.dto.ScoreApiResponse
import api.dto.SimulateApiRequest
import api.dto.SimulateApiResponse
import api.dto.StartApiRequest
import com.google.gson.Gson
import api.dto.StartApiResponse
import api.dto.TrucksApiResponse
import api.util.HttpUtil

class RestApi {
    fun startApi(request: StartApiRequest): StartApiResponse =
        HttpUtil.callApi(
            httpMethod = POST,
            urlString = "$BASE_URI/start",
            token = X_AUTH_TOKEN,
            body = Gson().toJson(request),
            doesInput = true,
            doesOutput = true,
        ).let { Gson().fromJson(it, StartApiResponse::class.java) }


    fun locationsApi(authKey: String): LocationsApiResponse =
        HttpUtil.callApi(
            httpMethod = GET,
            urlString = "$BASE_URI/locations",
            authKey = authKey,
            doesInput = true,
            doesOutput = false,
        ).let { Gson().fromJson(it, LocationsApiResponse::class.java) }

    fun trucksApi(authKey: String): TrucksApiResponse =
        HttpUtil.callApi(
            httpMethod = GET,
            urlString = "$BASE_URI/trucks",
            authKey = authKey,
            doesInput = true,
            doesOutput = true,
        ).let { Gson().fromJson(it, TrucksApiResponse::class.java) }

    fun simulateApi(request: SimulateApiRequest, authKey: String): SimulateApiResponse =
        HttpUtil.callApi(
            httpMethod = PUT,
            urlString = "$BASE_URI/simulate",
            authKey = authKey,
            body = Gson().toJson(request),
            doesInput = true,
            doesOutput = true,
        ).let { Gson().fromJson(it, SimulateApiResponse::class.java) }

    fun scoreApi(authKey: String): ScoreApiResponse =
        HttpUtil.callApi(
            httpMethod = GET,
            urlString = "$BASE_URI/score",
            authKey = authKey,
            doesInput = true,
            doesOutput = false,
        ).let { Gson().fromJson(it, ScoreApiResponse::class.java) }

    companion object {
        const val BASE_URI = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users"
        const val X_AUTH_TOKEN = "bcb07526e2da65feaf97210ed1fad467"
        const val POST = "POST"
        const val GET = "GET"
        const val PUT = "PUT"
    }
}