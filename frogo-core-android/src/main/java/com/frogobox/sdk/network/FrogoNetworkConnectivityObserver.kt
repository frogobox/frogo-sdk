package com.frogobox.sdk.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Modern reactive network connectivity observer utilizing [ConnectivityManager.NetworkCallback]
 * wrapped in a coroutine [callbackFlow].
 *
 * Emits [FrogoConnectivityObserver.FrogoConnectivityStatus] changes in real-time.
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
class FrogoNetworkConnectivityObserver(
    context: Context
) : FrogoConnectivityObserver {

    private val appContext: Context = context.applicationContext
    private val connectivityManager: ConnectivityManager =
        appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override val isConnected: Boolean
        get() {
            val network = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                    capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        }

    override fun observe(): Flow<FrogoConnectivityObserver.FrogoConnectivityStatus> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    trySend(FrogoConnectivityObserver.FrogoConnectivityStatus.Available)
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    super.onLosing(network, maxMsToLive)
                    trySend(FrogoConnectivityObserver.FrogoConnectivityStatus.Losing)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    trySend(FrogoConnectivityObserver.FrogoConnectivityStatus.Lost)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    trySend(FrogoConnectivityObserver.FrogoConnectivityStatus.Unavailable)
                }
            }

            val request = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

            // Initial status emission
            if (isConnected) {
                trySend(FrogoConnectivityObserver.FrogoConnectivityStatus.Available)
            } else {
                trySend(FrogoConnectivityObserver.FrogoConnectivityStatus.Unavailable)
            }

            connectivityManager.registerNetworkCallback(request, callback)

            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}
