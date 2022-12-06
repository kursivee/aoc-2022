package aoc

import util.FileUtil

fun main() {
    val input = FileUtil.read("day3.txt").split("\n")
    solveP1(input).also { println(it) }
    solveP2(input).also { println(it) }
}

fun solveP1(rucksacks: List<String>): Int {
    var sum = 0
    for(rucksack in rucksacks) {
        val list = mutableListOf<Char>()
        for(i in rucksack.indices) {
            val c = rucksack[i]
            if(i < rucksack.length / 2) {
                list.add(c)
            } else {
                if(list.contains(c)) {
                    sum += if(c.isLowerCase()) {
                        c - 'a' + 1
                    } else {
                        c - 'A' + 27
                    }
                    break
                }
            }
        }
    }
    return sum
}

fun solveP2(rucksacks: List<String>): Int {
    var sum = 0
    for(i in rucksacks.indices step 3) {
        val counts = mutableMapOf<Char, Int>()
        for(j in i until i + 3) {
            for(c in rucksacks[j].toSet()) {
                val count = counts.getOrDefault(c, 0)
                if(count == 2) {
                    sum += if(c.isLowerCase()) {
                        c - 'a' + 1
                    } else {
                        c - 'A' + 27
                    }
                    break
                }
                counts[c] = count + 1
            }
        }
    }
    return sum
}