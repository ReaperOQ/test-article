package ru.reaperoq.test_article.presentation.models

import ru.reaperoq.test_article.api.models.Announcement
import ru.reaperoq.test_article.domain.FormatDateUseCase
import java.time.Instant
import java.time.ZoneId

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

fun Announcement.toAnnouncementUi(formatDateUseCase: FormatDateUseCase) : AnnouncementUi =
    AnnouncementUi(
        title,
        description,
        type.title,
        tags,
        url,
        formatDateUseCase(Instant.ofEpochSecond(dateTimestamp).atZone(ZoneId.systemDefault()).toLocalDateTime()),
        formatDateUseCase(Instant.ofEpochSecond(startDateTimestamp).atZone(ZoneId.systemDefault()).toLocalDate()),
        formatDateUseCase(Instant.ofEpochSecond(endDateTimestamp).atZone(ZoneId.systemDefault()).toLocalDate()),
    )