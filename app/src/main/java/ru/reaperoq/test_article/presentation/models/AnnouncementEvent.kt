package ru.reaperoq.test_article.presentation.models

sealed class AnnouncementEvent {
    data object RetryClicked : AnnouncementEvent()
}