package com.xtrem.peads_cardiac.helpers

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object NetworkHelper {
    fun getSafeOkHttpClient(): OkHttpClient.Builder {
        return try {

            val builder = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)

            builder
        } catch (e: java.lang.Exception) {
            throw RuntimeException(e)
        }
    }

    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

}