import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask


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

val dependenciesAndVersions: MutableMap<String, MutableSet<String>> = mutableMapOf()

tasks.register("customTask", myplugins.greet.CustomDependencyUpdatesTask::class.java) {
    this.resolvedDependencies = dependenciesAndVersions
}

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

    finalizedBy(tasks.findByName("customTask"))
}
