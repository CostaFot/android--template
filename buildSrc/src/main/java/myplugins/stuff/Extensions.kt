package myplugins.stuff

import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit


fun DependencyCurrent.toSimpleDependency(): SimpleDependency {
    return SimpleDependency(this.group, this.name, this.version, this.projectUrl)
}

fun DependencyExceeded.toSimpleDependency(): SimpleDependency {
    return SimpleDependency(this.group, this.name, this.version, this.projectUrl)
}

fun DependencyOutDated.toSimpleDependency(): SimpleDependency {
    return SimpleDependency(this.group, this.name, this.version, this.projectUrl)
}

fun DependencyUnresolved.toSimpleDependency(): SimpleDependency {
    return SimpleDependency(this.group, this.name, this.version, this.projectUrl)
}

fun String.runCommand(workingDirPath: String = baseFolder) {
    ProcessBuilder(*split(" ").toTypedArray())
        .directory(File(workingDirPath))
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()
        .waitFor(10, TimeUnit.MINUTES)
}

fun runCommand(commands: List<String>, workingDirPath: String = baseFolder) {
    ProcessBuilder(commands)
        .directory(File(workingDirPath))
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()
        .waitFor(10, TimeUnit.MINUTES)
}


fun InputStream?.inputStreamToString(): String {
    if (this == null)
        return ""

    val bufferedReader = BufferedReader(InputStreamReader(this))
    val result = StringBuilder()
    var line: String? = ""
    while (line != null) {
        line = bufferedReader.readLine()
        if (line != null)
            result.appendln(line)
    }

    return result.toString()
}
