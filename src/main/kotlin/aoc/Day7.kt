package aoc

import util.FileUtil

data class FileNode(
    val parent: FileNode? = null,
    val children: MutableList<FileNode> = mutableListOf(),
    val dir: Boolean = false,
    val name: String,
    val size: Int = 0
) {

    override fun toString(): String {
        return name
    }

    companion object {
        fun directory(parent: FileNode?, name: String): FileNode {
            return FileNode(parent = parent, dir = true, name = name)
        }

        fun file(name: String, size: Int): FileNode {
            return FileNode(name = name, size = size)
        }
    }
}

data class Day7Input(
    val command: List<String>,
    val output: MutableList<List<String>> = mutableListOf()
)

fun main() {
    val file = FileUtil.read("day7.txt").split("\n")
    val input = mutableListOf<Day7Input>()
    var curr: Day7Input? = null
    for(line in file) {
        if (line.startsWith("$")) {
            curr = Day7Input(line.split(" "))
            input.add(curr)
        } else {
            curr?.output?.add(line.split(" "))
        }
    }
    Day7.solveP1(input).also { println(it) }
    Day7.solveP2().also { println(it) }
}

object Day7 {
    var totalP1 = 0
    val values = mutableListOf<Int>()
    fun solveP1(terminal: List<Day7Input>): Int {
        val root = FileNode.directory(null, "/")
        var curr = root
        for(term in terminal) {
            when {
                term.command[0] == "$" && term.command[1] == "cd" -> {
                    curr = when(term.command[2]) {
                        ".." -> curr.parent!!
                        "/" -> root
                        else -> curr.children.find { it.name == term.command[2] }!!
                    }
                }
                term.command[0] == "$" && term.command[1] == "ls" -> {
                    for(output in term.output) {
                        if(output[0].startsWith("dir")) curr.children.add(FileNode.directory(parent = curr, name = output[1]))
                        else curr.children.add(FileNode.file(name = output[1], size = output[0].toInt()))
                    }
                }
            }
        }
        getSum(root)
        return totalP1
    }

    fun getSum(node: FileNode): Int {
        if(!node.dir) return node.size
        var sum = 0
        for(child in node.children) {
            sum += getSum(child)
        }
        if(sum <= 100000) totalP1 += sum
        values.add(sum)
        return sum
    }

    // Run together with P1
    fun solveP2(): Int {
        values.sort()
        val target = 30000000 - (70000000 - values.last())
        for(n in values) {
            if(n >= target) return n
        }
        return 0
    }
}