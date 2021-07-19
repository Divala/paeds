package com.xtrem.peads_cardiac.ui.main.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xtrem.peads_cardiac.ui.main.home.registration.admission.AdmissionFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.admission.outcome.OutcomeFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.anthropometry.PatientAnthropometryFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.clinic.ClinicFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.demographics.PatientDemographicsFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.diagnosis.DiagnosisFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.investigation.InvestigationFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.investigation.fbc.FbcFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.investigation.uandes.UandEsFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.medication.MedicationFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.referral.ReferralFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.surgical.SurgicalFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.vital_signs.VitalSignsFragment

class RegistrationPagerAdapter(fragmentManager: FragmentActivity) :
    FragmentStateAdapter(fragmentManager) {

    override fun createFragment(position: Int): Fragment {

        when (position) {
            0 -> return PatientDemographicsFragment()
            1 -> return PatientAnthropometryFragment()
            2 -> return VitalSignsFragment()
            3 -> return ReferralFragment()
            4 -> return DiagnosisFragment()
            5 -> return InvestigationFragment()
            6 -> return FbcFragment()
            7 -> return UandEsFragment()
            8 -> return MedicationFragment()
            9 -> return AdmissionFragment()
            10 -> return SurgicalFragment()
            11 -> return ClinicFragment()
        }
        return PatientDemographicsFragment()
    }

    override fun getItemCount(): Int {
        return 12
    }

}