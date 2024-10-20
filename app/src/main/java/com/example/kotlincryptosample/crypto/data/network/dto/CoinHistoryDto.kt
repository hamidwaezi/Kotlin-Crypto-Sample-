package com.example.kotlincryptosample.crypto.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinHistoryDto(val data:List<CoinPriceDto>) {
}