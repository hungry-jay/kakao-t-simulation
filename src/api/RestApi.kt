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

object RestApi {
    private const val BASE_URL = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users"
    private const val X_AUTH_TOKEN = "d42062ee8ac5da45a55d78ca5461aec3"
    private const val POST = "POST"
    private const val GET = "GET"
    private const val PUT = "PUT"

    fun startApi(request: StartApiRequest): StartApiResponse =
        HttpUtil.callApi(
            httpMethod = POST,
            urlString = "$BASE_URL/start",
            token = X_AUTH_TOKEN,
            body = Gson().toJson(request),
            doesInput = true,
            doesOutput = true,
        ).let { Gson().fromJson(it, StartApiResponse::class.java) }


    fun locationsApi(authKey: String): LocationsApiResponse =
        HttpUtil.callApi(
            httpMethod = GET,
            urlString = "$BASE_URL/locations",
            authKey = authKey,
            doesInput = true,
            doesOutput = false,
        ).let { Gson().fromJson(it, LocationsApiResponse::class.java) }

    fun trucksApi(authKey: String): TrucksApiResponse =
        HttpUtil.callApi(
            httpMethod = GET,
            urlString = "$BASE_URL/trucks",
            authKey = authKey,
            doesInput = true,
            doesOutput = true,
        ).let { Gson().fromJson(it, TrucksApiResponse::class.java) }

    fun simulateApi(request: SimulateApiRequest, authKey: String): SimulateApiResponse =
        HttpUtil.callApi(
            httpMethod = PUT,
            urlString = "$BASE_URL/simulate",
            authKey = authKey,
            body = Gson().toJson(request),
            doesInput = true,
            doesOutput = true,
        ).let { Gson().fromJson(it, SimulateApiResponse::class.java) }

    fun scoreApi(authKey: String): ScoreApiResponse =
        HttpUtil.callApi(
            httpMethod = GET,
            urlString = "$BASE_URL/score",
            authKey = authKey,
            doesInput = true,
            doesOutput = false,
        ).let { Gson().fromJson(it, ScoreApiResponse::class.java) }
}
