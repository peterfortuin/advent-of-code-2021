fun main() {
    class Decoder(signalPatterns: String) {
        val decodeMap = mutableMapOf<String, Int>()

        init {
            val signalPatternList = signalPatterns.trim().split(" ").map { it.sortChars() }

            val pattern1 = signalPatternList.first { pattern -> pattern.length == 2 }

            val pattern4 = signalPatternList.first { pattern -> pattern.length == 4 }

            val pattern9 = signalPatternList
                .first { pattern -> pattern.length == 6 && pattern.containsLetters(pattern4) }

            val pattern0 = signalPatternList.first { pattern ->
                pattern.length == 6 && pattern != pattern9 && pattern.containsLetters(pattern1)
            }

            val pattern3 =
                signalPatternList.first { pattern -> pattern.length == 5 && pattern.containsLetters(pattern1) }

            val pattern5 = signalPatternList.first { pattern ->
                pattern.length == 5
                        && pattern.subtractLetters(pattern9).isEmpty()
                        && pattern != pattern3
            }

            decodeMap[pattern1] = 1
            decodeMap[pattern4] = 4
            decodeMap[signalPatternList.first { pattern -> pattern.length == 3 }] = 7
            decodeMap[signalPatternList.first { pattern -> pattern.length == 7 }] = 8
            decodeMap[pattern9] = 9
            decodeMap[pattern0] = 0
            decodeMap[signalPatternList.first { pattern -> pattern.length == 6 && pattern != pattern9 && pattern != pattern0 }] =
                6
            decodeMap[pattern3] = 3

            decodeMap[pattern5] = 5
            decodeMap[signalPatternList.first { pattern -> pattern.length == 5 && pattern != pattern3 && pattern != pattern5 }] =
                2
        }

        fun decode(values: String): List<Int> {
            return values
                .trim()
                .split(" ")
                .mapNotNull { value ->
                    val decodedValue = decodeMap[value.sortChars()]
                    decodedValue
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
            .map { numbers ->
                numbers[0] * 1000 + numbers[1] * 100 + numbers[2] * 10 + numbers[3]
            }
            .sum()

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
