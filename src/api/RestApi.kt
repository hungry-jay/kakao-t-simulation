package api

import api.dto.LocationsApiResponse
import api.dto.ScoreResponse
import api.dto.SimulateApiResponse
import api.dto.StartApiRequest
import com.google.gson.Gson
import api.dto.StartApiResponse
import api.dto.TrucksApiResponse
import api.util.HttpUtil

class RestApi {
    fun startApi(request: StartApiRequest): StartApiResponse {
        val body: MutableMap<String, Any> = HashMap()
        body["problem"] = request.problem

        return HttpUtil.callApi(
            httpMethod = POST,
            urlString = "$BASE_URI/start",
            token = X_AUTH_TOKEN,
            body = body,
            doesInput = true,
            doesOutput = true,
        ).let { Gson().fromJson(it, StartApiResponse::class.java) }
    }

    fun locationsApi(authKey: String): LocationsApiResponse =
        HttpUtil.callApi(
            httpMethod = GET,
            urlString = "$BASE_URI/locations",
            authKey = authKey,
            body = HashMap(),
            doesInput = true,
            doesOutput = false,
        ).let { Gson().fromJson(it, LocationsApiResponse::class.java) }

    fun trucksApi(authKey: String): TrucksApiResponse =
        HttpUtil.callApi(
            httpMethod = GET,
            urlString = "$BASE_URI/trucks",
            authKey = authKey,
            body = HashMap(),
            doesInput = true,
            doesOutput = true,
        ).let { Gson().fromJson(it, TrucksApiResponse::class.java) }

    fun simulateApi(authKey: String): SimulateApiResponse {
        val body: MutableMap<String, Any> = HashMap()
        // TODO()

        return HttpUtil.callApi(
            httpMethod = PUT,
            urlString = "$BASE_URI/simulate",
            authKey = authKey,
            body = HashMap(),
            doesInput = true,
            doesOutput = true,
        ).let { Gson().fromJson(it, SimulateApiResponse::class.java) }
    }

    fun scoreApi(authKey: String): ScoreResponse =
        HttpUtil.callApi(
            httpMethod = GET,
            urlString = "$BASE_URI/score",
            authKey = authKey,
            body = HashMap(),
            doesInput = true,
            doesOutput = false,
        ).let { Gson().fromJson(it, ScoreResponse::class.java) }

    companion object {
        const val BASE_URI = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users"
        const val X_AUTH_TOKEN = "3f1c83a1dfd14544e6252ca6d7cfffed"
        const val POST = "POST"
        const val GET = "GET"
        const val PUT = "PUT"
    }
}