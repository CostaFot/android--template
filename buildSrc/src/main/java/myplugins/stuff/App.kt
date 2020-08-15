package myplugins.stuff

import com.beust.klaxon.Klaxon
import com.beust.klaxon.PathMatcher
import java.io.File
import java.io.StringReader
import java.net.URL
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern

val baseFolder = "./"
val jsonReportLocation = baseFolder + "build/dependencyUpdates/report.json"
val jsonUpdateLocation = baseFolder + "build/dependencyUpdates/updates.json"
val buildSrcGradle = baseFolder + "buildSrc/build.gradle.kts"
val buildConstantsLocation = baseFolder + "buildSrc/src/main/java/Dependencies.kt"
val gradleWrapperFolder = baseFolder + "gradle/wrapper"

val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss")
val friendlyFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

val listOfUpdatedDependencies = mutableListOf<SimpleDependency>()

val dependenciesToIgnoreFile = File("${baseFolder}dependencyUpdate.ignore")
val dependenciesToIgnore = dependenciesToIgnoreFile.readLines().drop(1)

fun chop() {
    val now = OffsetDateTime.now()
    val dateBasicISO = now.format(formatter)
    val datePR = now.format(friendlyFormatter)

    /*"git checkout master".runCommand()
    "git pull".runCommand()
    "./gradlew dependencyUpdates".runCommand()*/

    val readDependencyResults = File(jsonReportLocation)
    val results = Klaxon().parse<GradleResults>(readDependencyResults)!!
    val allDepsMap = listAllCurrentDependencies(results).map { it.id to it }.toMap()
    Klaxon().parseArray<DependencyCustomerUpdate>(File(jsonUpdateLocation))!!.forEach {
        if (!it.latest.isNullOrEmpty())
            allDepsMap["${it.group}:${it.name}"]?.latest = it.latest

        if (!it.latestStable.isNullOrEmpty())
            allDepsMap["${it.group}:${it.name}"]?.latestStable = it.latestStable
    }

    updateBuildConstants(allDepsMap.values)

    val hasUpdatedAndroidGradle =
        updateAndroidGradle(allDepsMap["com.android.tools.build:gradle"])

    //update gradle wrapper
    // updateGradleWrapper(results)

    /*// git stuff to finish
    if (hasUpdated)
        "git add $buildConstantsLocation".runCommand()

    if (hasUpdatedAndroidGradle)
        "git add $buildSrcGradle".runCommand()

    if (hasUpdated) {
        grabGifUrl()
        "git checkout -b gradleUpdate-$dateBasicISO".runCommand()
        runCommand(listOf("git", "commit", "-m", "gradle update for $dateBasicISO"))
        "git push -u origin gradleUpdate-$dateBasicISO".runCommand()

        try {
            val file = File("gitPR.md")
            val sb = StringBuilder()
            sb.appendln("Gradle update $datePR\n")
            sb.appendln("I've got updates for you\n![gif]($gifUrl)")
            listOfUpdatedDependencies.forEach { sb.appendln("updated ${it.name}: ${it.projectUrl}") }
            file.writeText(sb.toString())

            runCommand(listOf("hub","pull-request","-F",file.absolutePath))

            file.delete()
        } catch (t: Throwable)
        {
            runCommand(listOf("hub","pull-request","-m","Gradle update for $datePR"))
        }
    }*/
}

fun updateGradleWrapper(results: GradleResults) {
    val currentGradleVersion = results.gradle.running.version
    val newestStableGradleVersion = results.gradle.current.version
    if (currentGradleVersion != newestStableGradleVersion) {
        "./gradlew wrapper --gradle-version=$newestStableGradleVersion --distribution-type=bin".runCommand()
        "git add $gradleWrapperFolder".runCommand()
    }
}

fun updateAndroidGradle(gradleDep: SimpleDependency?): Boolean {
    if (gradleDep == null)
        return false

    val buildSrcGradle = File(buildSrcGradle)
    val buildSrcGradleTextOriginal = buildSrcGradle.readText()
    var buildSrcGradleText = buildSrcGradleTextOriginal
    gradleDep.latestStable?.let {
        buildSrcGradleText = buildSrcGradleText.replace("${gradleDep.id}:${gradleDep.currentVersion}", it)
    }
    buildSrcGradle.writeText(buildSrcGradleText)
    val result = buildSrcGradleTextOriginal.equals(buildSrcGradleText).not()
    if (result)
        listOfUpdatedDependencies.add(gradleDep)
    return result
}

fun isBetaRc(version: String) = listOf("alpha", "beta", "rc", "cr", "m", "preview", "SNAPSHOT")
    .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-]*") }
    .any { it.matches(version) }

fun listAllCurrentDependencies(results: GradleResults): List<SimpleDependency> {
    val allDependencies = mutableListOf<SimpleDependency>()

    allDependencies.addAll(results.current.dependencies.map { it.toSimpleDependency() })
    allDependencies.addAll(results.exceeded.dependencies.map { it.toSimpleDependency() })
    allDependencies.addAll(results.outdated.dependencies.map { it.toSimpleDependency() })
    allDependencies.addAll(results.unresolved.dependencies.map { it.toSimpleDependency() })

    allDependencies.removeAll { dependenciesToIgnore.contains(it.id) }
    return allDependencies
}

fun updateBuildConstants(allDepsMap: Collection<SimpleDependency>): Boolean {
    val buildConstants = File(buildConstantsLocation)
    val originalBuildConstantsText = buildConstants.readText()
    var buildConstantsText = originalBuildConstantsText
    allDepsMap.forEach { simpleDependency ->
        val fullRef = "${simpleDependency.group}:${simpleDependency.name}:${simpleDependency.currentVersion}"

        if (isBetaRc(fullRef)) {
            simpleDependency.latest?.let {
                if (dependenciesToIgnore.contains(it)) {
                    return@forEach
                }
                if (fullRef != it && it != "null") { // groovy cast?
                    buildConstantsText = buildConstantsText.replace(fullRef, it)
                    listOfUpdatedDependencies.add(simpleDependency)
                }
            }
        } else {
            simpleDependency.latestStable?.let {
                if (dependenciesToIgnore.contains(it)) {
                    return@forEach
                }
                if (fullRef != it && it != "null") {
                    buildConstantsText = buildConstantsText.replace(fullRef, it)
                    listOfUpdatedDependencies.add(simpleDependency)
                }
            }
        }
    }

    buildConstants.writeText(buildConstantsText)

    return originalBuildConstantsText.equals(buildConstantsText).not()
}

fun grabGifUrl() {
    try {
        val url =
            URL("http://api.giphy.com/v1/gifs/random?tag=update&rating=PG-13&api_key=4n63kPUjuBFLBRenDMZ421vzJpxIJCZC").readText()
        Klaxon().pathMatcher(pathMatcher).parseJsonObject(StringReader(url))
    } catch (t: Throwable) {
        //just ignore it
    }
}

var gifUrl: String = "https://i.imgur.com/XNWoIkY.png"
val pathMatcher = object : PathMatcher {
    override fun pathMatches(path: String) = Pattern.matches(".*data.*images.*fixed_height_still.*url.*", path)

    override fun onMatch(path: String, value: Any) {
        gifUrl = value.toString()
    }
}
