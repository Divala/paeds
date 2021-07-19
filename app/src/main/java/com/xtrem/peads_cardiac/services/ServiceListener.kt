package com.xtrem.peads_cardiac.services

interface ServiceListener {
    fun onStarted()
    fun onFailure(message: String)
}