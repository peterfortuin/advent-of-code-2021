data class Line(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
)

fun main() {
    fun part1(input: List<String>): Int {
        val regex = """([0-9]*),([0-9]*) -> ([0-9]*),([0-9]*)""".toRegex()

        val numberOf2OrLarger = input
            .asSequence()
            .mapNotNull { line ->
                regex.find(line)?.groupValues
            }.map { groupValues ->
                Line(groupValues[1].toInt(), groupValues[2].toInt(), groupValues[3].toInt(), groupValues[4].toInt())
            }.fold(Array(1000) { IntArray(1000) }) { acc, line ->
                if (line.x1 == line.x2) {
                    (minOf(line.y1, line.y2)..maxOf(line.y1, line.y2)).forEach { y ->
                        acc[y][line.x1] += 1
                    }
                }
                if (line.y1 == line.y2) {
                    (minOf(line.x1, line.x2)..maxOf(line.x1, line.x2)).forEach { x ->
                        acc[line.y1][x] += 1
                    }
                }
                acc
            }.fold(mutableListOf<Int>()) { acc, row ->
                acc.addAll(row.toList())
                acc
            }.fold(0) { acc, i ->
                if (i > 1)
                    acc + 1
                else
                    acc
            }

        return numberOf2OrLarger
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
