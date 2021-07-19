package com.xtrem.peads_cardiac.ui.main.home.registration.diagnosis


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import kotlinx.android.synthetic.main.fragment_diagnosis.*

class DiagnosisFragment : Fragment() {
    private lateinit var diagnosisViewModel: DiagnosisViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diagnosis, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {
            if (getDiagnosis().isEmpty()) {
                AlertDialog.Builder(activity).setTitle("Validation Error")
                    .setMessage("Choose diagnosis")
                    .setNegativeButton("Ok") { dialogInterface, i -> dialogInterface.dismiss() }
                    .show()
                return@setOnClickListener
            }

            diagnosisViewModel = ViewModelProvider(this).get(DiagnosisViewModel::class.java)
            diagnosisViewModel.saveData(getDiagnosis())

            (activity as PatientRegistrationActivity?)?.nextFragment()


        }

        btnBack.setOnClickListener {
            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }

    private fun getDiagnosis(): String {
        var result = ""
        if (cbPatent.isChecked) {
            result += cbPatent.text.toString() + ","
        }
        if (cbRheumatic.isChecked) {
            result += cbRheumatic.text.toString() + ","
        }
        if (cbTetralogy.isChecked) {
            result += cbTetralogy.text.toString() + ","
        }
        if (cbAtrioventral.isChecked) {
            result += cbAtrioventral.text.toString() + ","
        }
        if (cbTricuspid.isChecked) {
            result += cbTricuspid.text.toString() + ","
        }
        if (cbAtrioseptal.isChecked) {
            result += cbAtrioseptal.text.toString() + ","
        }
        if (cbTransposition.isChecked) {
            result += cbTransposition.text.toString() + ","
        }
        if (cbSyncope.isChecked) {
            result += cbSyncope.text.toString() + ","
        }
        if (cbTruncus.isChecked) {
            result += cbTruncus.text.toString() + ","
        }
        if (cbPulmonaryAtresia.isChecked) {
            result += cbPulmonaryAtresia.text.toString() + ","
        }
        if (cbAortic.isChecked) {
            result += cbAortic.text.toString() + ","
        }
        if (cbInfectiveEndocarditis.isChecked) {
            result += cbInfectiveEndocarditis.text.toString() + ","
        }
        if (cbCoarctation.isChecked) {
            result += cbCoarctation.text.toString() + ","
        }
        if (cbTotalAnomalous.isChecked) {
            result += cbTotalAnomalous.text.toString() + ","
        }
        if (cbPartialAnomalous.isChecked) {
            result += cbPartialAnomalous.text.toString() + ","
        }
        if (cbDoubleOutlet.isChecked) {
            result += cbDoubleOutlet.text.toString() + ","
        }
        if (cbPulmonaryHypertension.isChecked) {
            result += cbPulmonaryHypertension.text.toString() + ","
        }
        if (cbChestPain.isChecked) {
            result += cbChestPain.text.toString() + ","
        }
        if (cbArrhythmias.isChecked) {
            result += cbArrhythmias.text.toString() + ","
        }
        if (cbHeartBlock.isChecked) {
            result += cbHeartBlock.text.toString() + ","
        }
        if (cbNormalHeart.isChecked) {
            result += cbNormalHeart.text.toString() + ","
        }
        if (cbVertricular.isChecked) {
            result += cbVertricular.text.toString() + ","
        }
        if (cbDilated.isChecked) {
            result += cbDilated.text.toString() + ","
        }

        result += etOther.text.toString()

        return result
    }

}
