package util

import java.io.File
import java.io.FileNotFoundException

object FileUtil {
    fun read(path: String, trim: Boolean = true): String {
        val resource = this::class.java.classLoader.getResource(path) ?: throw FileNotFoundException("could not load '$path'")
        val file = File(resource.toURI())
        return file.readText()
    }
}
