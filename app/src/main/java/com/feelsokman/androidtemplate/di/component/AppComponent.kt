package com.feelsokman.androidtemplate.di.component

import android.app.Application
import com.feelsokman.androidtemplate.TemplateApplication
import com.feelsokman.androidtemplate.di.module.AppModule
import com.feelsokman.androidtemplate.di.module.NetworkModule
import com.feelsokman.androidtemplate.di.module.UseCaseModule
import com.feelsokman.androidtemplate.di.module.ViewModelModule
import com.feelsokman.androidtemplate.di.module.WorkModule
import com.feelsokman.androidtemplate.ui.activity.MainActivity
import com.feelsokman.androidtemplate.ui.activity.di.MainActivityModule
import com.feelsokman.androidtemplate.ui.fragments.another.AnotherFragment
import com.feelsokman.androidtemplate.ui.fragments.another.di.AnotherViewModelsModule
import com.feelsokman.androidtemplate.ui.fragments.host.HostFragment
import com.feelsokman.androidtemplate.ui.fragments.host.di.HostViewModelsModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        UseCaseModule::class,
        WorkModule::class,
        ViewModelModule::class,
        HostViewModelsModule::class,
        AnotherViewModelsModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent {

    fun inject(application: TemplateApplication)
    fun inject(activity: MainActivity)
    fun inject(hostFragment: HostFragment)
    fun inject(anotherFragment: AnotherFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    companion object {
        lateinit var instance: AppComponent

        fun initAppComponent(
            application: Application,
        ): AppComponent {

            instance = DaggerAppComponent.builder()
                .application(application)
                .build()

            return instance
        }
    }
}
