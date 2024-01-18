package ru.reaperoq.test_article.presentation.models

import ru.reaperoq.test_article.api.models.Announcement
import ru.reaperoq.test_article.domain.FormatDateUseCase
import java.util.Date

data class AnnouncementUi(
    val title: String,
    val description: String,
    val type: String,
    val tags: List<String>,
    val url: String,
    val date: String,
    val startDate: String,
    val endDate: String
)

fun Announcement.toAnnouncementUi(formatDateUseCase: FormatDateUseCase): AnnouncementUi =
    AnnouncementUi(
        title,
        description,
        type.title,
        tags,
        url,
        formatDateUseCase(Date(dateTimestamp), true),
        formatDateUseCase(Date(startDateTimestamp)),
        formatDateUseCase(Date(endDateTimestamp)),
    )