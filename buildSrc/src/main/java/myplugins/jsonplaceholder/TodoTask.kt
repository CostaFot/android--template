package myplugins.jsonplaceholder

import com.android.build.gradle.internal.scope.GlobalScope
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.workers.WorkAction
import org.gradle.workers.WorkParameters
import org.gradle.workers.WorkQueue
import org.gradle.workers.WorkerExecutor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@Suppress("UnstableApiUsage")
open class TodoTask @Inject constructor(
    private val workerExecutor: WorkerExecutor
) : DefaultTask() {

    @Input
    var url: String? = null
    @Input
    var id: Int? = null

    @TaskAction
    fun run() {
        val workQueue: WorkQueue = workerExecutor.noIsolation()
        workQueue.submit(TodoRework::class.java) {
            url = this@TodoTask.url
            id = this@TodoTask.id
        }
    }

    abstract class TodoRework : WorkAction<TodoParams> {

        private val logger = Logging.getLogger(TodoRework::class.simpleName)

        override fun execute() {
            logger.lifecycle(parameters.url)

            val okHttpClient = OkHttpClient().newBuilder()
                .apply {
                    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    addInterceptor(httpLoggingInterceptor)
                }.build()

            logger.lifecycle("okhttp created")

            val retrofit = Retrofit.Builder()
                .apply {
                    baseUrl(parameters.url!!)
                    client(okHttpClient)
                    addConverterFactory(GsonConverterFactory.create(GsonBuilder().setPrettyPrinting().create()))
                }.build()

            logger.lifecycle("retrofit created")

            val service = retrofit.create(JsonPlaceHolderService::class.java)
            logger.lifecycle("service created")

            val response: Response<ApiTodo> = service.getTodo(parameters.id!!).execute()
            if (response.isSuccessful) {
                logger.lifecycle(response.body().toString())
            } else {
                logger.lifecycle(response.code().toString())
            }
        }
    }

    interface TodoParams : WorkParameters {
        var url: String?
        var id: Int?
    }
}
