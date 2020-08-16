package com.feelsokman.androidtemplate.core.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

object ApplicationScope : CoroutineScope by CoroutineScope(SupervisorJob())
