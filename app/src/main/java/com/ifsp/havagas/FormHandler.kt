package com.ifsp.havagas

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.widget.*

class FormHandler(private val context: Context) {

    fun saveForm(
        fullName: String,
        email: String,
        receiveUpdates: Boolean,
        phone: String,
        phoneType: Int,
        addMobile: Boolean,
        mobile: String,
        gender: Int,
        birthDate: String,
        education: String,
        graduationYear: String,
        institution: String,
        thesisTitle: String,
        advisor: String,
        interestedJobs: String
    ) {
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

        AlertDialog.Builder(context)
            .setTitle("Formulário Salvo")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    fun clearForm(
        fullName: EditText,
        email: EditText,
        receiveUpdates: CheckBox,
        phone: EditText,
        phoneType: RadioGroup,
        addMobile: CheckBox,
        mobile: EditText,
        gender: RadioGroup,
        birthDate: EditText,
        education: Spinner,
        graduationYear: EditText,
        institution: EditText,
        thesisTitle: EditText,
        advisor: EditText,
        interestedJobs: EditText,
        btnClear: Button,
        btnSave: Button
    ) {
        fullName.text.clear()
        email.text.clear()
        receiveUpdates.isChecked = false
        phone.text.clear()
        phoneType.clearCheck()
        addMobile.isChecked = false
        mobile.text.clear()
        mobile.visibility = View.GONE
        gender.clearCheck()
        birthDate.text.clear()
        education.setSelection(0)
        graduationYear.text.clear()
        institution.text.clear()
        thesisTitle.text.clear()
        advisor.text.clear()
        interestedJobs.text.clear()
        btnClear.isEnabled = false
        btnSave.isEnabled = false
    }
}