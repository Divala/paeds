package com.xtrem.peads_cardiac.ui.main.home.registration.investigation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import kotlinx.android.synthetic.main.fragment_investigation.*

class InvestigationFragment : Fragment() {
    private lateinit var investigationViewModel: InvestigationViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_investigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {
            validateAndProceed()
        }

        btnBack.setOnClickListener {
            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }

    private fun validateAndProceed() {
        val situs = etSitus.text.toString()
        val venousReturn = etVenousReturn.text.toString()
        val mitral = etMitral.text.toString()
        val tricuspid = etTricuspid.text.toString()
        val valvesAortic = etValvesAortic.text.toString()
        val pulmonary = etPulmonary.text.toString()
        val lv = etLV.text.toString()
        val ivs = etIVS.text.toString()
        val rv = etRV.text.toString()
        val la = etLA.text.toString()
        val ias = etIAS.text.toString()
        val ra = etRA.text.toString()
        val lvdd = etLVDd.text.toString()
        val lvds = etLVDs.text.toString()
        val lvef = etLVEF.text.toString()
        val sf = etSF.text.toString()
        val ao = etAO.text.toString()
        val laao = etLAAO.text.toString()
        val aorta = etAorta.text.toString()
        val mpa = etMPA.text.toString()
        val rpa = etRPA.text.toString()
        val lpa = etLPA.text.toString()
        val coronaries = etCoronaries.text.toString()
        val aortic = etAortic.text.toString()
        val lvpwd = etLVPWd.text.toString()
        val lvpws = etLVPWs.text.toString()
        val edv = etEDV.text.toString()
        val esv = etESV.text.toString()
        val ef = etEF.text.toString()
        val sv = etSV.text.toString()
        val fs = etFS.text.toString()

        etLA.addTextChangedListener(object : TextWatcher {
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

                val value = (etLA.text.toString() + ":" + etAO.text.toString())
                etLAAO.setText(value)
            }
        })

        etAO.addTextChangedListener(object : TextWatcher {
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
                val value = (etLA.text.toString() + ":" + etAO.text.toString())

                etLAAO.setText(value)
            }
        })

        /* if (situs.isEmpty()) {
             itSitus.error = "Please enter Situs details"
             return
         }
         if (venousReturn.isEmpty()) {
             itVenousReturn.error = "Please enter Venous Return"
             return
         }
         if (mitral.isEmpty()) {
             itMitral.error = "Please enter Mitral details"
             return
         }
         if (tricuspid.isEmpty()) {
             itTricuspid.error = "Please enter Tricuspid details"
             return
         }
         if (valvesAortic.isEmpty()) {
             itValvesAortic.error = "Please enter Aortic details"
             return
         }
         if (pulmonary.isEmpty()) {
             itPulmonary.error = "Please enter Pulmonary details"
             return
         }
         if (getGreatVesselRelations().isEmpty()) {
             AlertDialog.Builder(activity).setTitle("Validation Error")
                 .setMessage("Please select Great Vessel Relations")
                 .setNegativeButton("Ok") { dialogInterface, i ->
                     dialogInterface.dismiss()
                 }.show()
             return
         }
         if (lv.isEmpty()) {
             itLV.error = "Please enter LV"
             return
         }
         if (ivs.isEmpty()) {
             itIVS.error = "Please enter IVS"
             return
         }
         if (rv.isEmpty()) {
             itRV.error = "Please enter RV"
             return
         }
         if (la.isEmpty()) {
             itLA.error = "Please enter LA"
             return
         }
         if (ias.isEmpty()) {
             itIAS.error = "Please enter IAS"
             return
         }
         if (ra.isEmpty()) {
             itRA.error = "Please enter RA"
             return
         }
         if (lvdd.isEmpty()) {
             itLVDd.error = "Please enter LVDd"
             return
         }
         if (lvds.isEmpty()) {
             itLVDs.error = "Please enter LVDs"
             return
         }
         if (lvef.isEmpty()) {
             itLVEF.error = "Please enter LVEF"
             return
         }
         if (lvdd.isEmpty()) {
             itLVDd.error = "Please enter LVDd"
             return
         }
         if (sf.isEmpty()) {
             itSF.error = "Please enter SF"
             return
         }
         if (ao.isEmpty()) {
             itAO.error = "Please enter AO"
             return
         }
         if (laao.isEmpty()) {
             itLAAO.error = "Please enter LA:AO"
             return
         }
         if (getPericardialEffusion().isEmpty()) {
             AlertDialog.Builder(activity)
                 .setMessage("Please select Pericardial Effusion")
                 .setTitle("Validation Error")
                 .setNegativeButton("Ok") { dialogInterface, i -> dialogInterface.dismiss() }
                 .show()

             return
         }
         if (aorta.isEmpty()) {
             itAorta.error = "Please enter Aorta"
             return
         }
         if (mpa.isEmpty()) {
             itMPA.error = "Please enter MPA"
             return
         }
         if (rpa.isEmpty()) {
             itRPA.error = "Please enter RPA"
             return
         }
         if (lpa.isEmpty()) {
             itLPA.error = "Please enter LPA"
             return
         }
         if (coronaries.isEmpty()) {
             itCoronaries.error = "Please enter Coronaries"
             return
         }
         if (aortic.isEmpty()) {
             itAortic.error = "Please enter Aortic"
             return
         }
 */

        val valvesMorphology =
            "Mitral: $mitral,Tricuspid: $tricuspid,Aortic: $valvesAortic,Pulmonary: $pulmonary"
        val measurements =
            "LV: $lv,IVS: $ivs,RV: $rv,LA: $la,IAS: $ias,RA: $ra,LVDd: $lvdd,LVDs: $lvds,LVEF: $lvef,SF: $sf,AO: $ao,LA:Ao: $laao"
        val greatVesselMeasurements =
            "Aorta: $aorta,MPA: $mpa,RPA: $rpa,LPA: $lpa,LVPWD: $lvpwd,LVPWS: $lvpws,EDV: $edv,ESV: $esv,EF: $ef,SV: $sv,FS $fs"
        val other = etOther.text.toString()

        investigationViewModel = ViewModelProvider(this).get(InvestigationViewModel::class.java)
        investigationViewModel.saveData(
            situs,
            venousReturn,
            valvesMorphology,
            measurements,
            greatVesselMeasurements,
            other,
            getGreatVesselRelations(),
            coronaries,
            aortic,
            getPericardialEffusion()
        )
        (activity as PatientRegistrationActivity?)?.nextFragment()


    }

    private fun getPericardialEffusion(): String {
        return when (rgPericardialEffusion.checkedRadioButtonId) {
            R.id.rbYes -> "Yes"
            R.id.rbNo -> "No"
            else -> ""
        }
    }

    private fun getGreatVesselRelations(): String {
        return when (rgGreatVesselRelations.checkedRadioButtonId) {
            R.id.rbNormal -> "Normal"
            R.id.rbAbnormal -> "Abnormal"
            else -> ""
        }
    }

}
