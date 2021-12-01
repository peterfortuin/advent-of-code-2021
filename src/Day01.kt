fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.fold(Pair(Int.MAX_VALUE, 0)) { acc, next ->
            val numberOfIncreases = if (next > acc.first) acc.second + 1 else acc.second
            Pair(next, numberOfIncreases)
        }.second
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
