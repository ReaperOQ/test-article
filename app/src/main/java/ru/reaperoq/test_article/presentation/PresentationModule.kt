package ru.reaperoq.test_article.presentation

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::AnnouncementsViewModel)
}