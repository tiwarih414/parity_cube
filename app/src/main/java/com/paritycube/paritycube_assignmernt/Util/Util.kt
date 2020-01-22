package com.paritycube.paritycube_assignmernt.Util

import android.text.format.DateFormat
import java.util.*

object Util {

    fun getDate(timestamp: Long) : String {
        val calender = Calendar.getInstance(Locale.ENGLISH)
        calender.timeInMillis = timestamp
        val date = DateFormat.format("dd MMM yyyy", calender).toString()
        return date
    }

}