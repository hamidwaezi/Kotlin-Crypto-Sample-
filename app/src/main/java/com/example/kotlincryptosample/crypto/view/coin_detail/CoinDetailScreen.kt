package com.example.kotlincryptosample.crypto.view.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.kotlincryptosample.R
import com.example.kotlincryptosample.crypto.domain.Coin
import com.example.kotlincryptosample.crypto.view.coin_detail.components.DetailBox
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListState
import com.example.kotlincryptosample.crypto.view.model.toCoinUi
import com.example.kotlincryptosample.crypto.view.model.toDisplayableNumber
import com.example.kotlincryptosample.ui.theme.KotlinCryptoSampleTheme

@ExperimentalLayoutApi
@Composable
fun CoinDetailScreen(
    state: CoinListState,
    modifier: Modifier
) {
    val context = LocalContext.current

    if (state.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else if (state.selected != null) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val coin = state.selected
            AsyncImage(
                model = coin.symbolUrl,
                contentDescription = "Translated description of what the image contains",
                modifier = modifier.size(90.dp)
            )
//            Icon(
//                imageVector = ImageVector.vectorResource(coin.iconRes),
//                coin.name,
//                tint = MaterialTheme.colorScheme.primary,
//
//                )
            Text(
                text = coin.name,
                style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary)
            )
            Text(
                text = coin.symbol,
                style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.secondary)
            )
            Spacer(modifier.height(15.dp))
            FlowRow(horizontalArrangement = Arrangement.Center) {
                DetailBox(
                    R.drawable.stock,
                    "$ " + coin.marketCapUsd.formatted,
                    context.getString(R.string.market_cap),
                    modifier

                )
                DetailBox(
                    R.drawable.dollar,
                    "$ " + coin.priceUsd.formatted,
                    context.getString(R.string.price),
                    modifier
                )
                val isUp = coin.changePercent24Hr.value > 0.0
                DetailBox(
                    if (isUp) R.drawable.trending else R.drawable.trending_down,
                    "$ " + (coin.priceUsd.value * (coin.changePercent24Hr.value / 100))
                        .toDisplayableNumber().formatted,
                    context.getString(R.string.change_24_h),
                    modifier,
                    if (!isUp) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@PreviewLightDark
@Composable
private fun Preview() {
    KotlinCryptoSampleTheme {
        CoinDetailScreen(
            CoinListState(selected = previewCoin.toCoinUi()),
            modifier = Modifier
        )
    }
}

internal val previewCoin = Coin(
    id = "bitcoin",
    name = "BitCoin",
    rank = 1,
    changePercent24Hr = 0.1,
    symbol = "BTC",
    priceUsd = 62892.12,
    marketCapUsd = 123456123123.01
)
