package com.g.vicprojetointegrador.utils

fun Int.formatHourMinutes(): String {
    val hours = this.div(60)
    val minutes = this % 60
    return String.format("%dh %02dmin", hours, minutes)
}

fun Double.formatPercentage() : String {
    return "${(this*10).toInt()}%"
}

