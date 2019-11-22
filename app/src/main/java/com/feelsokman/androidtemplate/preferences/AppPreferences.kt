package com.feelsokman.androidtemplate.preferences

interface AppPreferences {

    fun saveSampleString(text: String)

    fun getSampleString(): String
}
