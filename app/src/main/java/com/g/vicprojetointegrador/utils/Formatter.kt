package com.g.vicprojetointegrador.utils

fun Int.formatHourMinutes(): String {
    val hours = this.div(60)
    val minutes = this % 60
    return String.format("%dh %02dmin", hours, minutes)
}

fun Int.toBoolean(): Boolean {
    return this == 1
}

fun Double.formatPercentage() : String {
    return "${(this*10).toInt()}%"
}
//"%d: %02d: 00"
//%dh %dmin