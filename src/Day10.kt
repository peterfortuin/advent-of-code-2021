fun main() {
    val symbols = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>',
    )

    fun findCorrupted(line: String): Int {
        var lineToParse = line

        while (lineToParse.isNotEmpty()) {
            var itemRemoved = false
            (0..lineToParse.length - 2).forEach { i ->
                if (itemRemoved) return@forEach

                if (symbols.containsValue(lineToParse[i + 1]) && lineToParse[i + 1] != symbols[lineToParse[i]]) {
                    return when (lineToParse[i + 1]) {
                        ')' -> 3
                        ']' -> 57
                        '}' -> 1197
                        '>' -> 25137
                        else -> throw RuntimeException("Not possible")
                    }
                }

                if (lineToParse[i + 1] == symbols[lineToParse[i]]) {
                    lineToParse = lineToParse.removeRange(i, i + 2)
                    itemRemoved = true
                }
            }

            if (!itemRemoved) {
                break
            }
        }

        return 0
    }

    fun part1(input: List<String>): Int {
        return input.map { line -> findCorrupted(line) }.sum()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
