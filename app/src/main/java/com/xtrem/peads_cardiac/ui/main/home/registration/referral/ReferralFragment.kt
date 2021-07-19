package com.xtrem.peads_cardiac.ui.main.home.registration.referral

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import kotlinx.android.synthetic.main.fragment_referral.*

class ReferralFragment : Fragment() {
    private lateinit var referralViewModel: ReferralViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_referral, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        referralViewModel = ViewModelProvider(this).get(ReferralViewModel::class.java)

        rgReferred.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbYes -> "Yes".validatePositiveResults()
                R.id.rbNo -> "No".validateNegativeResults()
            }
        }
        btnNext.setOnClickListener {
            val referralStatus = getReferralStatus()

            if (referralStatus == "Yes") {

                "Yes".validatePositiveResults()

            } else {
                "No".validateNegativeResults()

            }


        }

        btnBack.setOnClickListener {
            (activity as PatientRegistrationActivity?)?.previousFragment()
        }
    }

    private fun String.validateNegativeResults() {
        layoutReferralReasons.visibility = View.GONE
        tvReason.visibility = View.GONE
        etSource.visibility = View.GONE
        itSource.visibility = View.GONE

        referralViewModel.saveData("", "", this)

        (activity as PatientRegistrationActivity?)?.nextFragment()


    }

    private fun String.validatePositiveResults() {
        layoutReferralReasons.visibility = View.VISIBLE
        tvReason.visibility = View.VISIBLE
        etSource.visibility = View.VISIBLE
        itSource.visibility = View.VISIBLE

        val source = etSource.text.toString()
        val reason = getReason()

        if (source.isEmpty()) {
            itSource.error = "Please enter source"
            return
        }
        if (reason?.isEmpty()!!) {
            AlertDialog.Builder(activity)
                .setTitle("Validation Error")
                .setMessage("Choose reason")
                .setNegativeButton("Ok") { dialogInterface, i -> dialogInterface.dismiss() }
                .show()

            return
        }

        referralViewModel.saveData(
            source, reason,
            this
        )
        (activity as PatientRegistrationActivity?)?.nextFragment()


    }

    private fun getReferralStatus(): String {
        return when (rgReferred.checkedRadioButtonId) {
            R.id.rbYes -> "Yes"
            R.id.rbNo -> "No"
            else -> ""
        }
    }

    private fun getReason(): String? {
        var result = ""
        if (cbSyndromic.isChecked) {
            result += cbSyndromic.text.toString() + ","
        }
        if (cbSyndromic.isChecked) {
            result += cbSyndromic.text.toString() + ","
        }
        if (cbDizziness.isChecked) {
            result += cbDizziness.text.toString() + ","
        }
        if (cbFever.isChecked) {
            result += cbFever.text.toString() + ","
        }
        if (cbShortnessOfBreath.isChecked) {
            result += cbShortnessOfBreath.text.toString() + ","
        }
        if (cbFailureToThrive.isChecked) {
            result += cbFailureToThrive.text.toString() + ","
        }
        if (cbChestPains.isChecked) {
            result += cbChestPains.text.toString() + ","
        }
        if (cbSyncope.isChecked) {
            result += cbSyncope.text.toString() + ","
        }
        if (cbForCardiacEcho.isChecked) {
            result += cbForCardiacEcho.text.toString() + ","
        }
        if (cbUnableToWeanOffOxygen.isChecked) {
            result += cbUnableToWeanOffOxygen.text.toString() + ","
        }
        if (cbIncidentalMummur.isChecked) {
            result += cbIncidentalMummur.text.toString() + ","
        }

        result += etOther.text.toString()

        return result
    }

}
