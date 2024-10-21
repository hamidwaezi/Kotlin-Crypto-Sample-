package com.example.kotlincryptosample.crypto.view.model

import androidx.annotation.DrawableRes
import com.example.kotlincryptosample.crypto.domain.Coin
import com.example.kotlincryptosample.core.view.util.getDrawableIdForCoin
import com.example.kotlincryptosample.crypto.domain.CoinPrice
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int,
    val priceHistory: List<CoinPriceUi> = emptyList<CoinPriceUi>()
) {
    val symbolUrl = "https://assets.coincap.io/assets/icons/${symbol.lowercase()}@2x.png"
}

data class DisplayableNumber(
    val value: Double,
    val formatted: String,
)

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        name = name,
        priceUsd = priceUsd.toDisplayableNumber(),
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        rank = rank,
        symbol = symbol,
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)

    );
}

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }
    return DisplayableNumber(value = this, formatted = formatter.format(this))
}