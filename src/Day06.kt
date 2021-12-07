fun main() {
    fun growFish(input: List<String>, days: Int): Long {
        var lanternFish = input.first().split(',').map { it.toInt() }.map { Pair(1L, it) }.toMutableList()
        println("Initial state: $lanternFish")

        (1..days).forEach {
            var numberOfFishToAdd = 0L

            lanternFish = lanternFish.map { fishGroup ->
                var daysUntilNewRemaining = fishGroup.second

                daysUntilNewRemaining -= 1

                if (daysUntilNewRemaining == -1) {
                    daysUntilNewRemaining = 6
                    numberOfFishToAdd += fishGroup.first
                }

                Pair(fishGroup.first, daysUntilNewRemaining)
            }.toMutableList()

            lanternFish.add(Pair(numberOfFishToAdd, 8))

            val totalFish = lanternFish.fold(0L) { acc, fish -> acc + fish.first }

            println("After ${it} day${if (it > 1) "s" else " "}:  $totalFish")
        }

        val totalFish = lanternFish.fold(0L) { acc, fish -> acc + fish.first }
        println("Number of lanternfish = $totalFish")
        return totalFish
    }

    fun part1(input: List<String>): Long {
        return growFish(input, 80)
    }

    fun part2(input: List<String>): Long {
        return growFish(input, 256)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539)
    Long.MAX_VALUE

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
