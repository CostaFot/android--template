package com.feelsokman.androidtemplate.ui.fragments.host.di

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.feelsokman.androidtemplate.coroutine.DispatcherProvider
import com.feelsokman.androidtemplate.net.domain.JsonPlaceHolderClient
import com.feelsokman.androidtemplate.ui.CODE_PROXY
import com.feelsokman.androidtemplate.ui.Main2Activity
import com.feelsokman.androidtemplate.ui.OnActivityResultProxy
import com.feelsokman.androidtemplate.ui.ProxyFragment
import com.feelsokman.androidtemplate.ui.activity.MainActivity
import com.feelsokman.androidtemplate.ui.fragments.host.viewmodel.HostViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.consumeAsFlow

@Module
class HostModule {

    @FlowPreview
    @Provides
    fun providesOnActivityResultProxy(
        activity: MainActivity,
        dispatcherProvider: DispatcherProvider
    ): OnActivityResultProxy {
        val fragmentActivity = activity as FragmentActivity
        var fragment: ProxyFragment? =
            fragmentActivity.supportFragmentManager.findFragmentByTag(ProxyFragment.PROXY_FRAGMENT_TAG) as ProxyFragment?

        if (fragment == null) {
            fragment = ProxyFragment().also {
                fragmentActivity.supportFragmentManager.commit {
                    add(it, ProxyFragment.PROXY_FRAGMENT_TAG)
                }
            }
        }

        return OnActivityResultProxy(
            dispatcherProvider = dispatcherProvider,
            flowCallback = {
                fragment.channel.consumeAsFlow().also {
                    // guess this could be a library working with activity result too or something
                    fragment.run {
                        startActivityForResult(
                            Intent(requireContext(), Main2Activity::class.java),
                            CODE_PROXY
                        )
                    }
                }
            }
        )
    }

    @Provides
    internal fun providesHostViewModelFactory(
        jsonPlaceHolderClient: JsonPlaceHolderClient,
        onActivityResultProxy: OnActivityResultProxy
    ): HostViewModelFactory = HostViewModelFactory(jsonPlaceHolderClient, onActivityResultProxy)
}
