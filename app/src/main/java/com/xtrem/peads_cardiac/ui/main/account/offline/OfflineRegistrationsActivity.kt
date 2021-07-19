package com.xtrem.peads_cardiac.ui.main.account.offline

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.db.PatientRecordDB
import com.xtrem.peads_cardiac.data.records.PatientRecord
import com.xtrem.peads_cardiac.helpers.AppConfig
import com.xtrem.peads_cardiac.helpers.AppPrefKeys
import com.xtrem.peads_cardiac.helpers.SharedPreferenceManager
import com.xtrem.peads_cardiac.ui.main.adapters.OfflineRegAdapter
import com.xtrem.peads_cardiac.ui.main.home.registration.clinic.ClinicViewModel
import kotlinx.android.synthetic.main.activity_offline_registrations.*

class OfflineRegistrationsActivity : AppCompatActivity() {
    private val clinicViewModel: ClinicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_registrations)

        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        PatientRecordDB.getDatabase(this).patientRecordDao().getUploadReadyRecordList()
            .observe(this, Observer { patientList ->
                if (patientList.isNullOrEmpty()) {
                    btnUpload.isEnabled = false
                    btnUpload.text = "No patients"
                }
                showPatients(patientList)
            })

        btnUpload.setOnClickListener {
            postData()
        }
    }

    private fun showPatients(patientList: List<PatientRecord>?) {
        rvPatientRecords.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvPatientRecords.adapter = patientList?.let { OfflineRegAdapter(it) }
    }

    private fun getKey(): String {
        return AppConfig.APP_SECRET
    }

    private fun getToken(): String {
        return SharedPreferenceManager(this).retrievePreference(
            AppPrefKeys.ACCESS_TOKEN,
            ""
        ).toString()
    }

    private fun updateSync() {

        clinicViewModel.setSync()


    }

    private fun postData() {
        val patientRecord = PatientRecordDB.getDatabase(this).patientRecordDao()
            .getLastSavedRecordNotSync()

        val patientId: Int = patientRecord.patientId!!
        val admissionDate: String = patientRecord.admissionDate.toString()
        val admissionDuration: String = patientRecord.admissionDuration.toString()
        val admissionOutcome: String = patientRecord.admissionOutcome.toString()
        val admissionReason: String = patientRecord.admissionReason.toString()
        val admissionStatus: String = patientRecord.admissionStatus.toString()
        val dischargedDate: String = patientRecord.dischargedDate.toString()
        val admissionTreatment: String = patientRecord.admissionDuration.toString()
        val age: String = patientRecord.age.toString()
        val awaitingSurgery: String = patientRecord.awaitingSurgery.toString()
        val bloodPressure: String = patientRecord.bloodPressure.toString()
        val cardiacEco: String = patientRecord.cardiacEco.toString()
        val chestXRay: String = patientRecord.chestXRay.toString()
        val deathReason: String = patientRecord.deathReason.toString()
        val diagnosisDetails: String = patientRecord.diagnosisDetails.toString()
        val dob: String = patientRecord.dob.toString()
        val headCircumference: String = patientRecord.headCircumference.toString()
        val heartRate: String = patientRecord.heartRate.toString()
        val height: String = patientRecord.height.toString()
        val location: String = patientRecord.location.toString()
        val medicationDetails: String = patientRecord.medicationDetails.toString()
        val muac: String = patientRecord.muac.toString()
        val name: String = patientRecord.name.toString()
        val nextVisitDate: String = patientRecord.nextVisitDate.toString()
        val phone: String = patientRecord.phone.toString()
        val previousCardiacSurgery: String =
            patientRecord.previousCardiacSurgery.toString()
        val pulseRate: String = patientRecord.pulseRate.toString()
        val pvc: String = patientRecord.pvc.toString()
        val referralSource: String = patientRecord.referralSource.toString()
        val referred: String = patientRecord.referred.toString()
        val referredReason: String = patientRecord.referredReason.toString()
        val respiratoryRate: String = patientRecord.respiratoryRate.toString()
        val sex: String = patientRecord.sex.toString()
        val sponsor: String = patientRecord.sponsor.toString()
        val surgery: String = patientRecord.surgery.toString()
        val surgeryDate: String = patientRecord.surgeryDate.toString()
        val surgeryLocation: String = patientRecord.surgeryLocation.toString()
        val surgeryPeriod: String = patientRecord.surgeryPeriod.toString()
        val surgeryReason: String = patientRecord.surgeryReason.toString()
        val visitNumber: Int = patientRecord.visitNumber ?: 1
        val visitReason: String = patientRecord.visitReason.toString()
        val cardiacMeasurements: String =
            patientRecord.cardiacMeasurements.toString()
        val cardiacOther: String = patientRecord.cardiacOther.toString()
        val greatVesselRelationsAortic: String =
            patientRecord.greatVesselRelationsAortic.toString()
        val greatVesselRelationsCoronaries: String =
            patientRecord.greatVesselRelationsCoronaries.toString()
        val greatVesselRelationsMeasurements: String =
            patientRecord.greatVesselRelationsMeasurements.toString()
        val greatVesselRelationsState: String =
            patientRecord.greatVesselRelationsState.toString()
        val pericardialEffusion: String =
            patientRecord.pericardialEffusion.toString()
        val situs: String = patientRecord.situs.toString()
        val valvesMorphology: String = patientRecord.valvesMorphology.toString()
        val venousReturn: String = patientRecord.venousReturn.toString()
        val weight: String = patientRecord.weight.toString()
        val hb: String = patientRecord.hb.toString()
        val mvc: String = patientRecord.mvc.toString()
        val plt: String = patientRecord.plt.toString()
        val wbc: String = patientRecord.wbc.toString()
        val cl: String = patientRecord.cl.toString()
        val ca: String = patientRecord.ca.toString()
        val creatinine: String = patientRecord.creatinine.toString()
        val k: String = patientRecord.k.toString()
        val mg: String = patientRecord.mg.toString()
        val na: String = patientRecord.na.toString()
        val urea: String = patientRecord.urea.toString()
        val bmi: String = patientRecord.bmi.toString()
        val ageAtPresentation = patientRecord.ageAtPresentation.toString()
        val secondaryPhone = patientRecord.secondaryPhone.toString()
        val generalOutcome = patientRecord.generalOutcome.toString()


        clinicViewModel.register(
            getKey(), "Bearer " + getToken(),
            patientId,
            name,
            dob,
            sex,
            phone,
            mg,
            admissionDate,
            visitNumber,
            valvesMorphology,
            venousReturn,
            visitReason,
            admissionDuration,
            admissionOutcome,
            admissionReason,
            admissionStatus,
            dischargedDate,
            admissionTreatment,
            awaitingSurgery,
            bloodPressure,
            cardiacEco,
            cardiacMeasurements,
            cardiacOther,
            chestXRay,
            deathReason,
            diagnosisDetails,
            hb,
            headCircumference,
            heartRate,
            height,
            location,
            medicationDetails,
            muac,
            mvc,
            nextVisitDate,
            previousCardiacSurgery,
            pericardialEffusion,
            plt,
            pulseRate,
            situs,
            sponsor,
            surgery,
            surgeryDate,
            surgeryLocation,
            surgeryPeriod,
            surgeryReason,
            pvc,
            referralSource,
            referred,
            referredReason,
            respiratoryRate,
            greatVesselRelationsAortic,
            greatVesselRelationsCoronaries,
            greatVesselRelationsMeasurements,
            greatVesselRelationsState,
            weight,
            wbc,
            ca,
            cl,
            creatinine,
            k,
            na,
            urea,
            bmi,
            ageAtPresentation,
            secondaryPhone,
            generalOutcome
        )

        clinicViewModel.post.observe(this, Observer { data ->
            updateSync()

            androidx.appcompat.app.AlertDialog.Builder(this)
                .setMessage("Patient has been registered successfully")
                .setTitle("Success")
                .setNegativeButton("Ok") { dialogInterface, _ ->
                    dialogInterface.cancel()
                }
                .show()
        })

        clinicViewModel.errorMessage.observe(this, Observer { message ->

            if (message.isNotEmpty()) {
                androidx.appcompat.app.AlertDialog.Builder(this)
                    .setMessage(message)
                    .setTitle("Error")
                    .setNegativeButton("Ok") { dialogInterface, _ ->
                        clinicViewModel.errorMessage.postValue("")
                        dialogInterface.cancel()
                    }
                    .show()
            }

        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
