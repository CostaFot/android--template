package com.feelsokman.androidtemplate.di.module

import com.feelsokman.androidtemplate.ui.fragments.another.AnotherFragment
import com.feelsokman.androidtemplate.ui.fragments.another.di.AnotherModule
import com.feelsokman.androidtemplate.ui.fragments.host.HostFragment
import com.feelsokman.androidtemplate.ui.fragments.host.di.HostViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [HostViewModelsModule::class])
    abstract fun hostFragment(): HostFragment

    @ContributesAndroidInjector(modules = [AnotherModule::class])
    abstract fun anotherFragment(): AnotherFragment
}
