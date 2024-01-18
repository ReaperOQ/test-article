package ru.reaperoq.test_article.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Announcement(
    val title: String,
    val description: String,
    val type: AnnouncementType,
    val tags: List<String>,
    val url: String,
    val dateTimestamp: Long,
    val startDateTimestamp: Long,
    val endDateTimestamp: Long
)

@Serializable
data class AnnouncementType(
    val title: String,
    val key: String
)