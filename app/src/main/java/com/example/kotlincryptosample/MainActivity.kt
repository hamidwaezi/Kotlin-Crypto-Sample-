package com.example.kotlincryptosample

import com.example.kotlincryptosample.crypto.view.coin_list.CoinListViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListScreen
import com.example.kotlincryptosample.ui.theme.KotlinCryptoSampleTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
//            val vm = koinViewModel<CoinListViewModel>()
            val viewModel: CoinListViewModel by viewModel()

            KotlinCryptoSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val state by viewModel.state.collectAsStateWithLifecycle()
                    CoinListScreen(
                        state = state,
                        modifier = Modifier.padding(innerPadding)
                    )
//                    Text(text = "Hamid is Here")
                }
            }
        }
    }
}


