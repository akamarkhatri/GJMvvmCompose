package com.jp.gojekassignment.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.jp.gojekassignment.BuildConfig
import com.jp.gojekassignment.idlingresources.EspressoIdlingResources


private val LOG_TAG = "GOJeck->"

fun notifyUserWithLog(msg: String,tag: String = "") {
    if (BuildConfig.DEBUG) {
        Log.d(LOG_TAG + tag, msg)
    }
}

fun Context.isConnectedToNetwork(): Boolean {
    val connectivityManager: ConnectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val activeNetworkInfo = connectivityManager.activeNetworkInfo

    return activeNetworkInfo?.isConnected ?: false
}

fun increamentIdling() {
    EspressoIdlingResources.increment()
}

fun decreamentIdling() {
    EspressoIdlingResources.decrement()
}
