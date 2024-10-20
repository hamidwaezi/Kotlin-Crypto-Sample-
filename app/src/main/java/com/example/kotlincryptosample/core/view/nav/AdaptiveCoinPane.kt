package com.example.kotlincryptosample.core.view.nav

import android.widget.Toast
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.kotlincryptosample.core.view.util.eventObserver
import com.example.kotlincryptosample.core.view.util.toString
import com.example.kotlincryptosample.crypto.view.coin_detail.CoinDetailScreen
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListAction
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListEvent
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListScreen
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalLayoutApi::class)
@Composable
fun AdaptiveCoinPane(
    viewModel: CoinListViewModel, modifier: Modifier
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

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

    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()

    NavigableListDetailPaneScaffold(
        modifier = modifier,
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinListScreen(
                    modifier = modifier,
                    state = state.value, onAction = { acttion ->
                        viewModel.onAction(acttion)
                        when (acttion) {
                            is CoinListAction.OnCoinClick -> {
                                navigator.navigateTo(
                                    pane = ListDetailPaneScaffoldRole.Detail
                                )
                            }

                            else -> {}
                        }
                    })
            }
        },
        detailPane = {
            AnimatedPane {
                CoinDetailScreen(state = state.value, modifier)
            }
        },
    )
}