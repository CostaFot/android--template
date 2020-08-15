package myplugins.greet

import org.gradle.api.internal.artifacts.ivyservice.ivyresolve.strategy.DefaultVersionComparator
import org.gradle.api.internal.artifacts.ivyservice.ivyresolve.strategy.VersionParser
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CustomDependencyUpdatesTask : DefaultTask() {

    @Input
    lateinit var resolvedDependencies: MutableMap<String, MutableSet<String>>

    @TaskAction
    fun exec() {

        logger.lifecycle("HALLOOOO   $resolvedDependencies")

        val baseComparator = DefaultVersionComparator().asVersionComparator()
        val versionParser = VersionParser()
        val versionComparator = Comparator<String> { p0, p1 ->
            baseComparator.compare(
                versionParser.transform(p0),
                versionParser.transform(p1)
            )
        }
        val someFile = File("build/dependencyUpdates/updates.json")
        someFile.delete()
        someFile.createNewFile()
        someFile.setWritable(true)

        val sb = StringBuilder()
        sb.appendln("[")
        resolvedDependencies.forEach {
            sb.appendln("{")
            val group = it.key.split(':')[0]
            val name = it.key.split(':')[1]
            sb.appendln("\"group\": \"$group\",")
            sb.appendln("\"name\": \"$name\",")

            val latest = it.value.sortedWith(versionComparator).last()
            val stableReleases = it.value.filterNot { v -> isBetaRc(v) }
            val latestStable =
                if (stableReleases.any()) stableReleases.sortedWith(versionComparator).last() else null

            sb.appendln("\"latest\": \"$latest\",")
            sb.appendln("\"latestStable\": \"$latestStable\"},")
        }
        sb.deleteCharAt(sb.length - 2)
        sb.appendln("]")
        someFile.writeText(sb.toString())
    }

    fun isBetaRc(version: String) = listOf("alpha", "beta", "rc", "cr", "m", "preview", "SNAPSHOT")
        .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-]*") }
        .any { it.matches(version) }

}
