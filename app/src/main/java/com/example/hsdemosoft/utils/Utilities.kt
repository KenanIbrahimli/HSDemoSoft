package com.example.hsdemosoft.utils

import android.content.Context
import android.net.ConnectivityManager
import androidx.core.content.ContextCompat.getSystemService


object Utilities {

    fun chechNetwork(context: Context): Boolean{
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

}