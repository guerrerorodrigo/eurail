package com.rodrigoguerrero.eurail.ui.network

sealed class NetworkState {
    object Unavailable : NetworkState()
    object Available : NetworkState()
}
