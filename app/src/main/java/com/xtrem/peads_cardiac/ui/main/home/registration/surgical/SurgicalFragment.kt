package com.xtrem.peads_cardiac.ui.main.home.registration.surgical

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import kotlinx.android.synthetic.main.fragment_patient_demographics.*
import kotlinx.android.synthetic.main.fragment_surgical.*
import kotlinx.android.synthetic.main.fragment_surgical.btnNext
import java.util.*

class SurgicalFragment : Fragment() {
    private lateinit var surgicalViewModel: SurgicalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_surgical, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etSurgeryDate.setOnClickListener { showDatePicker() }
        rgAdmission.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbYes -> validatePositiveSurgeryStatusResults()
                R.id.rbNo -> validateNegativeSurgeryStatusResults()
            }
        }

        rgPreviousCardiacSurgery.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbYesPreviousCardiacSurgery -> validatePositivePreviousCardiacSurgeryResults()
                R.id.rbNoPreviousCardiacSurgery -> validateNegativePreviousCardiacSurgeryResults()
            }
        }

        btnNext.setOnClickListener {
            val reason = etReason.text.toString()
            val location = etSurgeryLocation.text.toString()
            val surgeryDate = etSurgeryDate.text.toString()
            val surgerySponsor = etSurgerySponsor.text.toString()

            if (getEligibilityStatus().isEmpty()) {
                showValidationError()
                return@setOnClickListener
            }
            if (getEligibilityStatus().equals("No", true)) {

                if (reason.isEmpty()) {
                    itReason.error = "Please enter reason"
                    return@setOnClickListener
                }
            }
            if (getCardiacSurgeryStatus().isEmpty()) {
                showValidationError()
                return@setOnClickListener
            }
            if (getCardiacSurgeryStatus().equals("Yes", true)) {
                if (location.isEmpty()) {
                    itLocation.error = "Please enter location"
                    return@setOnClickListener
                }
                if (surgeryDate.isEmpty()) {
                    itSurgeryDate.error = "Please enter date"
                    return@setOnClickListener
                }
            }
            if (getAwaitingSurgeryStatus().isEmpty()) {
                showValidationError()
                return@setOnClickListener
            }
            if (getEligibilityStatus().isEmpty()) {
                showValidationError()
                return@setOnClickListener
            }

            surgicalViewModel = ViewModelProvider(this).get(SurgicalViewModel::class.java)

            surgicalViewModel.saveData(
                reason,
                location,
                surgeryDate,
                surgerySponsor,
                getAwaitingSurgeryStatus(),
                getCardiacSurgeryStatus(),
                getEligibilityStatus()
            )
            (activity as PatientRegistrationActivity?)?.nextFragment()

        }

        btnBack.setOnClickListener {

            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }

    private fun validateNegativePreviousCardiacSurgeryResults() {
        etSurgeryDate.visibility = View.GONE
        itSurgeryDate.visibility = View.GONE
        etSurgeryLocation.visibility = View.GONE
        itSurgeryLocation.visibility = View.GONE
    }

    private fun validatePositivePreviousCardiacSurgeryResults() {
        etSurgeryDate.visibility = View.VISIBLE
        itSurgeryDate.visibility = View.VISIBLE
        etSurgeryLocation.visibility = View.VISIBLE
        itSurgeryLocation.visibility = View.VISIBLE
    }

    private fun validateNegativeSurgeryStatusResults() {
        itReason.visibility = View.VISIBLE
        etReason.visibility = View.VISIBLE
    }

    private fun validatePositiveSurgeryStatusResults() {
        itReason.visibility = View.GONE
        etReason.visibility = View.GONE
    }

    private fun getEligibilityStatus(): String {
        return when (rgAdmission.checkedRadioButtonId) {
            R.id.rbYes -> "Yes"
            R.id.rbNo -> "No"
            else -> ""
        }
    }

    private fun getCardiacSurgeryStatus(): String {
        return when (rgPreviousCardiacSurgery.checkedRadioButtonId) {
            R.id.rbYesPreviousCardiacSurgery -> "Yes"
            R.id.rbNoPreviousCardiacSurgery -> "No"
            else -> ""
        }
    }

    private fun getAwaitingSurgeryStatus(): String {
        return when (rgAwaitingSurgery.checkedRadioButtonId) {
            R.id.rbYesAwaitingSurgery -> "Yes"
            R.id.rbNoAwaitingSurgery -> "No"
            else -> ""
        }
    }

    private fun showValidationError() {
        AlertDialog.Builder(activity).setMessage("Please choose status")
            .setTitle("Validation Error")
            .setNegativeButton("Ok") { dialogInterface, i -> dialogInterface.dismiss() }
            .show()
    }

    private fun showDatePicker() {
        val myCalendar = Calendar.getInstance()

        val picker = context?.let {
            DatePickerDialog(
                it,
                R.style.MySpinnerDatePickerStyle,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, month + 1)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    etSurgeryDate.setText("$year-$month-$dayOfMonth")


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
