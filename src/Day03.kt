import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        val gammaAndEpsilon = input
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
        val oxygenGeneratorRating = input
            .toIntArrays()
            .filterUntilOne(true, 0)
            .toNumber()
        val co2ScrubberRating = input
            .toIntArrays()
            .filterUntilOne(false, 0)
            .toNumber()

        println("$oxygenGeneratorRating / $co2ScrubberRating")

        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

private fun Collection<String>.toIntArrays(): List<List<Int>> {
    return map { value ->
        value
            .toCharArray()
            .map {
                if (it == '1') 1 else 0
            }
    }
}

private fun List<List<Int>>.filterUntilOne(mostCommon: Boolean, bit: Int): List<Int> {
    val numberOfZeros = this.count { it[bit] == 0 }
    val numberOfOnes = this.count { it[bit] == 1 }
    val filter = when {
        mostCommon && numberOfZeros > numberOfOnes -> 0
        mostCommon && numberOfZeros <= numberOfOnes -> 1
        !mostCommon && numberOfZeros > numberOfOnes -> 1
        else -> 0
    }

    val filteredValues = this.filter { it[bit] == filter }

    return if (filteredValues.size == 1) filteredValues.first() else filteredValues.filterUntilOne(mostCommon, bit + 1)
}

private fun List<Int>.toNumber(): Int {
    return this.foldIndexed(0) { index, acc, next ->
        acc + (2.0.pow(this.size - index - 1) * next).toInt()
    }
}
