package com.example.kotlincryptosample.crypto.data.network

import com.example.kotlincryptosample.core.data.network.constructUrl
import com.example.kotlincryptosample.core.data.network.safeCall
import com.example.kotlincryptosample.core.domain.util.NetworkError
import com.example.kotlincryptosample.core.domain.util.Result
import com.example.kotlincryptosample.core.domain.util.map
import com.example.kotlincryptosample.crypto.data.network.dto.CoinHistoryDto
import com.example.kotlincryptosample.crypto.data.network.dto.CoinsResponseDto
import com.example.kotlincryptosample.crypto.data.network.dto.toCoinPrice
import com.example.kotlincryptosample.crypto.domain.Coin
import com.example.kotlincryptosample.crypto.domain.CoinDataSource
import com.example.kotlincryptosample.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(private val httpClient: HttpClient) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> { httpClient.get(constructUrl("/assets")) }
            .map { res -> res.data.map { it.toCoin() } }

    }

    override suspend fun getCoinDetails(
        id: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMill = start.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        val endMill = end.withZoneSameInstant(ZoneId.of("UTC")).toInstant().toEpochMilli()
        return safeCall<CoinHistoryDto> {
            httpClient.get(constructUrl("/assets/${id}/history/")) {
                parameter("interval", "h6")
                parameter("start", startMill)
                parameter("end", endMill)
            }
        }.map { history ->
            history.data.map { coinPriceDto ->
                coinPriceDto.toCoinPrice()
            }
        }
    }
}