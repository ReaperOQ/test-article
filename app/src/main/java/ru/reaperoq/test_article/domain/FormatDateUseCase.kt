package ru.reaperoq.test_article.domain

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Locale
import java.util.regex.Pattern

class FormatDateUseCase {
    private val dateFormatter = SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()
    )

    private val dateTimeFormatter = SimpleDateFormat(
        "HH:mm, dd.MM.yyyy",
        Locale.getDefault()
    )

    operator fun invoke(date: LocalDate): String =
        dateFormatter.format(date)

    operator fun invoke(dateTime: LocalDateTime): String =
        dateTimeFormatter.format(dateTime)
}