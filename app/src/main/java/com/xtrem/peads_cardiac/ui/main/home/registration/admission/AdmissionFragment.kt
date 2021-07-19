package com.xtrem.peads_cardiac.ui.main.home.registration.admission


import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import kotlinx.android.synthetic.main.fragment_admission.*
import java.util.*

class AdmissionFragment : Fragment() {

    private lateinit var admissionViewModel: AdmissionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etAdmissionDate.setOnClickListener { showDatePicker(etAdmissionDate) }
        etDischarge.setOnClickListener { showDatePicker(etDischarge) }

        admissionViewModel = ViewModelProvider(this).get(AdmissionViewModel::class.java)

        rgAdmission.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbYes -> "Yes".validatePositiveResults()
                R.id.rbNo -> "No".validateNegativeResults()
            }
        }

        rgAdmissionOutcome.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbDeath -> validateDeath()
                R.id.rbAlive -> validateAlive()
            }
        }

        btnNext.setOnClickListener {

            if (getAdmissionStatus().isEmpty()) {
                AlertDialog.Builder(activity).setTitle("Validation Error")
                    .setMessage("Please indicate admission status")
                    .setNegativeButton("Ok") { dialogInterface, i -> dialogInterface.dismiss() }
                    .show()
                return@setOnClickListener
            }
            if (getAdmissionStatus().equals("Yes", true)) {

                "Yes".validatePositiveResults()

            } else {

                "No".validateNegativeResults()
            }


        }

        btnBack.setOnClickListener {

            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }

    private fun validateAlive() {
        itDeathReason.visibility = View.GONE
    }

    private fun validateDeath() {
        itDeathReason.visibility = View.VISIBLE
    }

    private fun String.validateNegativeResults() {
        itGeneralOutcome.visibility = View.VISIBLE
        layoutAdmitted.visibility = View.GONE

        val generalOutcome = etGeneralOutcome.text.toString()

        if (generalOutcome.isEmpty()) {
            itGeneralOutcome.error = "Please enter general outcome"
            return
        }
        admissionViewModel.saveData(
            this, "", "", "", "", "",
            "",
            etGeneralOutcome.text.toString()
        )
        (activity as PatientRegistrationActivity?)?.nextFragment()

    }

    private fun String.validatePositiveResults() {
        layoutAdmitted.visibility = View.VISIBLE
        itGeneralOutcome.visibility = View.GONE

        val reason = etReasonForAdmission.text.toString()
        val treatment = etTreatment.text.toString()
        val date = etAdmissionDate.text.toString()


        if (reason.isEmpty()) {
            itReasonForAdmission.error = "Please enter reason"
            return
        }
        if (treatment.isEmpty()) {
            itTreatment.error = "Please enter treatment"
            return
        }
        if (date.isEmpty()) {
            itAdmissionDate.error = "Please select date"
            return
        }

        admissionViewModel.saveData(
            this, reason, treatment, date, getOutcomeStatus(),
            etDeathReason.text.toString(),
            etDischarge.text.toString(),
            etGeneralOutcome.text.toString()
        )

        (activity as PatientRegistrationActivity?)?.nextFragment()

    }

    private fun getOutcomeStatus(): String {
        return when (rgAdmissionOutcome.checkedRadioButtonId) {
            R.id.rbDeath -> "Death"
            R.id.rbAlive -> "Alive"
            else -> ""

        }
    }

    private fun getAdmissionStatus(): String {
        return when (rgAdmission.checkedRadioButtonId) {
            R.id.rbYes -> "Yes"
            R.id.rbNo -> "No"
            else -> ""
        }
    }

    private fun showDatePicker(editText: EditText) {
        val myCalendar = Calendar.getInstance()

        val picker = context?.let {
            DatePickerDialog(
                it,
                R.style.MySpinnerDatePickerStyle,
                { _, year, month, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, month)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val currentMonth = month + 1

                    editText.setText("$year-$currentMonth-$dayOfMonth")


                },
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            )
        }

        picker?.datePicker?.maxDate = Date().time
        picker?.show()
    }

}



