package com.example.kotlincryptosample.crypto.view.coin_list

import com.example.kotlincryptosample.crypto.view.model.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coin: CoinUi) : CoinListAction
    data object Refresh : CoinListAction
}