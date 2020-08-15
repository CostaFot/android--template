package myplugins.jsonplaceholder

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.invoke
import myplugins.greet.GreetingPluginExtension
import myplugins.greet.HelloWorldTask

@Suppress("unused")
class TodoPlugin : Plugin<Project> {

    override fun apply(project: Project): Unit = project.run {

        val extension = project.extensions.create<TodoPluginExtension>("todo")

        tasks {
            register("todo", TodoTask::class.java) {
                group = "customPlugins"
                description = "Fetches a TODO from jsonplaceholderapi"
                url = extension.url
                id = extension.id
            }
        }
    }
}
