package com.example.kotlincryptosample.crypto.view.coin_list

import androidx.compose.runtime.Immutable
import com.example.kotlincryptosample.crypto.view.model.CoinUi

@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUi> = emptyList(),
    val selected: CoinUi? = null
)
