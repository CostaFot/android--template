package plugins.greet

import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logging
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

    @TaskAction
    fun run() {
        val workQueue: WorkQueue = workerExecutor.noIsolation()
        workQueue.submit(PrintWork::class.java) {
            // no parameters!
        }
    }

    abstract class PrintWork : WorkAction<WorkParameters.None> {

        private val logger = Logging.getLogger(PrintWork::class.simpleName)

        override fun execute() {
            logger.lifecycle("Hello World!")
        }
    }

}
