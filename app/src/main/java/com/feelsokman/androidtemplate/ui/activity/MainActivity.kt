package com.feelsokman.androidtemplate.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import com.feelsokman.androidtemplate.R
import com.feelsokman.androidtemplate.di.component.AppComponent
import com.feelsokman.androidtemplate.di.getComponent
import com.feelsokman.androidtemplate.extensions.logDebug
import com.feelsokman.androidtemplate.ui.activity.viewmodel.MainViewModel
import com.feelsokman.androidtemplate.ui.base.BaseActivity
import com.feelsokman.androidtemplate.utilities.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject internal lateinit var viewModelFactory: ViewModelFactory

    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initial setup!
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.hostFragment -> logDebug { "hostFragment showing!" }
                R.id.anotherFragment -> logDebug { "anotherFragment showing!" }
            }
        }

        mainViewModel.textData.observe(this) {
            logDebug { "MainActivity $it" }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return item.onNavDestinationSelected(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun injectDependencies() {
        application.getComponent<AppComponent>().inject(this)
    }

}
