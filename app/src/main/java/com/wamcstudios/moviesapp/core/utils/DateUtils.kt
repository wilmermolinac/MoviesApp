package com.wamcstudios.moviesapp.core.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Transform date String in LocalDate
fun String.toLocalDate(): LocalDate {
    val formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return if (this.isNullOrBlank()){
        LocalDate.now()
    }else{
        LocalDate.parse(this, formatDate)
    }
}

fun LocalDate.toDateString(): String {
    val dateLocalDate = this
    val formatDate = DateTimeFormatter.ofPattern("MMMM d, yyyy")
    return dateLocalDate.format(formatDate)
}