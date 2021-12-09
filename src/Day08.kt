fun main() {
    class Decoder(signalPatterns: String) {
        val decodeMap = mutableMapOf<String, Int>()

        init {
            val signalPatternList = signalPatterns.trim().split(" ")

            // 0 has 6 elements
            decodeMap[signalPatternList.first { pattern -> pattern.length == 2 }.sortChars()] = 1
            // 2 has 5 elements
            // 3 has 5 elements
            decodeMap[signalPatternList.first { pattern -> pattern.length == 4 }.sortChars()] = 4
            // 5 has 5 elements
            // 6 has 6 elements
            decodeMap[signalPatternList.first { pattern -> pattern.length == 3 }.sortChars()] = 7
            decodeMap[signalPatternList.first { pattern -> pattern.length == 7 }.sortChars()] = 8
            // 9 has 6 elements
        }

        fun decode(values: String): List<Int> {
            return values
                .trim()
                .split(" ")
                .mapNotNull { value ->
                    decodeMap[value.sortChars()]
                }
        }
    }

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
        val sum = input
            .map { line ->
                line.split("|")
            }
            .map { line ->
                val decoder = Decoder(line[0])
                decoder.decode(line[1])
            }
            .flatten()
            .sum()
        println(sum)
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
