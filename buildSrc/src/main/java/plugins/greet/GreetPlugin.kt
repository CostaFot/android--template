package plugins.greet

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.invoke

@Suppress("unused")
class GreetPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {

        val extension = project.extensions.create<GreetingPluginExtension>("greeting")

        tasks {
            register("greet", HelloWorldTask::class.java) {
                group = "customPlugins"
                description = "Prints a greeting and the weather supposedly."
                message = extension.message
                temperature = extension.temperature
            }
        }
    }
}
