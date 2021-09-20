package util

import com.google.gson.GsonBuilder
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
        token: String,
        body: Map<String, Any>,
        doesInput: Boolean = false,
        doesOutput: Boolean = false,
    ): String {
        val connection: HttpURLConnection?

        //URL 설정
        val url = URL(urlString)
        connection = url.openConnection() as HttpURLConnection
        try {
            initConnection(connection, httpMethod, token, doesInput, doesOutput)
            if(body.isNotEmpty()) loadBody(connection, transformBodyToString(body))

            return if (connection.responseCode == HttpURLConnection.HTTP_OK) {
                val bufferedReader = BufferedReader(InputStreamReader(connection.inputStream, "UTF-8"))
                val res = StringBuilder()
                var inputLine: String?
                while (bufferedReader.readLine().also { inputLine = it } != null) {
                    res.append(inputLine)
                }

                res.toString()
            } else {
                println("Error: didn't got 200 http status")
                connection.responseCode.toString()
            }
        } catch (e: MalformedURLException) {
            println(e)
            e.printStackTrace()
        } catch (e: IOException) {
            val br =  BufferedReader(InputStreamReader(connection.errorStream, "UTF-8"))
            var errorLine: String?
            while (br.readLine().also { errorLine = it } != null) {
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
        token: String,
        doesInput: Boolean,
        doesOutput: Boolean,
    ) = connection.apply {
        requestMethod = httpMethod
        setRequestProperty("X-Auth-Token", token)
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

    private fun transformBodyToString(body: Map<String, Any>): String =
        GsonBuilder().setPrettyPrinting().create().toJson(body)

    private fun loadBody(connection: HttpURLConnection, bodyJson: String) =
        BufferedWriter(OutputStreamWriter(connection.outputStream, "UTF-8"))
            .run {
                write(bodyJson)
                flush()
                close()
            }
}