package com.xtrem.peads_cardiac.ui.main.home.registration.demographics

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import com.xtrem.peads_cardiac.ui.main.home.visits.PatientVisitsActivity
import kotlinx.android.synthetic.main.fragment_patient_demographics.*
import java.util.*

class PatientDemographicsFragment : Fragment() {
    private lateinit var demographicsViewModel: DemographicsViewModel
    var vistNumber: Int = 1
    var patientId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_patient_demographics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (PatientVisitsActivity.record != null) {
            fillViews(PatientVisitsActivity.record)
        }

        etDob.setOnClickListener { showDatePicker() }

        btnNext.setOnClickListener {

            val fname = etFName.text.toString()
            val surname = etSurname.text.toString()
            val dob = etDob.text.toString()
            val location = etLocation.text.toString()
            val phone = etPhone.text.toString()
            val secondaryPhone = etPhone2.text.toString()
            //val ageAtPresentation = etAgeAtPresentation.text.toString()

            if (fname.isEmpty()) {
                itFName.error = "Please enter first name"
                return@setOnClickListener
            }
            if (surname.isEmpty()) {
                itSurname.error = "Please enter surname"
                return@setOnClickListener
            }
            if (dob.isEmpty()) {
                itDob.error = "Please enter date of birth"
                return@setOnClickListener
            }

            if (getGender().isEmpty()) {
                AlertDialog.Builder(activity).setMessage("Please choose sex").setTitle("Error")
                    .setNegativeButton("Ok") { dialogInterface, _ -> dialogInterface.cancel() }
                    .show()
                return@setOnClickListener
            }

            if (location.isEmpty()) {
                itLocation.error = "Please enter location"
                return@setOnClickListener
            }

            /* if (phone.isNotEmpty() && !PhoneContact().isValidNumber(phone)) {
                 itPhone.error = "Number not valid"
                 return@setOnClickListener
             }

             if (secondaryPhone.isNotEmpty() && !PhoneContact().isValidNumber(phone)) {
                 itPhone.error = "Number not valid"
                 return@setOnClickListener
             }*/

            demographicsViewModel = ViewModelProvider(this).get(DemographicsViewModel::class.java)

            demographicsViewModel.saveData(
                patientId,
                vistNumber,
                "$fname $surname",
                dob,
                getGender(),
                location,
                phone,
                secondaryPhone
            )

            (activity as PatientRegistrationActivity?)?.nextFragment()

        }

    }

    private fun fillViews(record: List<Record>?) {
        etFName.setText(record?.last()?.name.toString().split(" ")[0])
        etSurname.setText(record?.last()?.name.toString().split(" ")[1])
        etDob.setText(record?.last()?.dob.toString())
        etLocation.setText(record?.last()?.location.toString())
        etLocation.setText(record?.last()?.location.toString())
        etPhone.setText(record?.last()?.phone.toString())
        etPhone2.setText(record?.last()?.secondaryPhone.toString())
        vistNumber = record?.last()?.visitNumber?.plus(1) ?: 1
        if (record?.last()?.sex.toString() == "Male") {
            rbMale.isChecked = true
        } else {
            rbFemale.isChecked = true
        }
        patientId = record?.last()?.patientId!!

    }

    private fun getGender(): String {
        return when (rgSex.checkedRadioButtonId) {
            R.id.rbMale -> "Male"
            R.id.rbFemale -> "Female"
            else -> ""
        }
    }

    private fun showDatePicker() {
        val myCalendar = Calendar.getInstance()

        val picker = context?.let {
            DatePickerDialog(
                it,
                R.style.MySpinnerDatePickerStyle,
                { _, year, month, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH + 1, month)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    val currentMonth = month + 1

                    etDob.setText("$year-$currentMonth-$dayOfMonth")


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
