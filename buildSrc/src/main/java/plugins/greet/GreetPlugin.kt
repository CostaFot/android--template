package plugins.greet

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke

@Suppress("unused")
class GreetPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {

        tasks {
            register("greet", HelloWorldTask::class.java) {
                group = "sample"
                description = "Prints a description of ${project.name}."
                group = "print"
                description = "Writes hello world"
            }
        }
    }


}
