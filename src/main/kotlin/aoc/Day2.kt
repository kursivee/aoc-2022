package aoc

import util.FileUtil

fun main() {
    // List of calories held by each elf split by empty line
    val input = FileUtil.read("day2.txt").split("\n").map {
        it.split(" ")
    }
    solveP1(input).also { println(it) }
    solveP2(input).also { println(it) }
}

// Get score of outcomes
fun solveP1(guide: List<List<String>>): Int {
    var score = 0
    for(picks in guide) {
        score += picks[0].solve(picks[1])
    }
    return score
}

fun solveP2(guide: List<List<String>>): Int {
    val converter = mutableMapOf(
        "A" to 3,
        "B" to 4,
        "C" to 5,
    )
    var score = 0
    for(outcome in guide) {
        when(outcome[1]) {
            "X" -> {
                score += (converter[outcome[0]]!! - 1) % 3 + 1
            }
            "Y" -> {
                score += (converter[outcome[0]]!!) % 3 + 1 + 3
            }
            "Z" -> {
                score += (converter[outcome[0]]!! + 1) % 3 + 1 + 6
            }

        }
    }
    return score
}

private fun String.solve(s: String): Int {
    val points = mutableMapOf(
        "X" to 1,
        "Y" to 2,
        "Z" to 3
    )
    return when {
        this == "A" && s == "X" -> 3
        this == "B" && s == "Y" -> 3
        this == "C" && s == "Z" -> 3
        this == "A" && s == "Y" -> 6
        this == "B" && s == "Z" -> 6
        this == "C" && s == "X" -> 6
        else -> 0
    } + points[s]!!
}