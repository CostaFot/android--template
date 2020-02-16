package com.feelsokman.androidtemplate.dagger2.di

import android.app.Activity
import com.feelsokman.androidtemplate.dagger2.SpotActivityViewModelFactory
import com.feelsokman.androidtemplate.dagger2.Wrapper
import dagger.Module
import dagger.Provides

@Module
object SpotModule {

    const val EXTRA_INT = "EXTRA_INT"

    @Provides
    fun providesWrapperOfNumber(activity: Activity): Wrapper {
        val number = activity.intent.getIntExtra(EXTRA_INT, 30)
        return Wrapper(number)
    }

    @Provides
    internal fun providesSpotActivityViewModelFactory(wrapper: Wrapper, activity: Activity) =
        SpotActivityViewModelFactory(wrapper, activity)
}
