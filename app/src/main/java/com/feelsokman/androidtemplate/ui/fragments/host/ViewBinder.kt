package com.feelsokman.androidtemplate.ui.fragments.host

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.feelsokman.androidtemplate.R
import timber.log.Timber
import java.util.UUID

class ViewBinder(private val view: View, private val callback: Callback) {

    interface Callback {
        fun onButtonClicked()
    }

    var button: Button = view.findViewById(R.id.button)

    init {
        button.setOnClickListener {
            changeButtonText()
        }
    }

    fun saveState(outState: Bundle) {
        outState.putString(SAVED_BUTTON_TEXT, button.text.toString())
    }

    fun restoreState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            return
        }
        when (val text: String? = savedInstanceState.getString(SAVED_BUTTON_TEXT)) {
            null -> Timber.e("no text saved")
            else -> button.text = text
        }
    }

    fun changeButtonText() {
        button.text = UUID.randomUUID().toString()
    }

    companion object {
        const val SAVED_BUTTON_TEXT = "SAVED_BUTTON_TEXT"
    }
}
