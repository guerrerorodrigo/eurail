package com.rodrigoguerrero.eurail.utils.network

sealed class NetworkState {
    object Unavailable : NetworkState()
    object Available : NetworkState()
}
