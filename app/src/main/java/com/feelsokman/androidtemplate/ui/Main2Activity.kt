package com.feelsokman.androidtemplate.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.feelsokman.androidtemplate.R
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        button2.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra(KEY_PROXY, "This is some string data")
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        button3.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
