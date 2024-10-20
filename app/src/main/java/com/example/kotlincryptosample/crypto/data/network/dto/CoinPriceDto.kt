package com.example.kotlincryptosample.crypto.data.network.dto

import com.example.kotlincryptosample.crypto.domain.CoinPrice
import kotlinx.serialization.Serializable
import java.time.Instant
import java.time.ZoneId

@Serializable
data class CoinPriceDto(val priceUsd: Double, val time: Long)

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        price = priceUsd,
        time = Instant.ofEpochMilli(time).atZone(ZoneId.of("UTC"))
    )
}