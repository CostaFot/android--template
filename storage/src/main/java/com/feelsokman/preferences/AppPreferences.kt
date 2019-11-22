package com.feelsokman.preferences

interface AppPreferences {

    fun saveSampleString(text: String)

    fun getSampleString(): String
}
