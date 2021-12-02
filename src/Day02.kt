class SubmarinePosition {
    var horizontal = 0
    var depth = 0
    var aim = 0
}

abstract class SubmarineCommand() {
    abstract fun execute(position: SubmarinePosition)
}

fun main() {
    fun part1(input: List<String>): Int {
        class ForwardCommand(private val amount: Int) : SubmarineCommand() {
            override fun execute(position: SubmarinePosition) {
                position.horizontal += amount
            }
        }

        class DownCommand(private val amount: Int) : SubmarineCommand() {
            override fun execute(position: SubmarinePosition) {
                position.depth += amount
            }
        }

        class UpCommand(private val amount: Int) : SubmarineCommand() {
            override fun execute(position: SubmarinePosition) {
                position.depth -= amount
            }
        }

        val position = input
            .map { line -> line.split(" ") }
            .map { textCommand ->
                when (textCommand[0]) {
                    "forward" -> ForwardCommand(textCommand[1].toInt())
                    "down" -> DownCommand(textCommand[1].toInt())
                    "up" -> UpCommand(textCommand[1].toInt())
                    else -> throw IllegalStateException("Unknown command")
                }
            }
            .fold(SubmarinePosition()) { position, command ->
                command.execute(position)
                position
            }

        return position.depth * position.horizontal
    }

    fun part2(input: List<String>): Int {
        class ForwardCommand(private val amount: Int) : SubmarineCommand() {
            override fun execute(position: SubmarinePosition) {
                position.horizontal += amount
                position.depth += position.aim * amount
            }
        }

        class DownCommand(private val amount: Int) : SubmarineCommand() {
            override fun execute(position: SubmarinePosition) {
                position.aim += amount
            }
        }

        class UpCommand(private val amount: Int) : SubmarineCommand() {
            override fun execute(position: SubmarinePosition) {
                position.aim -= amount
            }
        }

        val position = input
            .map { line -> line.split(" ") }
            .map { textCommand ->
                when (textCommand[0]) {
                    "forward" -> ForwardCommand(textCommand[1].toInt())
                    "down" -> DownCommand(textCommand[1].toInt())
                    "up" -> UpCommand(textCommand[1].toInt())
                    else -> throw IllegalStateException("Unknown command")
                }
            }
            .fold(SubmarinePosition()) { position, command ->
                command.execute(position)
                position
            }

        return position.depth * position.horizontal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
