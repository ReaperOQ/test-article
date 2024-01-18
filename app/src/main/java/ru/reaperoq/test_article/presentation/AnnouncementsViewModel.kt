package ru.reaperoq.test_article.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.reaperoq.test_article.api.models.Announcement
import ru.reaperoq.test_article.domain.FormatDateUseCase
import ru.reaperoq.test_article.domain.GetAnnouncementsListUseCase
import ru.reaperoq.test_article.presentation.models.AnnouncementEvent
import ru.reaperoq.test_article.presentation.models.AnnouncementsUiState
import ru.reaperoq.test_article.presentation.models.toAnnouncementUi

class AnnouncementsViewModel(
    private val getAnnouncementsListUseCase: GetAnnouncementsListUseCase,
    private val formatDateUseCase: FormatDateUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<AnnouncementsUiState> =
        MutableStateFlow(AnnouncementsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun obtainEvent(event: AnnouncementEvent) {
        when (event) {
            AnnouncementEvent.RetryClicked -> {
                loadData()
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            getAnnouncementsListUseCase()
                .onSuccess { list ->
                    _uiState.update {
                        it.copy(
                            isLoaded = true,
                            list = list.map { announcement ->
                                announcement.toAnnouncementUi(formatDateUseCase)
                            }
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            errorMessage = error.message
                        )
                    }
                }
        }
    }
}