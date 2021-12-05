fun main() {
    class BingoCard(val numbers: List<List<Int>>) {
        var checkedOf = listOf(
            listOf(false, false, false, false, false),
            listOf(false, false, false, false, false),
            listOf(false, false, false, false, false),
            listOf(false, false, false, false, false),
            listOf(false, false, false, false, false)
        )

        override fun toString(): String {
            return numbers.foldIndexed("") { row, acc, numbersLine ->
                acc + numbersLine.foldIndexed("") { col, acc, number ->
                    if (checkedOf[row][col])
                        acc + "\u001B[31m${number.toString().padStart(3)}\u001B[0m"
                    else
                        acc + "$number".padStart(3)
                } + "\n"
            }
        }
    }

    data class ParsedInput(val drawnBalls: List<Int>, val cards: List<BingoCard>)

    fun parseInput(input: List<String>): ParsedInput {
        val drawnBalls = input
            .first()
            .split(',')
            .map { it.toInt() }

        val cardsInput = input.subList(2, input.size)

        cardsInput.windowed(6, 6)
            .map { cardInput ->
                cardInput.mapNotNull { cardLine ->
                    if (cardLine.isEmpty())
                        null
                    else
                        cardLine
                            .trim()
                            .split("\\s+".toRegex())
                            .map { it.toInt() }
                }
            }
            .map { cardNumbers ->
                BingoCard(cardNumbers)
            }
            .forEach {
                println(it)
            }

        return ParsedInput(drawnBalls, emptyList())
    }

    fun part1(input: List<String>): Int {
        val (drawnBalls, cards) = parseInput(input)
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
