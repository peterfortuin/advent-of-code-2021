fun main() {
    class BingoCard(val numbers: List<List<Int>>) {
        val checkedOf = listOf(
            mutableListOf(false, false, false, false, false),
            mutableListOf(false, false, false, false, false),
            mutableListOf(false, false, false, false, false),
            mutableListOf(false, false, false, false, false),
            mutableListOf(false, false, false, false, false)
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

        fun checkNumber(numberToCheck: Int) {
            numbers.forEachIndexed { rowNumber, row ->
                row.forEachIndexed { colNumber, number ->
                    if (number == numberToCheck) {
                        checkedOf[rowNumber][colNumber] = true
                    }
                }
            }
        }

        fun hasBingo(): Boolean {
            checkedOf.forEach { row ->
                if (row[0] && row[1] && row[2] && row[3] && row[4])
                    return true
            }

            (0..4).forEach { col ->
                if (checkedOf[0][col] && checkedOf[1][col] && checkedOf[2][col] && checkedOf[3][col] && checkedOf[4][col])
                    return true
            }

            return false
        }

        fun sumOfUncheckedNumbers(): Int {
            return checkedOf.foldIndexed(0) { rowNUmber, acc, row ->
                acc + row.foldIndexed(0) { colNumber, acc, checked ->
                    if (!checked)
                        acc + numbers[rowNUmber][colNumber]
                    else
                        acc
                }
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

        val cards = cardsInput.windowed(6, 6)
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

        return ParsedInput(drawnBalls, cards)
    }

    fun part1(input: List<String>): Int {
        val (drawnBalls, cards) = parseInput(input)
        drawnBalls.forEach { number ->
            println("Next number: $number")

            cards.forEach { card ->
                card.checkNumber(number)
                println(card)
                if (card.hasBingo()) {
                    println("Bingo!")
                    println("Number = $number")
                    println("SumOfUncheckedNumbers = ${card.sumOfUncheckedNumbers()}")
                    return number * card.sumOfUncheckedNumbers()
                }
            }
        }

        return input.size
    }

    fun part2(input: List<String>): Int {
        val (drawnBalls, cards) = parseInput(input)

        val cardsLeft = cards.toMutableList()

        drawnBalls.forEach { number ->
            println("Next number: $number")

            val cardsToRemove = mutableListOf<BingoCard>()
            cardsLeft.forEach { card ->
                card.checkNumber(number)
                println(card)
                if (card.hasBingo()) {
                    println("Bingo! Removing card!")

                    cardsToRemove.add(card)
                    if (cardsLeft.size == 1) {
                        println("Number = $number")
                        println("SumOfUncheckedNumbers = ${card.sumOfUncheckedNumbers()}")
                        return number * card.sumOfUncheckedNumbers()
                    }
                }
            }
            cardsLeft.removeAll(cardsToRemove)
        }

        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
