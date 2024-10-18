package com.example.kotlincryptosample.crypto.view.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlincryptosample.crypto.view.model.DisplayableNumber
import com.example.kotlincryptosample.ui.theme.KotlinCryptoSampleTheme

@Composable
fun PriceChange(
    change: DisplayableNumber,
    modifier: Modifier = Modifier
) {
    val isNegative = change.value < 0;
    val onColor =
        if (isNegative) MaterialTheme.colorScheme.onError else MaterialTheme.colorScheme.onSecondary
    val color =
        if (isNegative) MaterialTheme.colorScheme.errorContainer else Color.Cyan
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100.dp))
            .background(color)
            .padding(horizontal = 4.dp, vertical = 0.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = modifier
//                .padding(4.dp)
                .size(20.dp),
            tint = onColor,
            imageVector = if (isNegative) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
            contentDescription = change.formatted,

            )
        Text(
            text = "${change.formatted}%",
            style = MaterialTheme.typography.labelLarge.copy(color = onColor)
        )
    }
}

@Preview
@Composable
private fun PriceChangePreview() {
    KotlinCryptoSampleTheme {
        PriceChange(change = DisplayableNumber(value = 2.33, formatted = "2.33"))
    }
}