package com.xtrem.peads_cardiac.ui.main.account.password

import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.services.ServiceListener
import com.xtrem.peads_cardiac.helpers.AppConfig
import com.xtrem.peads_cardiac.helpers.AppPrefKeys
import com.xtrem.peads_cardiac.helpers.SharedPreferenceManager
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_change_password.*

class ChangePasswordActivity : AppCompatActivity(),
    ServiceListener {
    private val changePasswordViewModel: ChangePasswordViewModel by viewModels()
    private lateinit var alertDialog: AlertDialog
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        setSupportActionBar(toolbar as Toolbar?)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        alertDialog = SpotsDialog.Builder()
            .setMessage("Changing password...").build()

        sharedPreferenceManager = SharedPreferenceManager(this)

        btnChangePassword.setOnClickListener {
            val oldPassword = etCurrentPassword.text.toString()
            val newPassword = etNewPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (oldPassword.isEmpty()) {
                itCurrentPassword.error = "Enter your old password"
                return@setOnClickListener
            }

            if (newPassword.isEmpty()) {
                itNewPassword.error = "Enter a new password"
                return@setOnClickListener

            }

            if (newPassword.length < 6) {
                itNewPassword.error = "Weak password, enter more than 6 characters"
                return@setOnClickListener
            }

            if (confirmPassword.isEmpty()) {
                itConfirmPassword.error = "Confirm your password"
                return@setOnClickListener
            }

            if (newPassword != confirmPassword) {
                itConfirmPassword.error = "Password not matching"
                return@setOnClickListener
            }
            changePasswordViewModel.changePassword(getToken(), getKey(), oldPassword, newPassword)
                .observe(this, Observer {
                    alertDialog.dismiss()

                    this.let {
                        androidx.appcompat.app.AlertDialog.Builder(it)
                            .setMessage("Password changed successfully").setTitle("Success")
                            .setNegativeButton("Ok") { dialogInterface, i -> dialogInterface.dismiss() }
                            .show()
                    }
                })
        }
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

    override fun onStarted() {
        alertDialog.show()
    }

    override fun onFailure(message: String) {
        alertDialog.dismiss()

        this.let {
            androidx.appcompat.app.AlertDialog.Builder(it).setMessage(message).setTitle("Error")
                .setNegativeButton("Ok") { dialogInterface, i -> dialogInterface.dismiss() }.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
