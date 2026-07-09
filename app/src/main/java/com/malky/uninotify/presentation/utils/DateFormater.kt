package com.malky.uninotify.presentation.utils


import android.text.format.DateFormat
import java.util.Date


fun Date.format(): String {
    return "${this.day} ${this.getMonthName()} ${this.year()}"
}


fun Date.getMonthName(): String {
    return DateFormat.format("MMMM", this).toString()
}

fun Date.year(): String {
    return DateFormat.format("yyyy",this).toString()
}

fun Date.formatTime() : String{
    return DateFormat.format("HH:mm", this.time).toString()
}