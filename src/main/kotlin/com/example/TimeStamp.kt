package com.example

import java.time.LocalDate

data class TimeStamp(private val showTime: ShowTime, private val date: LocalDate) {
    fun validateDate() {
        if (date > LocalDate.now().plusDays(7))
            throw error("Cannot book tickets of shows beyond 7 days.")
    }
}