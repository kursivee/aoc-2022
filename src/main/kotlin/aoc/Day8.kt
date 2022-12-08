package aoc

import util.FileUtil

fun main() {
    val file = FileUtil.read("day8.txt").split("\n")
    val arr: Array<IntArray> = Array(file.size) { IntArray(file[0].length) }
    for(i in file.indices) {
        val line = file[i]
        for(j in line.indices) {
            arr[i][j] = line[j] - '0'
        }
    }
    Day8.solveP1(arr).also { println(it) }
    Day8.solveP2(arr).also { println(it) }
}

object Day8 {

    fun solveP1(arr: Array<IntArray>): Int {
        val set = mutableSetOf<Pair<Int, Int>>()
        for(j in 1 until arr[0].size - 1) {
            var prev = -1
            for(i in arr.indices) {
                if(arr[i][j] > prev) {
                    set.add(i to j)
                    prev = maxOf(prev, arr[i][j])
                }
            }
        }
        for(j in 1 until arr[0].size - 1) {
            var prev = -1
            for(i in arr.size - 1 downTo 0) {
                if(arr[i][j] > prev) {
                    set.add(i to j)
                    prev = maxOf(prev, arr[i][j])
                }
            }
        }
        for(i in 1 until arr.size - 1) {
            var prev = -1
            for(j in arr[i].indices) {
                if(arr[i][j] > prev) {
                    set.add(i to j)
                    prev = maxOf(prev, arr[i][j])
                }
            }
        }
        for(i in 1 until arr.size - 1) {
            var prev = -1
            for(j in arr[i].size - 1 downTo 0) {
                if(arr[i][j] > prev) {
                    set.add(i to j)
                    prev = maxOf(prev, arr[i][j])
                }
            }
        }
        return 4 + set.size
    }

    fun solveP2(arr: Array<IntArray>): Int {
        var max = 0
        for(i in 1 until arr.size - 1) {
            for(j in 1 until arr[i].size - 1) {
                var count = 0
                var total = 1
                for(up in (i - 1) downTo  0) {
                    count++
                    if(arr[up][j] >= arr[i][j]) break
                }
                total *= count
                count = 0
                for(down in i + 1 until arr.size) {
                    count++
                    if(arr[down][j] >= arr[i][j]) break
                }
                total *= count
                count = 0
                for(left in j - 1 downTo 0) {
                    count++
                    if(arr[i][left] >= arr[i][j]) break
                }
                total *= count
                count = 0
                for(right in j + 1 until arr.size) {
                    count++
                    if(arr[i][right] >= arr[i][j]) break
                }
                total *= count
                max = maxOf(max, total)
            }
        }
        return max
    }
}