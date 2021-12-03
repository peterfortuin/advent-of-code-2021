fun main() {
    fun part1(input: List<String>): Int {
        val gammaAndEpsilon = input
            .toList()
            .fold(IntArray(input.first().length) { 0 }) { acc, next ->
                next.map { value -> value == '1' }.forEachIndexed { index, b ->
                    if (b) acc[index] += 1
                }

                acc
            }.fold(Pair(0, 0)) { acc, next ->
                println("$next - ${input.size / 2}")
                Pair(
                    acc.first.shl(1) or (if (next > input.size / 2) 1 else 0),
                    acc.second.shl(1) or (if (next < input.size / 2) 1 else 0)
                )
            }

        val gamma = gammaAndEpsilon.first
        val epsilon = gammaAndEpsilon.second

        println("$gamma / $epsilon")

        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
