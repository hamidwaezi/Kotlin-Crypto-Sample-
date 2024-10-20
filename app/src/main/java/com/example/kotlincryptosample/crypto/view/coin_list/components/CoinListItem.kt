package com.example.kotlincryptosample.crypto.view.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.kotlincryptosample.crypto.domain.Coin
import com.example.kotlincryptosample.crypto.view.model.CoinUi
import com.example.kotlincryptosample.crypto.view.model.toCoinUi
import com.example.kotlincryptosample.ui.theme.KotlinCryptoSampleTheme

@Composable
fun CoinListItem(
    coinUi: CoinUi,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier

            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable(onClick = onClick)
            .padding(8.dp),

        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
//        Icon(
//            modifier = Modifier.size(60.dp),
//            imageVector = ImageVector.vectorResource(id = coinUi.iconRes),
//            contentDescription = coinUi.name,
//            tint = MaterialTheme.colorScheme.secondary,
//        )

        AsyncImage(
            model = coinUi.symbolUrl,
            contentDescription = "Translated description of what the image contains",
            modifier = modifier.size(60.dp)
        )

        Column(
            modifier = Modifier.weight(1f),

            ) {
            Text(text = coinUi.symbol, style = MaterialTheme.typography.titleMedium)
            Text(text = coinUi.name, style = MaterialTheme.typography.titleSmall)

        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = coinUi.priceUsd.formatted, style = MaterialTheme.typography.bodyLarge)
            PriceChange(
                modifier = modifier.padding(top = 2.dp),
                change = coinUi.changePercent24Hr,
            )
        }
    }
}

@Preview
@Composable
private fun CoinListItemPreview() {
    KotlinCryptoSampleTheme {
        CoinListItem(
//            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer),
            coinUi = previewCoin.toCoinUi(), onClick = { /*TODO*/ })
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