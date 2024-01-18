package ru.reaperoq.test_article.api.models

import kotlinx.serialization.Serializable

@Serializable
data class AnnouncementsResponse(
    val total: Int,
    val list: List<Announcement>
)
