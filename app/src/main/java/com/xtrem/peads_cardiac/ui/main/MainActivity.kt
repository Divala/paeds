package com.xtrem.peads_cardiac.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.helpers.SessionManager
import com.xtrem.peads_cardiac.ui.main.account.AccountFragment
import com.xtrem.peads_cardiac.ui.main.home.HomeFragment
import com.xtrem.peads_cardiac.ui.main.home.registration.PatientRegistrationActivity
import com.xtrem.peads_cardiac.ui.main.home.visits.PatientVisitsActivity
import com.xtrem.peads_cardiac.ui.main.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val MY_REQUEST_CODE = 1001

    override fun onStart() {
        super.onStart()

        SessionManager(this).checkLogin()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar as Toolbar?)

        bottomNav.setOnNavigationItemSelectedListener { item ->
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            when (item.itemId) {
                R.id.action_home -> {
                    transaction.replace(R.id.frame_layout, HomeFragment()).commit()
                }
                R.id.action_search -> {
                    transaction.replace(R.id.frame_layout, SearchFragment()).commit()
                }
                R.id.action_account -> {
                    transaction.replace(R.id.frame_layout, AccountFragment()).commit()
                }

                R.id.action_add -> {
                    PatientVisitsActivity.record = null
                    startActivity(Intent(this, PatientRegistrationActivity::class.java))
                }

            }
            true
        }

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigationView.selectedItemId = R.id.action_home

        checkUpdate()
    }

    private fun checkUpdate() {

        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo, AppUpdateType.IMMEDIATE, this, MY_REQUEST_CODE
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()

        val appUpdateManager = AppUpdateManagerFactory.create(this)

        appUpdateManager
            .appUpdateInfo
            .addOnSuccessListener { appUpdateInfo ->
                if (appUpdateInfo.updateAvailability()
                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
                ) {
                    appUpdateManager.startUpdateFlowForResult(
                        appUpdateInfo,
                        AppUpdateType.IMMEDIATE,
                        this,
                        MY_REQUEST_CODE
                    )
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                checkUpdate()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }
}
