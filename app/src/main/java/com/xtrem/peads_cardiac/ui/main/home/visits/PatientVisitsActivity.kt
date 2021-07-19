package com.xtrem.peads_cardiac.ui.main.home.visits

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.records.Record
import com.xtrem.peads_cardiac.helpers.AppConfig
import com.xtrem.peads_cardiac.helpers.AppPrefKeys
import com.xtrem.peads_cardiac.helpers.SharedPreferenceManager
import com.xtrem.peads_cardiac.ui.main.MainActivity
import com.xtrem.peads_cardiac.ui.main.adapters.PatientVisitsAdapter
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_patient_visits.*

class PatientVisitsActivity : AppCompatActivity() {
    private lateinit var alertDialog: AlertDialog
    private val patientVisitsViewModel: PatientVisitsViewModel by viewModels()

    companion object {
        var record: List<Record>? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_visits)

        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.title = intent.getStringExtra("PATIENT_NAME")
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        alertDialog =
            SpotsDialog.Builder().setContext(this).setMessage("Loading patient visits...").build().apply { show() }

        patientVisitsViewModel.patientsVisits(
            "Bearer " + getToken(),
            getKey(),
            intent.getIntExtra("PATIENT_ID", 0)
        )

        patientVisitsViewModel.post.observe(this, Observer { record ->
            showVisits(record)
        })

        patientVisitsViewModel.errorMessage.observe(this, Observer { message ->
            showError(message)
        })


        fabRegister.setOnClickListener {
            startActivity(Intent(this, PatientRegistrationActivity::class.java))
        }

    }

    private fun showVisits(visits: List<Record>?) {
        alertDialog.dismiss()

        record = visits!!
        rvPatientRecords.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvPatientRecords.adapter = PatientVisitsAdapter(visits)
    }

    private fun getKey(): String {
        return AppConfig.APP_SECRET
    }

    private fun getToken(): String {
        return this.let {
            SharedPreferenceManager(it).retrievePreference(
                AppPrefKeys.ACCESS_TOKEN,
                ""
            )
        }
            .toString()
    }

    private fun showError(message: String) {
        alertDialog.dismiss()

        androidx.appcompat.app.AlertDialog.Builder(this).setTitle("Error").setMessage(message)
            .setNegativeButton("Ok") { dialogInterface, i ->
                startActivity(Intent(this, MainActivity::class.java))
                dialogInterface.dismiss()
            }.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
