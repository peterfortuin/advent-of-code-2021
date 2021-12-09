fun main() {
    fun part1(input: List<String>): Int {
        val count = input
            .map { line ->
                line.split("|")[1].trim()
            }
            .map { values ->
                values.split(" ")
            }
            .flatten()
            .count { value ->
                value.length == 2 || value.length == 4 || value.length == 3 || value.length == 7
            }
        return count
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
