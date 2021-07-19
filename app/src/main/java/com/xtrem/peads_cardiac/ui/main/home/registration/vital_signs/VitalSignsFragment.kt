package com.xtrem.peads_cardiac.ui.main.home.registration.vital_signs


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import kotlinx.android.synthetic.main.fragment_vital_signs.*

class VitalSignsFragment : Fragment() {
    private lateinit var vitalSignsViewModel: VitalSignsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vital_signs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {

            val heartRate = etPulseRate.text.toString()
            val pulseRate = etPulseRate.text.toString()
            val respiratoryRate = etRespiratoryRate.text.toString()
            val systolic = etSystolic.text.toString()
            val diastolic = etDiastolic.text.toString()

            val bloodPressure = "$systolic/$diastolic"

        /*    if (heartRate.isEmpty()) {
                itHeartRate.error = "Please enter heart rate"
                return@setOnClickListener
            }
            if (pulseRate.isEmpty()) {
                itPulseRate.error = "Please enter pulse rate"
                return@setOnClickListener
            }
            if (respiratoryRate.isEmpty()) {
                itRespiratoryRate.error = "Please enter respiratory rate"
                return@setOnClickListener
            }
            if (bloodPressure.isEmpty()) {
                itBloodPressure.error = "Please enter blood pressure"
                return@setOnClickListener
            }*/

            vitalSignsViewModel = ViewModelProvider(this).get(VitalSignsViewModel::class.java)

            vitalSignsViewModel.saveData(heartRate, pulseRate, respiratoryRate, bloodPressure)
            (activity as PatientRegistrationActivity?)?.nextFragment()

        }

        btnBack.setOnClickListener {
            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }

}
