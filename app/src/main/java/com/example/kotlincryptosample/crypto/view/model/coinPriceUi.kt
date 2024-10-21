package com.example.kotlincryptosample.crypto.view.model

import com.example.kotlincryptosample.crypto.domain.CoinPrice
import java.time.format.DateTimeFormatter

data class CoinPriceUi(val label: String, val v: Float, val h: Double)

fun CoinPrice.toCoinPriceUi(): CoinPriceUi {
    return CoinPriceUi(
        label = DateTimeFormatter.ofPattern("ha\nM/d").format(time),
        v = time.toInstant().toEpochMilli()%43200000.toFloat(),
        h = price
    )
}