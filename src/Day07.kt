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
        val originalPositions = input.first().split(",").map { it.toInt() }
        val maxPosition = originalPositions.maxOrNull() ?: 0

        val bestHorizontalPositionAndFuel = (0..maxPosition).fold(Pair(-1, Int.MAX_VALUE)) { acc, currentPosition ->
            val fuelUsedForThisPosition = originalPositions.fold(0) { acc, next ->
                val distance = (currentPosition - next).absoluteValue
                val fuelNeeded = acc + distance * (distance + 1) / 2

                fuelNeeded
            }

            if (fuelUsedForThisPosition < acc.second) {
                Pair(currentPosition, fuelUsedForThisPosition)
            } else {
                acc
            }
        }

        return bestHorizontalPositionAndFuel.second
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
