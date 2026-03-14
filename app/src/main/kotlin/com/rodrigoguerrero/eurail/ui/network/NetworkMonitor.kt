package com.rodrigoguerrero.eurail.ui.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import android.net.NetworkRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class NetworkMonitor @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private var connectivityManager: ConnectivityManager? = null
    private val validNetworks = HashSet<Network>()
    private val callback = buildCallback()
    private val _networkAvailableStateFlow: MutableStateFlow<NetworkState?> = MutableStateFlow(null)
    val networkAvailableStateFlow: StateFlow<NetworkState?> = _networkAvailableStateFlow.asStateFlow()

    fun init() {
        connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager?.registerNetworkCallback(networkRequest, callback)
    }

    fun close() {
        validNetworks.clear()
        connectivityManager?.unregisterNetworkCallback(callback)
    }

    private fun checkValidNetworks() {
        _networkAvailableStateFlow.update {
            if (validNetworks.isNotEmpty()) {
                NetworkState.Available
            } else {
                NetworkState.Unavailable
            }
        }
    }

    private fun buildCallback() = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            connectivityManager?.getNetworkCapabilities(network).also {
                if (it?.hasCapability(NET_CAPABILITY_INTERNET) == true) {
                    validNetworks.add(network)
                }
            }
            checkValidNetworks()
        }

        override fun onLost(network: Network) {
            validNetworks.remove(network)
            checkValidNetworks()
        }
    }
}
