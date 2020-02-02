package com.feelsokman.androidtemplate.dagger2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.TemplateApplication
import com.feelsokman.androidtemplate.dagger2.di.DaggerSpotComponent
import com.feelsokman.androidtemplate.dagger2.di.SpotComponent
import com.feelsokman.androidtemplate.dagger2.di.SpotModule
import timber.log.Timber
import javax.inject.Inject

class SpotActivity : AppCompatActivity() {

    @Inject
    internal lateinit var spotActivityViewModelFactory: SpotActivityViewModelFactory

    private lateinit var spotActivityViewModel: SpotActivityViewModel

    lateinit var spotComponent: SpotComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val number = intent.getIntExtra(EXTRA_INT, 30)
        injectDependencies(number)

        spotActivityViewModel =
            ViewModelProviders.of(this, spotActivityViewModelFactory).get(SpotActivityViewModel::class.java)

        setContentView(R.layout.activity_spot)
    }

    private fun injectDependencies(number: Int) {
        if (!::spotComponent.isInitialized) {
            Timber.e("Component is not initialised")
            spotComponent = DaggerSpotComponent.builder()
                .appComponent(TemplateApplication.component)
                .spotModule(SpotModule(number))
                .build()
        }
        spotComponent.inject(this)
    }

    companion object {
        const val EXTRA_INT = "EXTRA_INT"
    }
}
