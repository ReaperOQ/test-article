package ru.reaperoq.test_article.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FormatDateUseCase {
    private val dateFormatter = SimpleDateFormat(
        "dd.MM.yyyy",
        Locale.getDefault()
    )

    private val dateTimeFormatter = SimpleDateFormat(
        "HH:mm, dd.MM.yyyy",
        Locale.getDefault()
    )

    operator fun invoke(date: Date, includeTime: Boolean = false): String =
        if (includeTime) dateTimeFormatter.format(date) else dateFormatter.format(date)
}