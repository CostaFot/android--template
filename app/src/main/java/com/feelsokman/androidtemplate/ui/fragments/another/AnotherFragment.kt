package com.feelsokman.androidtemplate.ui.fragments.another

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.di.component.AppComponent
import com.feelsokman.androidtemplate.di.getComponent
import com.feelsokman.androidtemplate.extensions.savedState
import com.feelsokman.androidtemplate.preferences.AppPreferences
import com.feelsokman.androidtemplate.ui.activity.viewmodel.MainViewModel
import com.feelsokman.androidtemplate.ui.base.BaseFragment
import com.feelsokman.androidtemplate.ui.fragments.another.viewmodel.AnotherViewModel
import com.feelsokman.androidtemplate.utilities.viewmodel.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class AnotherFragment : BaseFragment() {

    @Inject internal lateinit var appPreferences: AppPreferences
    @Inject internal lateinit var viewModelFactory: ViewModelFactory

    private val viewModelAnother by viewModels<AnotherViewModel> { viewModelFactory }
    private val activityViewModel by activityViewModels<MainViewModel>()

    private val state by savedState()
    private var count: Int by state.property(defaultValue = 0)
    private var text: String by state.property(
        { getString(it, "default value") },
        { key, value -> putString(key, value) })
    private var bool: Boolean by state.property(defaultValue = false)

    override fun onAttach(context: Context) {
        injectDependencies(context)
        super.onAttach(context)
    }

    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                // Handle the Intent
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_another, container, false)
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

    override fun injectDependencies(context: Context) {
        (context as AppCompatActivity).application.getComponent<AppComponent>().inject(this)
    }
}
