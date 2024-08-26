package com.ifsp.havagas

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var formHandler: FormHandler

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formHandler = FormHandler(this)

        val cbAddMobile: CheckBox = findViewById(R.id.cb_add_mobile)
        val etMobile: EditText = findViewById(R.id.et_mobile)
        val spEducation: Spinner = findViewById(R.id.sp_education)
        val etGraduationYear: EditText = findViewById(R.id.et_graduation_year)
        val etInstitution: EditText = findViewById(R.id.et_institution)
        val etThesisTitle: EditText = findViewById(R.id.et_thesis_title)
        val etAdvisor: EditText = findViewById(R.id.et_advisor)
        val etInterestedJobs: EditText = findViewById(R.id.et_interested_jobs)
        val btnSave: Button = findViewById(R.id.btn_save)
        val btnClear: Button = findViewById(R.id.btn_clear)
        val etPhone: EditText = findViewById(R.id.et_phone)
        val etBirthDate: EditText = findViewById(R.id.et_birth_date)

        btnClear.isEnabled = false
        btnSave.isEnabled = false

        cbAddMobile.setOnCheckedChangeListener { _, isChecked ->
            etMobile.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        spEducation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> {
                        etGraduationYear.visibility = View.GONE
                        etInstitution.visibility = View.GONE
                        etThesisTitle.visibility = View.GONE
                        etAdvisor.visibility = View.GONE
                    }
                    1 -> {
                        etGraduationYear.visibility = View.VISIBLE
                        etInstitution.visibility = View.GONE
                        etThesisTitle.visibility = View.GONE
                        etAdvisor.visibility = View.GONE
                    }
                    2, 3 -> {
                        etGraduationYear.visibility = View.VISIBLE
                        etInstitution.visibility = View.VISIBLE
                        etThesisTitle.visibility = View.GONE
                        etAdvisor.visibility = View.GONE
                    }
                    4, 5, 6 -> {
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
            etPhone,
            etMobile,
            etBirthDate,
            etGraduationYear,
            etInstitution,
            etThesisTitle,
            etAdvisor,
            etInterestedJobs
        )

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val isAnyFieldNotEmpty = textFields.any { it.text.isNotEmpty() }
                btnClear.isEnabled = isAnyFieldNotEmpty
                btnSave.isEnabled = isAnyFieldNotEmpty && isPhoneValid(etPhone.text.toString()) && isPhoneValid(etMobile.text.toString()) && isDateValid(etBirthDate.text.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        textFields.forEach { it.addTextChangedListener(textWatcher) }

        etPhone.addTextChangedListener(createPhoneTextWatcher(etPhone))
        etMobile.addTextChangedListener(createPhoneTextWatcher(etMobile))
        etBirthDate.addTextChangedListener(createDateTextWatcher(etBirthDate))

        btnSave.setOnClickListener {
            formHandler.saveForm(
                findViewById<EditText>(R.id.et_full_name).text.toString(),
                findViewById<EditText>(R.id.et_email).text.toString(),
                findViewById<CheckBox>(R.id.cb_receive_updates).isChecked,
                findViewById<EditText>(R.id.et_phone).text.toString(),
                findViewById<RadioGroup>(R.id.rg_phone_type).checkedRadioButtonId,
                findViewById<CheckBox>(R.id.cb_add_mobile).isChecked,
                findViewById<EditText>(R.id.et_mobile).text.toString(),
                findViewById<RadioGroup>(R.id.rg_gender).checkedRadioButtonId,
                findViewById<EditText>(R.id.et_birth_date).text.toString(),
                findViewById<Spinner>(R.id.sp_education).selectedItem.toString(),
                findViewById<EditText>(R.id.et_graduation_year).text.toString(),
                findViewById<EditText>(R.id.et_institution).text.toString(),
                findViewById<EditText>(R.id.et_thesis_title).text.toString(),
                findViewById<EditText>(R.id.et_advisor).text.toString(),
                findViewById<EditText>(R.id.et_interested_jobs).text.toString()
            )
        }

        btnClear.setOnClickListener {
            formHandler.clearForm(
                findViewById(R.id.et_full_name),
                findViewById(R.id.et_email),
                findViewById(R.id.cb_receive_updates),
                findViewById(R.id.et_phone),
                findViewById(R.id.rg_phone_type),
                findViewById(R.id.cb_add_mobile),
                findViewById(R.id.et_mobile),
                findViewById(R.id.rg_gender),
                findViewById(R.id.et_birth_date),
                findViewById(R.id.sp_education),
                findViewById(R.id.et_graduation_year),
                findViewById(R.id.et_institution),
                findViewById(R.id.et_thesis_title),
                findViewById(R.id.et_advisor),
                findViewById(R.id.et_interested_jobs),
                findViewById(R.id.btn_clear),
                findViewById(R.id.btn_save)
            )
        }
    }

    private fun createPhoneTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            private var isFormatting: Boolean = false
            private var deletingHyphen: Boolean = false
            private var hyphenStart: Int = 0

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (isFormatting) return
                if (count > 0 && s?.get(start) == '-') {
                    deletingHyphen = true
                    hyphenStart = start
                } else {
                    deletingHyphen = false
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isFormatting) return
                if (deletingHyphen && start == hyphenStart) return
            }

            override fun afterTextChanged(s: Editable?) {
                if (isFormatting) return
                isFormatting = true

                val formatted = formatPhoneNumber(s.toString())
                editText.setText(formatted)
                editText.setSelection(formatted.length)

                isFormatting = false
            }
        }
    }

    private fun createDateTextWatcher(editText: EditText): TextWatcher {
        return object : TextWatcher {
            private var isFormatting: Boolean = false

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (isFormatting) return
                isFormatting = true

                val formatted = formatDate(s.toString())
                editText.setText(formatted)
                editText.setSelection(formatted.length)

                isFormatting = false
            }
        }
    }

    private fun formatPhoneNumber(phone: String): String {
        val cleaned = phone.replace(Regex("[^\\d]"), "")
        val match = Regex("(\\d{2})(\\d{4,5})(\\d{4})").find(cleaned)
        return if (match != null) {
            val (ddd, part1, part2) = match.destructured
            "($ddd) $part1-$part2"
        } else {
            phone
        }
    }

    private fun formatDate(date: String): String {
        val cleaned = date.replace(Regex("[^\\d]"), "")
        val match = Regex("(\\d{2})(\\d{2})(\\d{4})").find(cleaned)
        return if (match != null) {
            val (day, month, year) = match.destructured
            "$day/$month/$year"
        } else {
            date
        }
    }

    private fun isPhoneValid(phone: String): Boolean {
        val regex = Regex("^\\(\\d{2}\\) \\d{4,5}-\\d{4}\$")
        return regex.matches(phone)
    }

    private fun isDateValid(date: String): Boolean {
        val regex = Regex("^\\d{2}/\\d{2}/\\d{4}\$")
        return regex.matches(date)
    }
}