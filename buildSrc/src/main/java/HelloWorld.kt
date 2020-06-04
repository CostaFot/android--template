import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

// Keep the file open otherwise gradle won't be able to generate the proxy class.
open class HelloWorld : DefaultTask() {

    init {
        group = "mycustomtasks" // This will be the group name for your task.
        description = "Writes hello world"
    }

    @TaskAction // Marks a function as the action to run when the task is executed.
    fun run() {
        println("Hello World!") // Prints to run log.
    }
}
