package ru.reaperoq.test_article.presentation.models

import ru.reaperoq.test_article.api.models.Announcement

data class AnnouncementsUiState(
    val isLoaded: Boolean = false,
    val list: List<AnnouncementUi> = emptyList(),
    val errorMessage: String? = null
)
