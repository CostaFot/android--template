import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask


plugins {
    id("com.github.ben-manes.versions") version "0.29.0"
}

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.29.0")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.2.0")
        classpath("com.google.gms:google-services:4.3.3")
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
