package com.example.kotlincryptosample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListScreen
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListState
import com.example.kotlincryptosample.crypto.view.coin_list.components.CoinListItem
import com.example.kotlincryptosample.crypto.view.coin_list.components.previewCoin
import com.example.kotlincryptosample.crypto.view.model.toCoinUi
import com.example.kotlincryptosample.ui.theme.KotlinCryptoSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinCryptoSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
    KotlinCryptoSampleTheme {
        CoinListScreen(

            state = CoinListState(
                isLoading = false,
                coins = IntArray(100).map { previewCoin.toCoinUi() }.toList()
            ),
            modifier = modifier.padding(
                16.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinCryptoSampleTheme {
        Greeting("Android")
    }
}