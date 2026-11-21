package com.frogobox.sdk.network

import kotlinx.coroutines.flow.Flow

/**
 * Interface for observing device network connectivity status reactively.
 *
 * Created by Muhammad Faisal Amir
 * github.com/amirisback
 */
interface FrogoConnectivityObserver {

    /**
     * Observes continuous network connectivity changes as a [Flow].
     */
    fun observe(): Flow<FrogoConnectivityStatus>

    /**
     * Quick synchronous check if the device currently has active Internet connectivity.
     */
    val isConnected: Boolean

    enum class FrogoConnectivityStatus {
        Available,
        Losing,
        Lost,
        Unavailable
    }
}
