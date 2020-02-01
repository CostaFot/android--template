package com.feelsokman.androidtemplate.dagger2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.TemplateApplication
import com.feelsokman.androidtemplate.dagger2.di.DaggerSpotComponent
import com.feelsokman.androidtemplate.dagger2.di.SpotComponent
import com.feelsokman.androidtemplate.dagger2.di.SpotModule
import kotlinx.android.synthetic.main.activity_spot.*
import timber.log.Timber
import javax.inject.Inject

class SpotActivity : AppCompatActivity() {

    @Inject
    internal lateinit var wrapper: Wrapper

    lateinit var spotComponent: SpotComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spot)

        val number = intent.getIntExtra(EXTRA_INT, 30)
        injectDependencies(number)

        textView.text = wrapper.number.toString()
    }

    private fun injectDependencies(number: Int) {
        if (!::spotComponent.isInitialized) {
            Timber.e("Component is not initialised")
            spotComponent = DaggerSpotComponent.builder()
                .appComponent(TemplateApplication.component)
                .spotModule(SpotModule(number))
                .build()

            spotComponent.inject(this)
        } else {
            Timber.e("Component is initialised")
        }
    }

    companion object {
        const val EXTRA_INT = "EXTRA_INT"
    }
}
