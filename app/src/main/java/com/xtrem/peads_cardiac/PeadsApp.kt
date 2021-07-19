package com.xtrem.peads_cardiac

import android.app.Application
import com.mlykotom.valifi.ValiFi
import com.xtrem.peads_cardiac.helpers.AppConfig.Companion.BASE_URL
import com.xtrem.peads_cardiac.helpers.NetworkHelper
import com.xtrem.peads_cardiac.services.PeadsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PeadsApp : Application() {

    companion object {
        var service: PeadsService? = null

    }

    override fun onCreate() {
        super.onCreate()

        service = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(NetworkHelper.getSafeOkHttpClient().build())
            .build()
            .create(PeadsService::class.java)

        ValiFi.install(this)

    }

}