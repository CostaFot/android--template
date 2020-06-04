plugins {
    id("com.github.ben-manes.versions") version BuildPluginsVersion.DEPENDENCY_VERSION_CHECKER
    greet
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

