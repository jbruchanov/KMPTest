package com.example.kmptest

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toInstant


fun dateTimeTest() {
    val now = LocalDateTime(2023, 10, 20, 12, 30, 45)
    require(now.time == LocalTime(12, 30, 45))
    require(now.date == LocalDate(2023, 10, 20))
    require(now.date.plus(1, DateTimeUnit.MONTH) == LocalDate(2023, 11, 20))
    val timestamp = now.toInstant(TimeZone.UTC).epochSeconds
    println(timestamp)
}