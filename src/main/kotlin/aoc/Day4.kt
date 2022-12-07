package aoc

import util.FileUtil

fun main() {
    val input = FileUtil.read("day4.txt").split("\n").map { s1 ->
        s1.split(",").map { s2 ->
            s2.split("-").map { s3 ->
                s3.toInt()
            }
        }
    }
    solveP1(input).also { println(it) }
    solveP2(input).also { println(it) }
}

fun solveP1(assignments: List<List<List<Int>>>): Int {
    var count = 0
    for(assignment in assignments) {
        if(assignment[0].within(assignment[1]) || assignment[1].within(assignment[0])) count++
    }
    return count
}

fun List<Int>.within(l1: List<Int>): Boolean {
    return this[0] <= l1[0] && this[1] >= l1[1]
}

fun solveP2(assignments: List<List<List<Int>>>): Int {
    var count = 0
    for(assignment in assignments) {
        if(assignment[0].overlap(assignment[1]) || assignment[1].overlap(assignment[0])) {
            count++
        }
    }
    return count
}

fun List<Int>.overlap(l1: List<Int>): Boolean {
    return this[1] >= l1[0] && this[0] <= l1[0]
}
