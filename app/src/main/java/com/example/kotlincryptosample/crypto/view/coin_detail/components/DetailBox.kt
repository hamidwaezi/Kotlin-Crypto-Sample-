package com.example.kotlincryptosample.crypto.view.coin_detail.components

import android.content.res.Resources.Theme
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.kotlincryptosample.R
import com.example.kotlincryptosample.ui.theme.KotlinCryptoSampleTheme

@Composable
fun DetailBox(
    icon: Int,
    text: String,
    title: String,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,

    ) {
    Card(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)

            .shadow(
                10.dp,
                RoundedCornerShape(20.dp),
                true,
                spotColor = MaterialTheme.colorScheme.primary,
                ambientColor = MaterialTheme.colorScheme.primary
            ),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimaryContainer),
        colors = CardDefaults.cardColors(
            contentColor = contentColor,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            Icon(
                ImageVector.vectorResource(icon),
                text,
//                tint = MaterialTheme.colorScheme.onPrimaryContainer,
            )
            Spacer(modifier.height(10.dp))
            AnimatedContent(text, label = "TEXT_LABEL") { text ->
                Text(
                    text, style = MaterialTheme.typography.headlineSmall.copy(
//                    color = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier.height(5.dp))

            AnimatedContent(title, label = "TEXT_LABEL") { text ->
                Text(
                    text, style = MaterialTheme.typography.titleSmall.copy(
//                    color = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier.height(5.dp))

        }
    }
}


@PreviewLightDark
@Composable
private fun Show() {
    KotlinCryptoSampleTheme {
        DetailBox(
            icon = R.drawable.eth,
            text = "\$123,123,213,123 \$ ",
            title = "Title",
            modifier = Modifier
        )
    }
}