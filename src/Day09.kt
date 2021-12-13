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

    data class Point(
        val column: Int,
        val row: Int
    )

    fun findBasin(raster: List<List<Int>>, point: Point, basinPoints: MutableSet<Point>): MutableSet<Point> {
        if (raster[point.row][point.column] == 9 || basinPoints.contains(point))
            return basinPoints

        var newBasinPoints = basinPoints
        newBasinPoints.add(point)

        if (point.column > 0)
            newBasinPoints = findBasin(raster, Point(point.column - 1, point.row), basinPoints)
        if (point.column < raster[0].size - 1)
            newBasinPoints = findBasin(raster, Point(point.column + 1, point.row), basinPoints)
        if (point.row > 0)
            newBasinPoints = findBasin(raster, Point(point.column, point.row - 1), basinPoints)
        if (point.row < raster.size - 1)
            newBasinPoints = findBasin(raster, Point(point.column, point.row + 1), basinPoints)

        return newBasinPoints
    }

    fun part2(input: List<String>): Int {
        val raster = input
            .map { line ->
                line.toList().map { numberChar ->
                    numberChar.toString().toInt()
                }
            }

        val lowPoints = raster.foldIndexed((mutableListOf<Point>())) { row, list, line ->
            line.foldIndexed(list) { column, lst, item ->
                val left = if (column > 0) raster[row][column - 1] else 10
                val right = if (column < line.size - 1) raster[row][column + 1] else 10
                val top = if (row > 0) raster[row - 1][column] else 10
                val bottom = if (row < raster.size - 1) raster[row + 1][column] else 10

                if (left > item && right > item && top > item && bottom > item) {
                    lst.add(Point(column, row))
                    lst
                } else
                    lst
            }
        }

        val basins = lowPoints
            .map { lowPoint ->
                findBasin(raster, lowPoint, mutableSetOf())
            }

        val basinsSizes = basins
            .map { basin -> basin.size }
            .sorted()

        val biggestBasinsMultiplied =
            basinsSizes[basinsSizes.size - 1] * basinsSizes[basinsSizes.size - 2] * basinsSizes[basinsSizes.size - 3]

        return biggestBasinsMultiplied
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
