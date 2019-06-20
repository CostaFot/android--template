package com.feelsokman.storage

import android.content.Context
import com.chibatching.kotpref.KotprefModel

class LocalStorage(context: Context) : KotprefModel(context), Storage {

    private var someText by stringPref(key = "some_text", default = "balls")

    override fun saveSampleString(text: String) {
        someText = text
    }

    override fun getSampleString(): String {
        return someText
    }
}