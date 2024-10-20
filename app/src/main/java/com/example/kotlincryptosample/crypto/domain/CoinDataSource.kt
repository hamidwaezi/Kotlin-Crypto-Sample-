package com.example.kotlincryptosample.crypto.domain

import com.example.kotlincryptosample.core.domain.util.NetworkError
import com.example.kotlincryptosample.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinDetails(
        id: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}