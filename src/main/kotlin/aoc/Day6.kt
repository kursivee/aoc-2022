package aoc

import util.FileUtil

fun main() {
    val input = FileUtil.read("day6.txt")
    solve(input, 4).also { println(it) }
    solve(input, 14).also { println(it) }
}

fun solve(s: String, targetLength: Int): Int {
    val sb = StringBuilder()
    for(i in s.indices) {
        val c = s[i]
        while(sb.contains(c)) {
            sb.deleteAt(0)
        }
        sb.append(c)
        if(sb.length == targetLength) return i + 1
    }
    return -1
}