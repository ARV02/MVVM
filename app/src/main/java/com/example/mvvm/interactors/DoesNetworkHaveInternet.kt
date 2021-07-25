package com.example.mvvm.interactors

import android.util.Log
import java.io.IOException
import java.net.InetSocketAddress
import javax.net.SocketFactory
import com.example.mvvm.network.TAG

object DoesNetworkHaveInternet {
    fun execute(socketFactory: SocketFactory):Boolean{
        return try{
            Log.d(TAG, "PINGING google.")
            val socket = socketFactory.createSocket() ?: throw IOException("Socket is null.")
            socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
            socket.close()
            Log.d(TAG, "PING success.")
            true
        }catch (e: IOException){
            Log.e(TAG, "No internet connection. ${e}")
            false
        }
    }
}