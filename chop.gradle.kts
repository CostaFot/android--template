plugins {
    id("com.github.ben-manes.versions") version BuildPluginsVersion.VERSIONS_PLUGIN
}

buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://maven.fabric.io/public")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${BuildPluginsVersion.AGP}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${BuildPluginsVersion.KOTLIN}")
        // Firebase
        classpath("com.google.gms:google-services:4.3.3")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.1.1")
        // Dependency version checker
        classpath("com.github.ben-manes:gradle-versions-plugin:${BuildPluginsVersion.VERSIONS_PLUGIN}")
        /*// Add the dependency for the Performance Monitoring plugin
         classpath('com.google.firebase:perf-plugin:1.3.1'*/
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
