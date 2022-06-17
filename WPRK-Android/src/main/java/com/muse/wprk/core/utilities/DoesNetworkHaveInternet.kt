package com.muse.wprk.core.utilities
import android.util.Log
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory


/**
 * Send a ping to googles primary DNS.
 * If successful, that means we have internet.
 */
object DoesNetworkHaveInternet {

    fun execute(socketFactory: SocketFactory): Boolean {
        return try {
            Log.d("IC-STATS", "PINGING google.")
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Log.d("IC-STATS", "PING success.")
            true
        } catch (e: IOException) {
            Log.e("IC-STATS", "No internet connection. ${e}")
            false
        }
    }
}