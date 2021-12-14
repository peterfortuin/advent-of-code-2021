fun main() {
    data class StepResult(val grid: MutableList<MutableList<Int>>, val flashes: Int)

    fun step(grid: MutableList<MutableList<Int>>): StepResult {
        // TODO implement this function
        return StepResult(grid, 0)
    }

    fun part1(input: List<String>): Int {
        var grid = input
            .map { line ->
                line.toCharArray().map { c -> c.toString().toInt() }.toMutableList()
            }.toMutableList()

        val flashes = (0..99).fold(0) { acc, _ ->
            val (g, flashes) = step(grid)
            grid = g

            acc + flashes
        }

        println(grid)
        println(flashes)

        return flashes
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 1656)

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
