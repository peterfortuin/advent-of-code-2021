data class Line(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int
)

fun main() {
    fun count2OrLarger(input: List<String>, doDiagonals: Boolean = false): Int {
        val regex = """([0-9]*),([0-9]*) -> ([0-9]*),([0-9]*)""".toRegex()

        val numberOf2OrLarger = input
            .asSequence()
            .mapNotNull { line ->
                regex.find(line)?.groupValues
            }.map { groupValues ->
                Line(groupValues[1].toInt(), groupValues[2].toInt(), groupValues[3].toInt(), groupValues[4].toInt())
            }.fold(Array(1000) { IntArray(1000) }) { acc, line ->
                val xRange = minOf(line.x1, line.x2)..maxOf(line.x1, line.x2)
                val yRange = minOf(line.y1, line.y2)..maxOf(line.y1, line.y2)

                when {
                    line.x1 == line.x2 -> {
                        yRange.forEach { y ->
                            acc[y][line.x1] += 1
                        }
                    }
                    line.y1 == line.y2 -> {
                        xRange.forEach { x ->
                            acc[line.y1][x] += 1
                        }
                    }
                    doDiagonals -> {
                        val xIterator = (line.x1 toward line.x2).iterator()
                        val yIterator = (line.y1 toward line.y2).iterator()

                        while (xIterator.hasNext() && yIterator.hasNext()) {
                            val x = xIterator.next()
                            val y = yIterator.next()

                            acc[y][x] += 1
                        }
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

    fun part1(input: List<String>): Int {
        return count2OrLarger(input)
    }

    fun part2(input: List<String>): Int {
        return count2OrLarger(input, true)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
