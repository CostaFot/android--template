package com.feelsokman.androidtemplate.dagger2.di

import com.feelsokman.androidtemplate.dagger2.SpotActivity
import com.feelsokman.androidtemplate.di.component.AppComponent
import com.feelsokman.androidtemplate.scope.FeatureScope
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [SpotModule::class]
)
@FeatureScope
interface SpotComponent {

    fun inject(spotActivity: SpotActivity)

    @Component.Builder
    interface Builder {

        fun build(): SpotComponent
        fun appComponent(appComponent: AppComponent): Builder
        fun spotModule(spotModule: SpotModule): Builder
    }
}
