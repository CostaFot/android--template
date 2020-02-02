package com.feelsokman.androidtemplate.dagger2.di

import com.feelsokman.androidtemplate.dagger2.SpotActivityViewModelFactory
import com.feelsokman.androidtemplate.dagger2.Wrapper
import dagger.Module
import dagger.Provides

@Module
class SpotModule(private val number: Int) {

    @Provides
    fun providesWrapperOfNumber(): Wrapper {
        return Wrapper(number)
    }

    @Provides
    internal fun providesSpotActivityViewModelFactory(wrapper: Wrapper) = SpotActivityViewModelFactory(wrapper)
}
