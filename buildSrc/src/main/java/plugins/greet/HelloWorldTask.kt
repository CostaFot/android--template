package plugins.greet

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import org.gradle.workers.WorkQueue
import org.gradle.workers.WorkerExecutor
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
        workQueue.submit(WeatherReportWork::class.java) {
            message = this@HelloWorldTask.message
            temperature = this@HelloWorldTask.temperature
        }
    }

    abstract class WeatherReportWork : WorkAction<WeatherParams> {

        private val logger = Logging.getLogger(WeatherReportWork::class.simpleName)

        override fun execute() {
            logger.lifecycle(parameters.message)
            logger.lifecycle("The temperature today is ${parameters.temperature}")
        }
    }

    interface WeatherParams : WorkParameters {
        var message: String?
        var temperature: Int?
    }
}
