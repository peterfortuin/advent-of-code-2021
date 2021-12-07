fun main() {
    fun part1(input: List<String>): Int {
        var lanternFish = input.first().split(',').map { it.toInt() }.toMutableList()
        println("Initial state: $lanternFish")

        (1..80).forEach {
            var numberOfFishToAdd = 0

            lanternFish = lanternFish.map { daysUntilNew ->
                var daysUntilNewRemaining = daysUntilNew

                daysUntilNewRemaining -= 1

                if (daysUntilNewRemaining == -1) {
                    daysUntilNewRemaining = 6
                    numberOfFishToAdd += 1
                }

                daysUntilNewRemaining
            }.toMutableList()

            lanternFish.addAll(IntArray(numberOfFishToAdd) { 8 }.toList())

            println("After ${it} day${if (it > 1) "s" else " "}:  $lanternFish")
        }
        println("Number of lanternfish = ${lanternFish.size}")
        return lanternFish.size
    }

    fun part2(input: List<String>): Long {
        return input.size.toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
