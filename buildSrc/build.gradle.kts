import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    `kotlin-dsl`
    id("com.github.ben-manes.versions") version  "0.28.0"
}

repositories {
    mavenCentral()
    google()
    jcenter()
}


dependencies {
    /* Example Dependency */
    /* Depend on the android gradle plugin, since we want to access it in our plugin */
    implementation("com.android.tools.build:gradle:4.0.1")

    /* Example Dependency */
    /* Depend on the kotlin plugin, since we want to access it in our plugin */
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    /* Depend on the default Gradle API's since we want to build a custom plugin */
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("com.beust:klaxon:5.0.1")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.squareup.okhttp3:okhttp:4.4.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.4.0")
    implementation("com.squareup.retrofit2:retrofit:2.7.1") {
        exclude("com.squareup.okhttp3", "okhttp")
    }
    implementation("com.squareup.retrofit2:converter-gson:2.7.1") {
        exclude("com.google.code.gson", "gson")
    }
}

gradlePlugin {
    plugins {
        register("greet-plugin") {
            id = "greet"
            implementationClass = "myplugins.greet.GreetPlugin"
        }
        register("todo-plugin") {
            id = "todo"
            implementationClass = "myplugins.jsonplaceholder.TodoPlugin"
        }
        register("packaging-plugin") {
            id = "packaging"
            implementationClass = "myplugins.packaging.PackagingPlugin"
        }
    }
}
