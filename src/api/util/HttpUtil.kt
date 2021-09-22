package api.util

import com.google.gson.JsonParseException
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

object HttpUtil {
    fun callApi(
        httpMethod: String,
        urlString: String,
        token: String? = null,
        authKey: String? = null,
        body: String? = null,
        doesInput: Boolean = false,
        doesOutput: Boolean = false,
    ): String {
        val connection: HttpURLConnection?

        val url = URL(urlString)
        connection = url.openConnection() as HttpURLConnection
        try {
            initConnection(connection, httpMethod, token, authKey, doesInput, doesOutput)
            if (body != null) loadBody(connection, body)

            return if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream, "UTF-8"))
                val response = StringBuilder()
                var inputLine: String?
                while (bufferedReader.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }

                response.toString()
            } else {
                println("[Http response]: ${connection.responseCode}: ${connection.responseMessage}")
                connection.responseCode.toString()
            }
        } catch (e: MalformedURLException) {
            println(e)
            e.printStackTrace()
        } catch (e: IOException) {
            val bufferedReader =  BufferedReader(InputStreamReader(connection.errorStream, "UTF-8"))
            var errorLine: String?
            while (bufferedReader.readLine().also { errorLine = it } != null) {
                println(errorLine)
            }
            println(e)
            e.printStackTrace()
        } catch (e: JsonParseException) {
            println("Not JSON Format response")
            e.printStackTrace()
        }
        return "Error: Should not reach here"
    }

    private fun initConnection(
        connection: HttpURLConnection,
        httpMethod: String,
        token: String?,
        authKey: String?,
        doesInput: Boolean,
        doesOutput: Boolean,
    ) = connection.apply {
        requestMethod = httpMethod
        if(!token.isNullOrBlank()) setRequestProperty("X-Auth-Token", token)
        if(!authKey.isNullOrBlank()) setRequestProperty("Authorization", authKey)
        setRequestProperty("Content-Type", "application/json")
        setRequestProperty("Accept-Charset", "UTF-8")
        setRequestProperty("Transfer-Encoding", "chunked")
        setRequestProperty("Connection", "keep-alive")
        connectTimeout = 10000
        readTimeout = 10000
        doInput = doesInput
        doOutput = doesOutput
        useCaches = false
        defaultUseCaches = false
    }

    private fun loadBody(connection: HttpURLConnection, body: String) =
        BufferedWriter(OutputStreamWriter(connection.outputStream, "UTF-8"))
            .run {
                write(body)
                flush()
                close()
            }
}
