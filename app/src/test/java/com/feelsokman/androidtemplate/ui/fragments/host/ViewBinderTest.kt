package com.feelsokman.androidtemplate.ui.fragments.host

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.feelsokman.androidtemplate.R
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class ViewBinderTest {

    lateinit var viewBinder: ViewBinder

    lateinit var appContext: Context

    @Mock
    lateinit var callback: ViewBinder.Callback

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        appContext = ApplicationProvider.getApplicationContext()
        val view = LayoutInflater.from(appContext).inflate(R.layout.fragment_host, null)

        viewBinder = ViewBinder(view, callback)
    }

    @Test
    fun saveState() {
        val savedbundle = Bundle().apply {
            putString(ViewBinder.SAVED_BUTTON_TEXT, "ahahahah")
        }
        viewBinder.saveState(savedbundle)
    }

    @Test
    fun restoreStateWorksWhenBundleNotNull() {
        val restoreBundle = Bundle().apply {
            putString(ViewBinder.SAVED_BUTTON_TEXT, "ahahahah")
        }
        viewBinder.restoreState(restoreBundle)

        assertTrue(viewBinder.button.text == "ahahahah")
    }

    @Test
    fun restoreStateDefaultsWhenStateIsNull() {

        viewBinder.restoreState(null)

        assertTrue(viewBinder.button.text == "GO TO ANOTHER FRAGMENT")
    }

    @Test
    fun changeButtonText() {
        val textBefore = viewBinder.button.text.toString()
        viewBinder.changeButtonText()

        val after = viewBinder.button.text.toString()

        assertTrue(textBefore != after)
    }
}
