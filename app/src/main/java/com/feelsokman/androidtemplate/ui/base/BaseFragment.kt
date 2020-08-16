package com.feelsokman.androidtemplate.ui.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun injectDependencies(context: Context)
}
