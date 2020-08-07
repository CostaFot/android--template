import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.internal.artifacts.ivyservice.ivyresolve.strategy.DefaultVersionComparator
import org.gradle.api.internal.artifacts.ivyservice.ivyresolve.strategy.VersionParser

plugins {
    id("com.github.ben-manes.versions") version BuildPluginsVersion.DEPENDENCY_VERSION_CHECKER
}

buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://maven.fabric.io/public")
    }
    dependencies {
        classpath(Plugins.AGP)
        classpath(Plugins.KOTLIN)
        classpath(Plugins.GOOGLE_SERVICES)
        classpath(Plugins.CRASHLYTICS)
        classpath(Plugins.DEPENDENCY_VERSION_CHECKER)
    }
}


allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven(url = "https://jitpack.io")
        maven(url = "https://maven.google.com/")
        maven(url = "http://dl.bintray.com/piasy/maven")
        maven(url = "https://dl.bintray.com/arrow-kt/arrow-kt/")
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}

val dependenciesAndVersions = mutableMapOf<String, MutableSet<String>>()
tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
    fun isBetaRc(version: String) = listOf("alpha", "beta", "rc", "cr", "m", "preview", "SNAPSHOT")
        .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-]*") }
        .any { it.matches(version) }

    resolutionStrategy {
        componentSelection {
            all {
                if (dependenciesAndVersions.containsKey(candidate.group + ":" + candidate.module))
                    dependenciesAndVersions[candidate.group + ":" + candidate.module]!!.add(candidate.toString())
                else
                    dependenciesAndVersions[candidate.group + ":" + candidate.module] =
                        mutableSetOf(candidate.toString())

                if (isBetaRc(candidate.version)) {
                    reject("Release candidate")
                }
            }
        }
    }
    outputFormatter = "json"

    doLast {
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
        dependenciesAndVersions.forEach {
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
}
