package com.xtrem.peads_cardiac.ui.main.home.registration.admission.outcome

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import com.xtrem.peads_cardiac.ui.main.home.registration.admission.AdmissionViewModel
import kotlinx.android.synthetic.main.fragment_outcome.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class OutcomeFragment : Fragment() {
    private lateinit var admissionViewModel: AdmissionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_outcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        admissionViewModel = ViewModelProvider(this).get(AdmissionViewModel::class.java)

        etDischarge.setOnClickListener { showDatePicker() }

        rgAdmissionOutcome.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rbDeath -> validateDeath()
                R.id.rbAlive -> validateAlive()
            }
        }

        btnNext.setOnClickListener {

            if (getOutcomeStatus().isEmpty() && etDischarge.text.toString().isEmpty()) {

            }
            if (getOutcomeStatus().equals("Death", true)) {
                validateDeath()
            } else {
                validateAlive()
            }

        }

        btnBack.setOnClickListener {
            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }

    private fun getOutcomeStatus(): String {
        return when (rgAdmissionOutcome.checkedRadioButtonId) {
            R.id.rbDeath -> "Death"
            R.id.rbAlive -> "Alive"
            else -> ""

        }
    }

    private fun validateAlive() {
        itDeathReason.visibility = View.GONE

        (activity as PatientRegistrationActivity?)?.nextFragment()

    }

    private fun validateDeath() {
        itDeathReason.visibility = View.VISIBLE
        val reason = etDeathReason.text.toString()

        if (reason.isEmpty()) {
            itDeathReason.error = "Please set reason"
            return
        }


        (activity as PatientRegistrationActivity?)?.nextFragment()


    }

    private fun showDatePicker() {
        val myCalendar = Calendar.getInstance()

        val picker = context?.let {
            DatePickerDialog(
                it,
                R.style.MySpinnerDatePickerStyle,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, month)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val currentMonth = month + 1

                    etDischarge.setText("$year-$currentMonth-$dayOfMonth")


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
