package com.wamcstudios.moviesapp.core.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

// Con este metodo podemos saber si tenemos conexion a internet o no
// Devuelve true si hay conexion y false si no hay conexion
fun isOnline(context: Context): Boolean {
    // Obtenemos el servicio de conectividad del contexto
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // Verificamos si el servicio de conectividad no es nulo
    if (connectivityManager != null) {
        // Obtenemos las capacidades de la red activa
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        // Verificamos si las capacidades no son nulas
        if (capabilities != null) {
            // Verificamos si la red activa tiene capacidad de transporte celular
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            }
            // Verificamos si la red activa tiene capacidad de transporte WiFi
            else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            }
            // Verificamos si la red activa tiene capacidad de transporte Ethernet
            else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
    }

    // Si no hay conectividad o no se cumple ninguna condición anterior, retornamos false
    return false
}