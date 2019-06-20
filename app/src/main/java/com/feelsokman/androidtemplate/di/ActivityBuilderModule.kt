package com.feelsokman.androidtemplate.di

import com.feelsokman.androidtemplate.ui.MainActivity
import com.feelsokman.androidtemplate.ui.fragments.another.AnotherFragment
import com.feelsokman.androidtemplate.ui.fragments.host.HostFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun hostFragment(): HostFragment

    @ContributesAndroidInjector
    abstract fun anotherFragment(): AnotherFragment
}
