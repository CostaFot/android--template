package com.feelsokman.androidtemplate.ui.fragments.another

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.ui.MainActivity
import com.feelsokman.androidtemplate.ui.MainViewModel
import com.feelsokman.androidtemplate.ui.base.BaseFragment
import com.feelsokman.androidtemplate.ui.fragments.another.viewmodel.AnotherViewModel
import com.feelsokman.androidtemplate.ui.fragments.another.viewmodel.AnotherViewModelFactory
import com.feelsokman.storage.Storage
import es.dmoral.toasty.Toasty
import timber.log.Timber
import javax.inject.Inject

class AnotherFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_another, container, false)
    }

    private val args: AnotherFragmentArgs by navArgs()
    @Inject
    internal lateinit var storage: Storage
    @Inject
    internal lateinit var factory: AnotherViewModelFactory
    private lateinit var viewModelAnother: AnotherViewModel
    private lateinit var activityViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Retrieving arguments if any
        val stringArgument = args.extraAnotherFragment
        Timber.tag("NavigationLogger").d("Retrieving argument $stringArgument")

        viewModelAnother = ViewModelProviders.of(this, factory).get(stringArgument, AnotherViewModel::class.java)

        activityViewModel = (context as MainActivity).getActivityViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toasty.error(view.context, storage.getSampleString()).show()
    }

    override fun onPause() {
        super.onPause()
    }
}