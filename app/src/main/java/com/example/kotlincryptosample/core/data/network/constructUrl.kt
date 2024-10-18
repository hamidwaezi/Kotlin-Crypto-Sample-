package com.example.kotlincryptosample.core.data.network

import com.example.kotlincryptosample.BuildConfig

fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.apiBaseUrl) -> url
        url.startsWith('/') -> BuildConfig.apiBaseUrl + url.drop(1)
        else -> BuildConfig.apiBaseUrl + url
    }
}