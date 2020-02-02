package com.feelsokman.androidtemplate.dagger2.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.dagger2.SpotActivity
import com.feelsokman.androidtemplate.dagger2.SpotActivityViewModel
import kotlinx.android.synthetic.main.fragment_spot.*

class SpotFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_spot, container, false)
    }

    // Get a reference to the ViewModel scoped to its Activity
    private val activityViewModel by activityViewModels<SpotActivityViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as SpotActivity).spotComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.textData.observe(viewLifecycleOwner, Observer {
            textView3.text = it
        })

        activityViewModel.wrapperData.observe(viewLifecycleOwner, Observer {
            textView4.text = it
        })
    }
}
