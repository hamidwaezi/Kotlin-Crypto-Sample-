package com.example.kotlincryptosample.crypto.domain

import java.time.ZonedDateTime

data class CoinPrice(
    val price: Double,
    val time: ZonedDateTime
)