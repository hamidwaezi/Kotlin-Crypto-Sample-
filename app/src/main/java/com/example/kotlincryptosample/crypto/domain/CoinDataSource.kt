package com.example.kotlincryptosample.crypto.domain

import com.example.kotlincryptosample.core.domain.util.NetworkError
import com.example.kotlincryptosample.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
//    suspend fun getCoinDetails(): Result<List<Coin>, NetworkError>
}