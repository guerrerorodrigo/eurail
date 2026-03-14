package com.rodrigoguerrero.eurail.ui.main

import com.rodrigoguerrero.eurail.ui.mvi.Middleware
import com.rodrigoguerrero.eurail.utils.network.NetworkMonitor
import com.rodrigoguerrero.eurail.utils.network.NetworkState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class MainNetworkMiddleware @Inject constructor(
    private val networkMonitor: NetworkMonitor,
) : Middleware<MainState, MainAction>() {
    override fun process(
        state: MainState,
        action: MainAction,
    ) {
        when (action) {
            MainAction.OnResume -> listenForNetworkChanges()
            MainAction.OnPause -> networkMonitor.close()
            else -> Unit
        }
    }

    private fun listenForNetworkChanges() {
        networkMonitor.init()
        if (!networkMonitor.isConnected()) {
            dispatch(MainAction.OnNetworkStateChanged(false))
        }
        scope.launch {
            networkMonitor.networkAvailableStateFlow
                .filterNotNull()
                .collect { networkState ->
                    handleNetworkState(networkState)
                }
        }
    }

    private fun handleNetworkState(networkState: NetworkState) {
        val isAvailable = when (networkState) {
            NetworkState.Available -> true
            NetworkState.Unavailable -> false
        }
        dispatch(MainAction.OnNetworkStateChanged(isAvailable))
    }
}
