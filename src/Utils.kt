import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

infix fun Int.toward(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}

fun List<Double>.median(): Double {
    return sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }
}

fun String.sortChars(): String {
    return toCharArray().sorted().joinToString("")
}

fun String.containsLetters(letters: String): Boolean {
    letters.forEach { letter ->
        if (!this.contains(letter))
            return false
    }

    return true
}

fun String.subtractLetters(lettersToSubtract: String): String {
    val letters = this.toCharArray().toMutableList()

    lettersToSubtract.toCharArray().forEach {
        letters.remove(it)
    }

    return letters.joinToString("")
}