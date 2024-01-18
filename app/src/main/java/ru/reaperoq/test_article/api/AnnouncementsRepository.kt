package ru.reaperoq.test_article.api

import ru.reaperoq.test_article.api.models.Announcement

interface AnnouncementsRepository {
    fun fetchLatestAnnouncements(): List<Announcement>
}