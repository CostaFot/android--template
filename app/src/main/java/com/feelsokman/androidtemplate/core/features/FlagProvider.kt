package com.feelsokman.androidtemplate.core.features

import com.feelsokman.androidtemplate.BuildConfig
import javax.inject.Inject

class FlagProvider @Inject constructor() {
    val isDebugEnabled = BuildConfig.DEBUG
}
