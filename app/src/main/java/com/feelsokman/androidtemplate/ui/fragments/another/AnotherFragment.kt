package com.feelsokman.androidtemplate.ui.fragments.another

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.extensions.savedState
import com.feelsokman.androidtemplate.preferences.AppPreferences
import com.feelsokman.androidtemplate.ui.activity.viewmodel.MainViewModel
import com.feelsokman.androidtemplate.ui.base.BaseFragment
import com.feelsokman.androidtemplate.ui.fragments.another.viewmodel.AnotherViewModel
import com.feelsokman.androidtemplate.ui.fragments.another.viewmodel.AnotherViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class AnotherFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_another, container, false)
    }

    @Inject
    internal lateinit var appPreferences: AppPreferences

    @Inject
    internal lateinit var factory: AnotherViewModelFactory

    // Get a reference to the ViewModel scoped to this Fragment
    private val viewModelAnother by viewModels<AnotherViewModel> { factory }

    // Get a reference to the ViewModel scoped to its Activity
    private val activityViewModel by activityViewModels<MainViewModel>()

    private val state by savedState()
    private var count: Int by state.property(defaultValue = 0)
    private var text: String by state.property(
        { getString(it, "default value") },
        { key, value -> putString(key, value) })
    private var bool: Boolean by state.property(defaultValue = false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelAnother.observeStringFromStorage()

        activityViewModel.textData.observe(viewLifecycleOwner) {
            Timber.tag("NavigationLogger").e("AnotherFragment Activity string is $it")
        }

        viewModelAnother.textData.observe(viewLifecycleOwner) { stringFromStorage ->
            Timber.tag("NavigationLogger").e("AnotherFragment storage string is $stringFromStorage")
        }
    }
}
