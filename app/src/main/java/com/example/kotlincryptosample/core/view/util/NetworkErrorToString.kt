package com.example.kotlincryptosample.core.view.util

import android.content.Context
import com.example.kotlincryptosample.R
import com.example.kotlincryptosample.core.domain.util.NetworkError

fun NetworkError.toString(context: Context): String {
    val strId = when (this) {
        is NetworkError.NoInternet -> R.string.network_error_no_intentnet
        is NetworkError.RequestTimeOut -> R.string.network_error_request_timeout
        is NetworkError.Serialization -> R.string.network_error_serialization
        is NetworkError.ServerError -> R.string.network_error_server
        is NetworkError.TooManyRequests -> R.string.network_error_too_many_request
        is NetworkError.Unknown -> R.string.network_error_unknown
    }
    return context.getString(strId)
}