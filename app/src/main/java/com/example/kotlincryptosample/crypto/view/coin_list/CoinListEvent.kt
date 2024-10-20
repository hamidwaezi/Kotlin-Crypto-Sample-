package com.example.kotlincryptosample.crypto.view.coin_list

import com.example.kotlincryptosample.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError) : CoinListEvent
}