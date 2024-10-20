package com.example.kotlincryptosample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.kotlincryptosample.core.view.nav.AdaptiveCoinPane
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListViewModel
import com.example.kotlincryptosample.ui.theme.KotlinCryptoSampleTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        enableEdgeToEdge()
        setContent {
//            val vm = koinViewModel<CoinListViewModel>()
            val viewModel: CoinListViewModel by viewModel()

            KotlinCryptoSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AdaptiveCoinPane(viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


