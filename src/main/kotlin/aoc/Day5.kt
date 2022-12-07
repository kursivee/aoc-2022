package aoc

import util.FileUtil

fun main() {
    // I modified the inital stack values to make it easier to parse
    val file = FileUtil.read("day5.txt").split("\n")
    val stacks = mutableListOf<ArrayDeque<Char>>()
    val commands = mutableListOf<List<Int>>()
    var initInput = true
    for(line in file) {
        if(line.isEmpty()) {
            initInput = false
            continue
        }
        if(initInput) {
            val stack = ArrayDeque<Char>()
            for(c in line) {
                stack.addLast(c)
            }
            stacks.add(stack)
        } else {
            val l = line.replace("move", "").replace("from", "").replace("to", "").trim()
            commands.add(l.split("  ").map {
                it.toInt()
            })
        }
    }
    // Can't run these at the same time since the stacks are mutable
//    solveP1(stacks, commands).also { println(it) }
    solveP2(stacks, commands).also { println(it) }
}

fun solveP1(stacks: List<ArrayDeque<Char>>, commands: List<List<Int>>): String {
    for(command in commands) {
        val times = command[0]
        val from = command[1] - 1
        val to = command[2] - 1
        repeat(times) {
            stacks[to].addLast(stacks[from].removeLast())
        }
    }
    var s = ""
    for(stack in stacks) {
        s += stack.last()
    }
    return s
}

fun solveP2(stacks: List<ArrayDeque<Char>>, commands: List<List<Int>>): String {
    for(command in commands) {
        val times = command[0]
        val from = command[1] - 1
        val to = command[2] - 1
        var s = ""
        repeat(times) {
            s += stacks[from].removeLast()
        }
        for(i in s.length - 1 downTo 0) {
            stacks[to].addLast(s[i])
        }
    }
    var s = ""
    for(stack in stacks) {
        s += stack.last()
    }
    return s
}