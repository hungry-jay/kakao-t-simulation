import domain.Problem

fun main() {
    println("Application start")
    Problem(
        scenario = 1,
        n = 5,
        truckCount = 5,
    ).doProblem()
    Problem(
        scenario = 2,
        n = 60,
        truckCount = 10,
    ).doProblem()
}
