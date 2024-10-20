package com.example.kotlincryptosample.inject

import com.example.kotlincryptosample.core.data.network.HttpClientFactory
import com.example.kotlincryptosample.crypto.domain.CoinDataSource
import com.example.kotlincryptosample.crypto.view.coin_list.CoinListViewModel
import com.example.kotlincryptosample.crypto.data.network.RemoteCoinDataSource

import io.ktor.client.engine.cio.CIO
import org.koin.androidx.compose.get
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind

import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()
//    viewModelOf(::CoinListViewModel)
    viewModel { CoinListViewModel(get()) }

}