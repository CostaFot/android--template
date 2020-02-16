package com.feelsokman.androidtemplate.dagger2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.TemplateApplication
import com.feelsokman.androidtemplate.dagger2.di.DaggerSpotComponent
import com.feelsokman.androidtemplate.dagger2.di.SpotComponent
import timber.log.Timber
import javax.inject.Inject

class SpotActivity : AppCompatActivity() {

    @Inject
    internal lateinit var spotActivityViewModelFactory: SpotActivityViewModelFactory

    private lateinit var spotActivityViewModel: SpotActivityViewModel

    private val provider: () -> ViewModelProvider = { ViewModelProviders.of(this, spotActivityViewModelFactory) }

    lateinit var spotComponent: SpotComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()

        spotActivityViewModel = provider().get(SpotActivityViewModel::class.java)

        setContentView(R.layout.activity_spot)
    }

    private fun injectDependencies() {
        if (!::spotComponent.isInitialized) {
            Timber.e("Component is not initialised")
            spotComponent = DaggerSpotComponent.builder()
                .activity(this)
                .appComponent(TemplateApplication.component)
                .build()
        }
        spotComponent.inject(this)
    }
}
