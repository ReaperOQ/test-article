package ru.reaperoq.test_article.domain

import org.koin.dsl.module

val domainModule = module {
    single { GetAnnouncementsListUseCase(get()) }
    single { FormatDateUseCase() }
}