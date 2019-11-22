package com.feelsokman.androidtemplate.net.connectivity

/**
 * Interface for hiding network connectivity resolution
 */
interface ConnectivityChecker {

    fun isConnected(): Boolean
}
