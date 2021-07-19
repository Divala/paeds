package com.xtrem.peads_cardiac.ui.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.xtrem.peads_cardiac.R
import com.xtrem.peads_cardiac.data.auth.AuthData
import com.xtrem.peads_cardiac.helpers.AppConfig
import com.xtrem.peads_cardiac.helpers.AppPrefKeys
import com.xtrem.peads_cardiac.helpers.SessionManager
import com.xtrem.peads_cardiac.helpers.SharedPreferenceManager
import com.xtrem.peads_cardiac.services.ServiceListener
import com.xtrem.peads_cardiac.ui.main.MainActivity
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(),
    ServiceListener {
    private val loginViewModel: LoginViewModel by viewModels()
    private lateinit var alertDialog: AlertDialog
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferenceManager = SharedPreferenceManager(this)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty()) {
                itEmail.error = "Please enter email"

                return@setOnClickListener
            }

            if (password.isEmpty()) {
                itPassword.error = "Please enter password"

                return@setOnClickListener
            }


            alertDialog = SpotsDialog.Builder().setContext(this)
                .setMessage("Logging in...").build().apply { show() }

            loginViewModel.attemptLogin(AppConfig.APP_SECRET, email, password)

            loginViewModel.post.observe(this, Observer { data ->
                alertDialog.dismiss()

                saveAuthKeysAndUserDetails(data)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                SessionManager(this).createLoginSession()
            })

            loginViewModel.errorMessage.observe(this, Observer { message ->
                alertDialog.dismiss()

                if (message.isNotEmpty()) {
                    androidx.appcompat.app.AlertDialog.Builder(this)
                        .setMessage(message)
                        .setTitle("Error")
                        .setNegativeButton("Ok") { dialogInterface, _ ->
                            loginViewModel.errorMessage.postValue("")
                            dialogInterface.cancel()
                        }
                        .show()
                }
            })

        }
    }

    private fun saveAuthKeysAndUserDetails(data: AuthData?) {
        data?.accessToken?.let {
            sharedPreferenceManager.storePreference(
                AppPrefKeys.ACCESS_TOKEN,
                it
            )
        }
        data?.user?.name?.let { sharedPreferenceManager.storePreference(AppPrefKeys.USER_NAME, it) }
        data?.user?.email?.let {
            sharedPreferenceManager.storePreference(
                AppPrefKeys.USER_EMAIL,
                it
            )
        }
        data?.user?.phone?.let {
            sharedPreferenceManager.storePreference(
                AppPrefKeys.USER_PHONE,
                it
            )
        }
    }

    override fun onStarted() {

    }

    override fun onFailure(message: String) {
        alertDialog.dismiss()

        androidx.appcompat.app.AlertDialog.Builder(this)
            .setMessage(message)
            .setNegativeButton("Ok") { dialogInterface, _ -> dialogInterface.cancel() }
            .show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        moveTaskToBack(true)
    }
}
