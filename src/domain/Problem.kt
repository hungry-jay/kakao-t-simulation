package domain

import api.RestApi
import api.dto.SimulateApiRequest
import api.dto.StartApiRequest
import api.vo.Command

class Problem(
    val scenario: Int,
    val n: Int,
    val truckCount: Int,
) {
    private val trucks: MutableList<Truck> = mutableListOf()
    private val commandTerm = arrayOf(0, 6, 12, 18, 24, 30, 36, 42, 48, 54)
    var serverStatus = ""

    fun doProblem() {
        val authKey = getAuthKey()
        val commands = makeCommands()
        initTrucks(authKey)
        do {
            val locations = RestApi.locationsApi(authKey).toMap(n)
            commandTerm.forEach { _ ->
                trucks.forEach {
                    it.findCurrentTask(locations, commands)
                }
            }
            val eachResult = RestApi.simulateApi(makeSimulateApiRequest(commands), authKey)
            serverStatus = eachResult.status
            println(eachResult)
        } while (serverStatus == SERVER_STATUS_READY)

        println(RestApi.scoreApi(authKey).score)
    }

    private fun initTrucks(authKey: String) =
        RestApi.trucksApi(authKey).trucks.forEach {
            trucks.add(it.toEntity())
        }

    private fun getAuthKey() = RestApi.startApi(StartApiRequest(problem = scenario)).auth_key

    private fun makeCommands(): List<Command> = trucks.map { Command(it.id, listOf()) }

    private fun makeSimulateApiRequest(commands: List<Command>) = SimulateApiRequest(commands)

    companion object {
        const val SERVER_STATUS_READY = "ready"
    }
}