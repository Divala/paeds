package com.xtrem.peads_cardiac.ui.main.home.registration.anthropometry


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import kotlinx.android.synthetic.main.fragment_patient_anthropometry.*


class PatientAnthropometryFragment : Fragment() {
    private lateinit var anthropometryViewModel: AnthropometricViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_anthropometry, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*
        etWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                val valueWeight =
                    if (etWeight.text.toString().isNotEmpty()) etWeight.text
                        .toString().toDouble() else "0".toDouble()
                val valueHeightmeters =
                    if (etHeight.text.toString().isNotEmpty()) etHeight.text
                        .toString().toDouble() else "0".toDouble()
                val value = (valueWeight / (valueHeightmeters * valueHeightmeters))
                etBmi.setText((Math.round(value * 10.0) / 10.0).toString())
            }
        })

        etHeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable) {
                val valueWeight =
                    if (etWeight.text.toString().isNotEmpty()) etWeight.text
                        .toString().toDouble() else "0".toDouble()
                val valueHeightmeters =
                    if (etHeight.text.toString().isNotEmpty()) etHeight.text
                        .toString().toDouble() else "0".toDouble()
                val value = (valueWeight / (valueHeightmeters * valueHeightmeters))
                etBmi.setText((Math.round(value * 10.0) / 10.0).toString())
            }
        })*/

        btnNext.setOnClickListener {
            val weight = etWeight.text.toString()
            val height = etHeight.text.toString()
            val muac = etMUAC.text.toString()
            val headCircumference = etHeadCircumference.text.toString()


           /* if (weight.isEmpty()) {
                itWeight.error = "Please enter weight"
                return@setOnClickListener
            }
            if (height.isEmpty()) {
                itHeight.error = "Please enter height"
                return@setOnClickListener
            }
            if (muac.isEmpty()) {
                itMUAC.error = "Please enter MUAC"
                return@setOnClickListener
            }
            if (headCircumference.isEmpty()) {
                itHeadCircumference.error = "Please enter head circumference"
                return@setOnClickListener
            }*/

            anthropometryViewModel = ViewModelProvider(this).get(AnthropometricViewModel::class.java)

            anthropometryViewModel.saveData(weight, height, muac, headCircumference)

            (activity as PatientRegistrationActivity?)?.nextFragment()

        }

        btnBack.setOnClickListener {
            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }

}
