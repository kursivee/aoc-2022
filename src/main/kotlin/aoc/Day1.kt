package aoc

import util.FileUtil
import java.util.*

fun main() {
    // List of calories held by each elf split by empty line
    val file = FileUtil.read("day1.txt").split("\n")
    val input = mutableListOf<List<Int>>()
    val curr = mutableListOf<Int>()
    for(s in file) {
        if(s.isEmpty()) {
            input.add(curr.toList())
            curr.clear()
        } else {
            curr.add(s.toInt())
        }
    }
    input.add(curr)
    solveP1(input).also {
        println(it)
    }
    solveP2(input).also {
        println(it)
    }
}

// Get max calorie count out of all elves
fun solveP1(elves: List<List<Int>>): Int {
    var max = 0
    for(calories in elves) {
        max = maxOf(max, calories.sum())
    }
    return max
}

// Get sum of top 3 elves' calories
fun solveP2(elves: List<List<Int>>): Int {
    val q = PriorityQueue<Int>()
    for(calories in elves) {
        q.add(calories.sum() * -1)
    }
    return -1 * (q.poll() + q.poll() + q.poll())
}