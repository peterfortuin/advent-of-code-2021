import kotlin.math.absoluteValue
import kotlin.math.roundToInt

fun main() {
    fun part1(input: List<String>): Int {
        val originalPositions = input.first().split(",").map { it.toInt() }
        val bestHorizontalPosition = originalPositions.map { it.toDouble() }.median().roundToInt()

        val fuelUsed = originalPositions.fold(0) { acc, next ->
            acc + (bestHorizontalPosition - next).absoluteValue
        }

        return fuelUsed
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
