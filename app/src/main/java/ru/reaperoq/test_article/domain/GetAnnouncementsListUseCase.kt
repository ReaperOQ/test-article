package ru.reaperoq.test_article.domain

import ru.reaperoq.test_article.api.models.Announcement
import ru.reaperoq.test_article.data.AnnouncementsRemoteDataSource

class GetAnnouncementsListUseCase(
    private val remoteDataSource: AnnouncementsRemoteDataSource
) {
    suspend operator fun invoke(): Result<List<Announcement>> {
        return remoteDataSource.fetchLatestAnnouncements(10)
    }
}