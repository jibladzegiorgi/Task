package com.giorgi.jibladze.football

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    @SuppressLint("SimpleDateFormat")
    fun getDateTimeFromTimeStamp(time: Long, mDateFormat: String="dd MMMM yyyy"): String {
        val dateFormat = SimpleDateFormat(mDateFormat)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = Date(time)
        return dateFormat.format(dateTime)
    }

}