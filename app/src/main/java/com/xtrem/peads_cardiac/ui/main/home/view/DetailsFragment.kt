package com.xtrem.peads_cardiac.ui.main.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnNext.setOnClickListener {
            (activity as ViewDetailsActivity?)?.nextFragment()
        }

        showData(ViewDetailsActivity.record)
    }

    private fun showData(record: Record?) {
        tvName.text = record?.name
        tvPhone.text = record?.phone
        tvDob.text = record?.dob
        tvSex.text = record?.sex
        tvLocation.text = record?.location
        tvAReferralReason.text = record?.referredReason
        tvAdmissionDate.text = record?.admissionDate.toString()
        tvAdmissionDuration.text = record?.admissionDuration.toString()
        tvAdmissionOutcome.text = record?.admissionOutcome.toString()
        tvAdmissionStatus.text = record?.admissionStatus
        tvAdmissionTreatment.text = record?.admissionTreatment.toString()
        tvHeight.text = record?.height.toString()
        tvWeight.text = record?.weight.toString()
        tvAwaitingSurgeryStatus.text = record?.awaitingSurgery
        tvBloodPressure.text = record?.bloodPressure
        tvHeartRate.text = record?.heartRate
        tvPulseRate.text = record?.pulseRate
        tvRespiratoryRate.text = record?.respiratoryRate
        tvReferralStatus.text = record?.referred
        tvReferralSource.text = record?.referralSource
        tvAReferralReason.text = record?.referredReason
        tvMuac.text = record?.muac
        tvHeadCircumference.text = record?.headCircumference
        tvSurgeryPeriod.text = record?.surgeryPeriod.toString()
        tvSurgeryReason.text = record?.surgeryReason.toString()
        tvSurgerySponsor.text = record?.sponsor
        tvEligibleForSurgery.text = record?.surgery
        tvAwaitingSurgeryStatus.text = record?.awaitingSurgery
        tvPreviousCardiacSurgery.text = record?.previousCardiacSurgery
        tvDeathReason.text = record?.deathReason.toString()
        tvVisitReason.text = record?.visitReason
        tvNextVisitDate.text = record?.nextVisitDate
        tvRegisteredBy.text = record?.registeredBy
        tvDiagnosisDetails.text = record?.diagnosisDetails
        tvGeneralOutcome.text = record?.generalOutcome

        tvAgeAtPresentation.text = record?.agePresentation.toString()
        tvPhone2.text = record?.secondaryPhone.toString()

    }

}
