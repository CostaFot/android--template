package com.feelsokman.androidtemplate.ui.fragments.host

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.feelsokman.androidtemplate.databinding.FragmentHostBinding
import com.feelsokman.androidtemplate.di.component.AppComponent
import com.feelsokman.androidtemplate.di.getComponent
import com.feelsokman.androidtemplate.extensions.logDebug
import com.feelsokman.androidtemplate.ui.activity.viewmodel.MainViewModel
import com.feelsokman.androidtemplate.ui.base.BaseFragment
import com.feelsokman.androidtemplate.ui.fragments.host.viewmodel.HostViewModel
import com.feelsokman.androidtemplate.utilities.viewmodel.ViewModelFactory
import com.feelsokman.androidtemplate.work.DoSomethingWorker
import javax.inject.Inject

class HostFragment : BaseFragment(), ViewBinder.Callback {
    override fun onButtonClicked() {
        //
    }

    @Inject internal lateinit var viewModelFactory: ViewModelFactory
    @Inject internal lateinit var workManager: WorkManager
    private val viewModelHost by viewModels<HostViewModel> { viewModelFactory }
    private val activityViewModel by activityViewModels<MainViewModel>()

    private lateinit var viewBinder: ViewBinder

    private var _binding: FragmentHostBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        injectDependencies(context)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.textData.observe(viewLifecycleOwner) {
            logDebug { "HostFragment Activity string is $it" }
        }

        viewModelHost.textData.observe(viewLifecycleOwner) {
            if (!it.isNullOrBlank()) {
                binding.button.text = it
            }
        }

        binding.button.setOnClickListener {

            // workmanager example
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(true)
                .build()

            val uploadWorkRequest = OneTimeWorkRequestBuilder<DoSomethingWorker>()
                .setConstraints(constraints)
                .build()

            workManager.enqueue(uploadWorkRequest)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun injectDependencies(context: Context) {
        (context as AppCompatActivity).application.getComponent<AppComponent>().inject(this)
    }
}
