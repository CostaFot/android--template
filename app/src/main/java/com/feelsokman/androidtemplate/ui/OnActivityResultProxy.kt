package com.feelsokman.androidtemplate.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.feelsokman.androidtemplate.coroutine.DispatcherProvider
import com.feelsokman.androidtemplate.result.Result
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class OnActivityResultProxy(
    private val dispatcherProvider: DispatcherProvider,
    private val flowCallback: () -> Flow<Result<Throwable, String>>
) {

    @FlowPreview
    suspend fun getStringFromActivityResult(): Flow<Result<Throwable, String>> = withContext(dispatcherProvider.io) {
        flowCallback()
    }
}

class ProxyFragment : Fragment() {
    val channel = Channel<Result<Throwable, String>>(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            channel.offer(Result.Success(data!!.getStringExtra(KEY_PROXY)))
        } else {
            channel.offer(Result.Error(Throwable("Result cancelled.")))
        }
    }

    companion object {
        const val PROXY_FRAGMENT_TAG = "PROXY_FRAGMENT_TAG"
    }
}

const val KEY_PROXY = "KEY_PROXY"
const val CODE_PROXY = 999
