package com.example.kotlincryptosample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kotlincryptosample.core.view.util.eventObserver
import com.example.kotlincryptosample.core.view.util.toString
import com.example.kotlincryptosample.crypto.view.coin_detail.CoinDetailScreen
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListEvent
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListScreen
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
                    val context = LocalContext.current

                    eventObserver(viewModel.events, onEvent = { event ->
                        when (event) {
                            is CoinListEvent.Error -> {
                                Toast.makeText(
                                    context,
                                    event.error.toString(context),
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }
                    })
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    if (state.selected == null)
                        CoinListScreen(
                            state = state,
                            onAction = viewModel::onAction,
                            modifier = Modifier
                                .padding(innerPadding)
                        )
                    else
                        CoinDetailScreen(
                            modifier = Modifier, state = state
                        )
                }
            }
        }
    }
}


