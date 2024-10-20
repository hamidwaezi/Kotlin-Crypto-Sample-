package com.example.kotlincryptosample.crypto.view.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlincryptosample.core.domain.util.onFail
import com.example.kotlincryptosample.core.domain.util.onSuccess
import com.example.kotlincryptosample.crypto.data.network.RemoteCoinDataSource
import com.example.kotlincryptosample.crypto.view.model.toCoinUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CoinListViewModel(private val coinDataSource: RemoteCoinDataSource) : ViewModel() {
    private val _state = MutableStateFlow(CoinListState())
    val state = _state.onStart {
        loadCoins()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(3000L),
        CoinListState()
    )

    //    init {
//        loadCoins()
//    }

    fun onAction(action: CoinListAction) {
        when (action) {
            is CoinListAction.Refresh -> {
                loadCoins()
            }

            is CoinListAction.OnCoinClick -> {
                //TODO
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
            coinDataSource.getCoins()
                .onSuccess { coins ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            coins = coins.map { it.toCoinUi() })
                    }
                }
                .onFail { error -> _state.update { it.copy(isLoading = false) } }
        }

    }
}