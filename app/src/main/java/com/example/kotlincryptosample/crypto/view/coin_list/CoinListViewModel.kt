package com.example.kotlincryptosample.crypto.view.coin_list
//package com.example.kotlincryptosample.crypto.view.coin_list
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.kotlincryptosample.core.domain.util.onFail
//import com.example.kotlincryptosample.core.domain.util.onSuccess
//import com.example.kotlincryptosample.crypto.data.network.RemoteCoinDataSource
//import com.example.kotlincryptosample.crypto.view.model.toCoinUi
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.onStart
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//
//class com.example.kotlincryptosample.crypto.view.coin_list.CoinListViewModel(private val coinDataSource: RemoteCoinDataSource) : ViewModel() {
//    private val _state = MutableStateFlow(CoinListState())
//    val state = _state.onStart {
//        loadCoins()
//    }
//        .stateIn(
//            viewModelScope,
//            SharingStarted.WhileSubscribed(3000L),
//            CoinListState()
//        )
//
//    init {
//        loadCoins()
//    }
//
//    fun onAction(action: CoinListAction) {
//        when (action) {
//            is CoinListAction.Refresh -> {
//                loadCoins()
//            }
//
//            is CoinListAction.OnCoinClick -> {
//                //TODO
//            }
//        }
//    }
//
//    private fun loadCoins() {
//
//        viewModelScope.launch {
//            _state.update {
//                it.copy(
//                    isLoading = true
//
//                )
//            }
//            coinDataSource.getCoins()
//                .onSuccess { coins ->
//                    _state.update {
//                        it.copy(
//                            isLoading = false,
//                            coins = coins.map { it.toCoinUi() })
//                    }
//                }
//                .onFail { error -> _state.update { it.copy(isLoading = false) } }
//        }
//
//    }
//}


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlincryptosample.core.domain.util.onFail
import com.example.kotlincryptosample.core.domain.util.onSuccess
import com.example.kotlincryptosample.crypto.domain.CoinDataSource
import com.example.kotlincryptosample.crypto.view.model.CoinUi
import com.example.kotlincryptosample.crypto.view.model.toCoinPriceUi
import com.example.kotlincryptosample.crypto.view.model.toCoinUi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class CoinListViewModel(
    private val coinDataSource: CoinDataSource
) : ViewModel() {

    private val _state = MutableStateFlow(CoinListState())
    val state = _state
        .onStart { loadCoins() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            CoinListState()
        )
    private val _events = Channel<CoinListEvent> { }
    val events = _events.receiveAsFlow()

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.OnCoinClick -> {
                _state.update {
                    it.copy(selected = action.coin)
                }
                loadHistory(action.coin)
            }

            else -> {
                // todo
            }
        }
    }

    private fun loadHistory(coin: CoinUi) {
        viewModelScope.launch {
            coinDataSource.getCoinDetails(
                coin.id,
                start = ZonedDateTime.now().minusDays(5),
                end = ZonedDateTime.now()
            ).onSuccess { data ->
                _state.update { state ->
                    when (state.selected?.id) {
                        coin.id -> {
                            state.copy(selected = state.selected.copy(priceHistory = data.sortedBy { it.time }
                                .map { it.toCoinPriceUi() }))
                        }

                        else -> {
                            state
                        }
                    }
                }
            }.onFail {
                Log.d("AAAAAAAAAAAAAAAFail", it.toString())

            }
        }
    }

    private fun loadCoins() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }

            coinDataSource
                .getCoins()
                .onSuccess { coins ->

                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { coin -> coin.toCoinUi() }
                        )
                    }
                }
                .onFail { error ->
                    _state.update { it.copy(isLoading = false) }
                    _events.send(CoinListEvent.Error(error))
                }
        }
    }
}