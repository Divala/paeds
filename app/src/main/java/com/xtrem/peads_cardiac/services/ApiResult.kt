package com.xtrem.peads_cardiac.services

sealed class ApiResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(val exception: String) : ApiResult<Nothing>()
}