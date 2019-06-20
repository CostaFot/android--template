package com.feelsokman.androidtemplate.ui.fragments.host

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.ui.MainActivity
import com.feelsokman.androidtemplate.ui.MainViewModel
import com.feelsokman.androidtemplate.ui.base.BaseFragment
import com.feelsokman.androidtemplate.ui.fragments.host.viewmodel.HostViewModel
import com.feelsokman.androidtemplate.ui.fragments.host.viewmodel.HostViewModelFactory
import kotlinx.android.synthetic.main.fragment_host.*
import javax.inject.Inject

class HostFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_host, container, false)
    }


    @Inject
    internal lateinit var factory: HostViewModelFactory
    private lateinit var viewModelHost: HostViewModel
    private lateinit var activityViewModel: MainViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModelHost = ViewModelProviders.of(this, factory).get(HostViewModel::class.java)

        activityViewModel = (context as MainActivity).getActivityViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button.setOnClickListener {
            findNavController().navigate(R.id.action_hostFragment_to_anotherFragment)
        }
    }

    override fun onPause() {
        super.onPause()
    }
}