package com.paritycube.paritycube_assignmernt.Util

import android.content.Context
import android.net.ConnectivityManager

class Utilities(context: Context) {

    lateinit var context: Context

    fun haveNetworkConnection(activity: Context): Boolean {
        var haveConnectedWifi = false
        var haveConnectedMobile = false

        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.allNetworkInfo
        for (ni in netInfo) {
            if (ni.typeName.equals("WIFI", ignoreCase = true))
                if (ni.isConnected)
                    haveConnectedWifi = true
            if (ni.typeName.equals("MOBILE", ignoreCase = true))
                if (ni.isConnected)
                    haveConnectedMobile = true
        }
        return haveConnectedWifi || haveConnectedMobile
    }

}