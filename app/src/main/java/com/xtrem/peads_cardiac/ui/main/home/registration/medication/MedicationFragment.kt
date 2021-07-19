package com.xtrem.peads_cardiac.ui.main.home.registration.medication


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import kotlinx.android.synthetic.main.fragment_medication.*

class MedicationFragment : Fragment() {
    private lateinit var medicationViewModel: MedicationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {

            if (getDetails().isEmpty()) {
                AlertDialog.Builder(activity).setTitle("Validation Error")
                    .setMessage("Please choose medication")
                    .setNegativeButton("Ok") { dialogInterface, i -> dialogInterface.dismiss() }
                    .show()
                return@setOnClickListener
            }

            medicationViewModel = ViewModelProvider(this).get(MedicationViewModel::class.java)
            medicationViewModel.saveData(getDetails())
            (activity as PatientRegistrationActivity?)?.nextFragment()
        }

        btnBack.setOnClickListener {
            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }

    private fun getDetails(): String {
        var result = ""
        if (cbFrusemide.isChecked) {
            result += cbFrusemide.text.toString() + ","
        }
        if (cbSpironolactone.isChecked) {
            result += cbSpironolactone.text.toString() + ","
        }
        if (cbPropanolol.isChecked) {
            result += cbPropanolol.text.toString() + ","
        }
        if (cbLisinopril.isChecked) {
            result += cbLisinopril.text.toString() + ","
        }
        if (cbCaptopril.isChecked) {
            result += cbCaptopril.text.toString() + ","
        }
        if (cbAtenolol.isChecked) {
            result += cbAtenolol.text.toString() + ","
        }
        if (cbDigoxin.isChecked) {
            result += cbDigoxin.text.toString() + ","
        }
        if (cbAmiodarone.isChecked) {
            result += cbAmiodarone.text.toString() + ","
        }
        if (cbAdenosine.isChecked) {
            result += cbAdenosine.text.toString() + ","
        }
        if (cbBenzanthinePenicillin.isChecked) {
            result += cbBenzanthinePenicillin.text.toString() + ","
        }
        if (cbAmoxyl.isChecked) {
            result += cbAmoxyl.text.toString() + ","
        }
        if (cbMorphine.isChecked) {
            result += cbMorphine.text.toString() + ","
        }
        if (cbAspirin.isChecked) {
            result += cbAspirin.text.toString() + ","
        }
        if (cbSildenafil.isChecked){
            result +=cbSildenafil.text.toString()+ ","
        }
        if (cbEnalapril.isChecked){
            result += cbEnalapril.text.toString()+ ","
        }

        result += etOther.text.toString()

        return result
    }

}
