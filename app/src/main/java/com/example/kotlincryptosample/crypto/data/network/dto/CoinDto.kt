package com.example.kotlincryptosample.crypto.data.network.dto

import com.example.kotlincryptosample.crypto.domain.Coin
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String,
    val rank: Int,
    val symbol: String,
    val name: String,
    val supply: Double,
    val maxSupply: String,
    val marketCapUsd: Double,
    val volumeUsd24Hr: Double,
    val priceUsd: Double,
    val changePercent24Hr: Double,
    val vwap24Hr: Double,
) {
    fun toCoin(): Coin {
        return Coin(
            id = id,
            rank = rank,
            name = name,
            symbol = symbol,
            priceUsd = priceUsd,
            marketCapUsd = marketCapUsd,
            changePercent24Hr = changePercent24Hr,
        )
    }
}