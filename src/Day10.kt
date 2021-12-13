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

    fun part2(input: List<String>): Long {
        val scores = input
            .asSequence()
            .mapNotNull { line ->
                if (findCorrupted(line) == 0)
                    line
                else
                    null
            }
            .map { line ->
                line.toCharArray()
            }
            .map { line ->
                line.fold(Stack<Char>()) { stack, c ->
                    if (symbols.keys.contains(c)) {
                        stack.push(c)
                    }

                    // stack.peek() != null && symbols.containsKey(stack.peek()) && symbols[stack.peek()]
                    if (symbols.values.contains(c)) {
                        val symbolToClose = stack.peek()
                        if (symbolToClose != null && symbols[symbolToClose] == c) {
                            stack.pop()
                        }
                    }

                    stack
                }
            }
            .map { stack -> stack.reversed() }
            .map { stack ->
                stack.toList().fold(0L) { acc, c ->
                    val points = when (c) {
                        '(' -> 1
                        '[' -> 2
                        '{' -> 3
                        '<' -> 4
                        else -> throw RuntimeException("Not possible")
                    }

                    acc * 5 + points
                }
            }
            .toList()
            .sorted()

        return scores[(scores.size - 1) / 2]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
