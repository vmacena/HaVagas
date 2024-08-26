package com.ifsp.havagas

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cbAddMobile = findViewById<CheckBox>(R.id.cb_add_mobile)
        val etMobile = findViewById<EditText>(R.id.et_mobile)

        cbAddMobile.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                etMobile.visibility = View.VISIBLE
            } else {
                etMobile.visibility = View.GONE
            }
        }
    }
}