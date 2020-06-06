plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
}

dependencies {
    /* Example Dependency */
    /* Depend on the android gradle plugin, since we want to access it in our plugin */
    implementation("com.android.tools.build:gradle:4.0.0")

    /* Example Dependency */
    /* Depend on the kotlin plugin, since we want to access it in our plugin */
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    /* Depend on the default Gradle API's since we want to build a custom plugin */
    implementation(gradleApi())
    implementation(localGroovy())

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
            implementationClass = "plugins.greet.GreetPlugin"
        }
        register("todo-plugin") {
            id = "todo"
            implementationClass = "plugins.jsonplaceholder.TodoPlugin"
        }
        register("packaging-plugin") {
            id = "packaging"
            implementationClass = "plugins.packaging.PackagingPlugin"
        }
    }
}
