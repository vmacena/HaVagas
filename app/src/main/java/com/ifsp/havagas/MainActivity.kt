package com.ifsp.havagas

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cbAddMobile = findViewById<CheckBox>(R.id.cb_add_mobile)
        val etMobile = findViewById<EditText>(R.id.et_mobile)
        val spEducation = findViewById<Spinner>(R.id.sp_education)
        val etGraduationYear = findViewById<EditText>(R.id.et_graduation_year)
        val etInstitution = findViewById<EditText>(R.id.et_institution)
        val etThesisTitle = findViewById<EditText>(R.id.et_thesis_title)
        val etAdvisor = findViewById<EditText>(R.id.et_advisor)
        val etInterestedJobs = findViewById<EditText>(R.id.et_interested_jobs)
        val btnSave = findViewById<Button>(R.id.btn_save)
        val btnClear = findViewById<Button>(R.id.btn_clear)

        btnClear.isEnabled = false

        cbAddMobile.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                etMobile.visibility = View.VISIBLE
            } else {
                etMobile.visibility = View.GONE
            }
        }

        spEducation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (position) {
                    0, 1 -> { // Fundamental e médio
                        etGraduationYear.visibility = View.VISIBLE
                        etInstitution.visibility = View.GONE
                        etThesisTitle.visibility = View.GONE
                        etAdvisor.visibility = View.GONE
                    }
                    2, 3 -> { // Graduação e especialização
                        etGraduationYear.visibility = View.VISIBLE
                        etInstitution.visibility = View.VISIBLE
                        etThesisTitle.visibility = View.GONE
                        etAdvisor.visibility = View.GONE
                    }
                    4, 5 -> { // Mestrado e doutorado
                        etGraduationYear.visibility = View.VISIBLE
                        etInstitution.visibility = View.VISIBLE
                        etThesisTitle.visibility = View.VISIBLE
                        etAdvisor.visibility = View.VISIBLE
                    }
                    else -> {
                        etGraduationYear.visibility = View.GONE
                        etInstitution.visibility = View.GONE
                        etThesisTitle.visibility = View.GONE
                        etAdvisor.visibility = View.GONE
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                etGraduationYear.visibility = View.GONE
                etInstitution.visibility = View.GONE
                etThesisTitle.visibility = View.GONE
                etAdvisor.visibility = View.GONE
            }
        }

        val textFields = listOf(
            findViewById<EditText>(R.id.et_full_name),
            findViewById<EditText>(R.id.et_email),
            findViewById<EditText>(R.id.et_phone),
            findViewById<EditText>(R.id.et_mobile),
            findViewById<EditText>(R.id.et_birth_date),
            etGraduationYear,
            etInstitution,
            etThesisTitle,
            etAdvisor,
            etInterestedJobs
        )

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btnClear.isEnabled = textFields.any { it.text.isNotEmpty() }
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        textFields.forEach { it.addTextChangedListener(textWatcher) }

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
        val graduationYear = findViewById<EditText>(R.id.et_graduation_year).text.toString()
        val institution = findViewById<EditText>(R.id.et_institution).text.toString()
        val thesisTitle = findViewById<EditText>(R.id.et_thesis_title).text.toString()
        val advisor = findViewById<EditText>(R.id.et_advisor).text.toString()
        val interestedJobs = findViewById<EditText>(R.id.et_interested_jobs).text.toString()

        val message = """
            Dados salvos:
            Nome: $fullName
            Email: $email
            Telefone: $phone
            Celular: $mobile
            Data de Nascimento: $birthDate
            Formação: $education
            Ano de Conclusão: $graduationYear
            Instituição: $institution
            Título da Monografia: $thesisTitle
            Orientador: $advisor
            Vagas de Interesse: $interestedJobs
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
        findViewById<EditText>(R.id.et_graduation_year).text.clear()
        findViewById<EditText>(R.id.et_institution).text.clear()
        findViewById<EditText>(R.id.et_thesis_title).text.clear()
        findViewById<EditText>(R.id.et_advisor).text.clear()
        findViewById<EditText>(R.id.et_interested_jobs).text.clear()
        findViewById<Button>(R.id.btn_clear).isEnabled = false
    }
}