fun main() {
    fun part1(input: List<String>): Int {
        val raster = input
            .map { line ->
                line.toList().map { numberChar ->
                    numberChar.toString().toInt()
                }
            }

        val totalRiskLevel = raster.foldIndexed(0) { row, riskLevel, line ->
            line.foldIndexed(riskLevel) { column, riskLvl, item ->
                val left = if (column > 0) raster[row][column - 1] else 10
                val right = if (column < line.size - 1) raster[row][column + 1] else 10
                val top = if (row > 0) raster[row - 1][column] else 10
                val bottom = if (row < raster.size - 1) raster[row + 1][column] else 10

                if (left > item && right > item && top > item && bottom > item)
                    riskLvl + item + 1
                else
                    riskLvl
            }
        }

        return totalRiskLevel
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
