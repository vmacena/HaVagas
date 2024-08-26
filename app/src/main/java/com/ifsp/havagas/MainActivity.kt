package com.ifsp.havagas

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cbAddMobile = findViewById<CheckBox>(R.id.cb_add_mobile)
        val etMobile = findViewById<EditText>(R.id.et_mobile)
        val btnSave = findViewById<Button>(R.id.btn_save)
        val btnClear = findViewById<Button>(R.id.btn_clear)

        cbAddMobile.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                etMobile.visibility = View.VISIBLE
            } else {
                etMobile.visibility = View.GONE
            }
        }

        btnSave.setOnClickListener {
            saveForm()
        }

        btnClear.setOnClickListener {
            clearForm()
        }
    }

    private fun saveForm() {
        val fullName = findViewById<EditText>(R.id.et_full_name).text.toString()
        val email = findViewById<EditText>(R.id.et_email).text.toString()
        val receiveUpdates = findViewById<CheckBox>(R.id.cb_receive_updates).isChecked
        val phone = findViewById<EditText>(R.id.et_phone).text.toString()
        val phoneType = findViewById<RadioGroup>(R.id.rg_phone_type).checkedRadioButtonId
        val addMobile = findViewById<CheckBox>(R.id.cb_add_mobile).isChecked
        val mobile = findViewById<EditText>(R.id.et_mobile).text.toString()
        val gender = findViewById<RadioGroup>(R.id.rg_gender).checkedRadioButtonId
        val birthDate = findViewById<EditText>(R.id.et_birth_date).text.toString()
        val education = findViewById<Spinner>(R.id.sp_education).selectedItem.toString()

        val message = """
            Dados salvos:
            Nome: $fullName
            Email: $email
            Telefone: $phone
            Celular: $mobile
            Data de Nascimento: $birthDate
            Formação: $education
        """.trimIndent()

        AlertDialog.Builder(this)
            .setTitle("Formulário Salvo")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun clearForm() {
        findViewById<EditText>(R.id.et_full_name).text.clear()
        findViewById<EditText>(R.id.et_email).text.clear()
        findViewById<CheckBox>(R.id.cb_receive_updates).isChecked = false
        findViewById<EditText>(R.id.et_phone).text.clear()
        findViewById<RadioGroup>(R.id.rg_phone_type).clearCheck()
        findViewById<CheckBox>(R.id.cb_add_mobile).isChecked = false
        findViewById<EditText>(R.id.et_mobile).text.clear()
        findViewById<EditText>(R.id.et_mobile).visibility = View.GONE
        findViewById<RadioGroup>(R.id.rg_gender).clearCheck()
        findViewById<EditText>(R.id.et_birth_date).text.clear()
        findViewById<Spinner>(R.id.sp_education).setSelection(0)
    }
}