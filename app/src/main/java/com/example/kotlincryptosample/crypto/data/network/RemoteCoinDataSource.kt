package com.example.kotlincryptosample.crypto.data.network

import com.example.kotlincryptosample.core.data.network.constructUrl
import com.example.kotlincryptosample.core.data.network.safeCall
import com.example.kotlincryptosample.core.domain.util.NetworkError
import com.example.kotlincryptosample.core.domain.util.Result
import com.example.kotlincryptosample.core.domain.util.map
import com.example.kotlincryptosample.crypto.data.network.dto.CoinsResponseDto
import com.example.kotlincryptosample.crypto.domain.Coin
import com.example.kotlincryptosample.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(private val httpClient: HttpClient) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> { httpClient.get(constructUrl("/assets")) }
            .map { res -> res.data.map { it.toCoin() } }

    }
}