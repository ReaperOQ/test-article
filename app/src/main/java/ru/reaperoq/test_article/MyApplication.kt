package ru.reaperoq.test_article

import android.app.Application
import org.koin.core.context.startKoin
import ru.reaperoq.test_article.data.dataModule
import ru.reaperoq.test_article.domain.domainModule
import ru.reaperoq.test_article.presentation.presentationModule

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule())
        }
    }
}

fun appModule() = listOf(dataModule, domainModule, presentationModule)