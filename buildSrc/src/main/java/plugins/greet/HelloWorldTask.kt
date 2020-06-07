package plugins.greet

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import org.gradle.workers.WorkQueue
import org.gradle.workers.WorkerExecutor
import java.io.File
import javax.inject.Inject

@Suppress("UnstableApiUsage")
open class HelloWorldTask @Inject constructor(
    private val workerExecutor: WorkerExecutor
) : DefaultTask() {

    @Input
    var message: String? = null

    @Input
    var temperature: Int? = null

    @TaskAction
    fun run() {
        val workQueue: WorkQueue = workerExecutor.noIsolation()

        workQueue.submit(HelloReportWork::class.java) {
            message = this@HelloWorldTask.message
            temperature = this@HelloWorldTask.temperature
            projectDir = project.projectDir
        }
    }

    abstract class HelloReportWork : WorkAction<GreetParams> {

        private val logger = Logging.getLogger(HelloReportWork::class.simpleName)

        override fun execute() {
            logger.lifecycle(parameters.message)
            logger.lifecycle("The temperature today is ${parameters.temperature}")
            logger.lifecycle(parameters.projectDir.absolutePath)

            val builder = StringBuilder()
            val layoutsDir = File("${parameters.projectDir}/src/main/res/layout")
            layoutsDir.walkTopDown().filter {
                it.name.endsWith(".xml")
            }.forEach {
                logger.lifecycle(it.name)
                val text = it.readText()
                if (text.contains("@color/")) {
                    throw GradleException("Found hardcoded colour assigned in layout file ${it.name}")
                }
                builder.appendln(it.name)
            }

            // File(parameters.projectDir, "choppa.txt").writeText(builder.toString())

            // read stuff in gitignore file for fun
            val ff = File(parameters.projectDir, ".gitignore").readText()
            logger.lifecycle(ff)
        }
    }

    interface GreetParams : WorkParameters {
        var message: String?
        var temperature: Int?
        var projectDir: File
    }
}
