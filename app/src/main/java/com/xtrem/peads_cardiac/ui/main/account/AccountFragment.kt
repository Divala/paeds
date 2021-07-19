package com.xtrem.peads_cardiac.ui.main.account


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import com.xtrem.peads_cardiac.helpers.AppPrefKeys
import com.xtrem.peads_cardiac.helpers.SessionManager
import com.xtrem.peads_cardiac.helpers.SharedPreferenceManager
import com.xtrem.peads_cardiac.ui.main.account.offline.OfflineRegistrationsActivity
import com.xtrem.peads_cardiac.ui.main.account.password.ChangePasswordActivity
import kotlinx.android.synthetic.main.fragment_account.*

/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : Fragment() {
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreferenceManager = activity?.let { SharedPreferenceManager(it) }!!

        offlineLayout.setOnClickListener {
            startActivity(
                Intent(
                    context,
                    OfflineRegistrationsActivity::class.java
                )
            )
        }
        changePasswordLayout.setOnClickListener {
            startActivity(Intent(activity, ChangePasswordActivity::class.java))
        }

        PatientRecordDB.getDatabase(requireActivity()).patientRecordDao().getUploadReadyRecordList()
            .observe(viewLifecycleOwner, Observer { patientList ->
                tvCount.text = "" + patientList.size
            })

        logoutLayout.setOnClickListener {
            context?.let { it1 ->
                AlertDialog.Builder(it1).setMessage("Are you sure?").setTitle("Confirm Action")
                    .setPositiveButton(
                        "Yes"
                    ) { _, _ ->

                        SessionManager(it1).logoutUser()
                    }
                    .setNegativeButton("No") { dialogInterface, _ -> dialogInterface.dismiss() }
                    .show()
            }
        }
        setUserDetails()

    }

    private fun setUserDetails() {
        tvName.text = sharedPreferenceManager.retrievePreference(AppPrefKeys.USER_NAME, "")
        tvEmail.text = sharedPreferenceManager.retrievePreference(AppPrefKeys.USER_EMAIL, "")
        tvPhone.text = sharedPreferenceManager.retrievePreference(AppPrefKeys.USER_PHONE, "")
    }
}
