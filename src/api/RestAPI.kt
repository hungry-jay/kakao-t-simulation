package api

import com.google.gson.Gson
import response.StartApiResponse
import util.HttpUtil

class RestAPI {
    fun startApi(): StartApiResponse {
        val body: MutableMap<String, Any> = HashMap()
        body["problem"] = 1

        return HttpUtil.callApi(
            httpMethod = POST,
            urlString = "$BASE_URI/start",
            token = X_AUTH_TOKEN,
            body = body,
            doesInput = true,
            doesOutput = true,
        ).let { Gson().fromJson(it, StartApiResponse::class.java) }
    }

    companion object {
        const val BASE_URI = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users"
        const val X_AUTH_TOKEN = "3f1c83a1dfd14544e6252ca6d7cfffed"
        const val POST = "POST"
        const val GET = "GET"
        const val PUT = "PUT"
        const val DELETE = "DELETE"
        const val PATCH = "PATCH"
        const val HEAD = "HEAD"
    }
}