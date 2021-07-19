package com.xtrem.peads_cardiac.ui.main.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.data.records.stats.StatsData
import com.xtrem.peads_cardiac.helpers.AppConfig
import com.xtrem.peads_cardiac.helpers.AppPrefKeys
import com.xtrem.peads_cardiac.helpers.SharedPreferenceManager
import com.xtrem.peads_cardiac.services.ServiceListener
import com.xtrem.peads_cardiac.ui.main.adapters.PatientDetailsAdapter
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(),
    ServiceListener {
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var alertDialog: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.todayPatients("Bearer " + getToken(), getKey())
            .observe(viewLifecycleOwner, Observer { records ->
                showPatients(records)
            })

        homeViewModel.statsSummary("Bearer " + getToken(), getKey())
            .observe(viewLifecycleOwner, Observer { data -> showStats(data) })
        alertDialog =
            SpotsDialog.Builder().setContext(activity).setMessage("Loading patients...").build()
                .apply { show() }
    }

    private fun showStats(data: StatsData?) {
        tvUsers.text = data?.users
        tvAdmitted.text = data?.admitted
        tvPatients.text = data?.patients
        tvDead.text = data?.dead
    }


    private fun showPatients(records: List<Record>?) {
        alertDialog.dismiss()

        tvName.text = "Dr. "+ context?.let { SharedPreferenceManager(it).retrievePreference(AppPrefKeys.USER_NAME, "") }

        if (records.isNullOrEmpty()) {
            tvNoPatients.visibility = View.VISIBLE
        } else {

            patientsRv.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            patientsRv.adapter = PatientDetailsAdapter(records)
        }
    }

    private fun getKey(): String {
        return AppConfig.APP_SECRET
    }

    private fun getToken(): String {
        return activity?.let {
            SharedPreferenceManager(it).retrievePreference(
                AppPrefKeys.ACCESS_TOKEN,
                ""
            )
        }
            .toString()
    }

    override fun onStarted() {
        alertDialog.show()
    }

    override fun onFailure(message: String) {
        alertDialog.dismiss()

        activity?.let {
            androidx.appcompat.app.AlertDialog.Builder(it).setMessage(message).setTitle("Error")
                .setNegativeButton("Ok") { dialogInterface, i -> dialogInterface.dismiss() }.show()
        }
    }
}
