package com.feelsokman.androidtemplate.dagger2.di

import android.app.Activity
import com.feelsokman.androidtemplate.dagger2.SpotActivity
import com.feelsokman.androidtemplate.dagger2.fragment.SpotFragment
import com.feelsokman.androidtemplate.di.component.AppComponent
import com.feelsokman.androidtemplate.scope.FeatureScope
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [SpotModule::class]
)
@FeatureScope
interface SpotComponent {

    fun inject(spotActivity: SpotActivity)
    fun inject(spotFragment: SpotFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun activity(spotActivity: Activity): Builder

        fun build(): SpotComponent
        fun appComponent(appComponent: AppComponent): Builder
    }
}
