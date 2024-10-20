package com.example.kotlincryptosample.crypto.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinsResponseDto(val data: List<CoinDto>)
