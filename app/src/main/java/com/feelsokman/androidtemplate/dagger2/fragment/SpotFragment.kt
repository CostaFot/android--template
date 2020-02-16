package com.feelsokman.androidtemplate.dagger2.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.dagger2.SpotActivity
import com.feelsokman.androidtemplate.dagger2.SpotActivityViewModel
import kotlinx.android.synthetic.main.fragment_spot.*

class SpotFragment : Fragment(R.layout.fragment_spot) {

    // Get a reference to the ViewModel scoped to its Activity
    private val activityViewModel by activityViewModels<SpotActivityViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as SpotActivity).spotComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityViewModel.wrapperData.observe(viewLifecycleOwner) {
            textView4.text = it
        }

        butt.setOnClickListener {
            activityViewModel.go()
        }
    }
}
