package com.feelsokman.androidtemplate.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun injectDependencies()
}
